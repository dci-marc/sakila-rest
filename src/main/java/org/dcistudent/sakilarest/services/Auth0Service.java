package org.dcistudent.sakilarest.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.dcistudent.sakilarest.configs.Auth0Config;
import org.dcistudent.sakilarest.exceptions.Auth0Exception;
import org.dcistudent.sakilarest.models.responses.Auth0ErrorResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Service
public class Auth0Service {

  private final @NotNull RestTemplate restTemplate;
  private final @NotNull Auth0Config config;

  public Auth0Service(@NotNull Auth0Config config, @NotNull RestTemplateBuilder builder) {
    this.config = config;
    @NotNull ObjectMapper objectMapper = new ObjectMapper();

    this.restTemplate = builder
        .rootUri("https://" + config.getDomain())
        .errorHandler(new Auth0ResponseErrorHandler(objectMapper))
        .build();
  }

  public @NotNull String getManagementToken() {
    var body = Map.of(
        "grant_type", "client_credentials",
        "client_id", config.getMgmtClientId(),
        "client_secret", config.getMgmtClientSecret(),
        "audience", config.getAudience()
    );

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(body, headers);

    Map<String, Object> response = Objects.requireNonNull(restTemplate.postForObject(
        "/oauth/token",
        requestEntity,
        Map.class
    ));
    return (String) response.get("access_token");
  }

  public void registerUser(@NotNull String email, @NotNull String password) {
    String token = getManagementToken();

    var body = Map.of(
        "email", email,
        "password", password,
        "connection", config.getConnection()
    );

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + token);
    HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(body, headers);

    restTemplate.exchange(
        "/api/v2/users",
        HttpMethod.POST,
        requestEntity,
        Void.class
    );
  }

  public @NotNull Map<String, String> loginUser(@NotNull String username, @NotNull String password) {
    var body = Map.of(
        "grant_type", "password",
        "username", username,
        "password", password,
        "audience", config.getAudience(),
        "scope", "openid profile email",
        "client_id", config.getAppClientId(),
        "client_secret", config.getAppClientSecret(),
        "realm", config.getConnection()
    );

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(body, headers);

    return Objects.requireNonNull(restTemplate.postForObject(
        "/oauth/token",
        requestEntity,
        Map.class
    ));
  }

  private record Auth0ResponseErrorHandler(ObjectMapper objectMapper) implements ResponseErrorHandler {

    @Override
      public boolean hasError(@NotNull ClientHttpResponse response) throws IOException {
        return !response.getStatusCode().is2xxSuccessful();
      }

      @Override
      public void handleError(@NotNull ClientHttpResponse response) throws IOException {
        String errorBody = new String(response.getBody().readAllBytes());
        try {
          Auth0ErrorResponse auth0Error = objectMapper.readValue(errorBody, Auth0ErrorResponse.class);
          System.err.printf(
              "Auth0 Error: [%d] %s - %s%n", auth0Error.getStatusCode(), auth0Error.getError(), auth0Error.getMessage()
          );
          throw new Auth0Exception(auth0Error);
        } catch (IOException e) {
          // If parsing to Auth0ErrorResponse fails, throw a generic RuntimeException
          System.err.println("Auth0 Error (unparseable or generic): " + errorBody);
          throw new RuntimeException("Failed to process Auth0 response: " + errorBody, e);
        }
      }
    }
}