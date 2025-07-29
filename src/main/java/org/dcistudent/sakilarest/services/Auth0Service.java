package org.dcistudent.sakilarest.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.dcistudent.sakilarest.configs.Auth0Config;
import org.dcistudent.sakilarest.exceptions.Auth0Exception;
import org.dcistudent.sakilarest.loggers.SqlLogger;
import org.dcistudent.sakilarest.models.responses.Auth0ErrorResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Service
public class Auth0Service {

  @NotNull
  private static final String STRING_PASSWORD = "password";

  private final @NotNull RestTemplate restTemplate;
  private final @NotNull Auth0Config config;

  public Auth0Service(
      @NotNull Auth0Config config,
      @NotNull RestTemplateBuilder builder,
      @NotNull SqlLogger logger
  ) {
    this.config = config;
    ObjectMapper objectMapper = new ObjectMapper();

    this.restTemplate = builder
        .rootUri("https://" + config.getDomain())
        .errorHandler(new Auth0ResponseErrorHandler(objectMapper, logger))
        .build();
  }

  public @NotNull String getManagementToken() {
    var body = Map.of(
        "grant_type", "client_credentials",
        "client_id", this.config.getMgmtClientId(),
        "client_secret", this.config.getMgmtClientSecret(),
        "audience", this.config.getAudience()
    );

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(body, headers);

    Map<String, Object> response = Objects.requireNonNull(this.restTemplate.postForObject(
        "/oauth/token",
        requestEntity,
        Map.class
    ));
    return (String) response.get("access_token");
  }

  public void registerUser(@NotNull String email, @NotNull String password) {
    String token = this.getManagementToken();

    var body = Map.of(
        "email", email,
        Auth0Service.STRING_PASSWORD, password,
        "connection", this.config.getConnection()
    );

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + token);
    HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(body, headers);

    this.restTemplate.exchange(
        "/api/v2/users",
        HttpMethod.POST,
        requestEntity,
        Void.class
    );
  }

  public @NotNull Map<String, String> loginUser(@NotNull String username, @NotNull String password) {
    var body = Map.of(
        "grant_type", Auth0Service.STRING_PASSWORD,
        "username", username,
        Auth0Service.STRING_PASSWORD, password,
        "audience", this.config.getAudience(),
        "scope", "openid profile email",
        "client_id", this.config.getAppClientId(),
        "client_secret", this.config.getAppClientSecret(),
        "realm", this.config.getConnection()
    );

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(body, headers);

    return Objects.requireNonNull(this.restTemplate.postForObject(
        "/oauth/token",
        requestEntity,
        Map.class
    ));
  }

  private class Auth0ResponseErrorHandler implements ResponseErrorHandler {

    private final @NotNull ObjectMapper objectMapper;
    private final @NotNull SqlLogger logger;

    public Auth0ResponseErrorHandler(@NotNull ObjectMapper objectMapper, @NotNull SqlLogger logger) {
      Objects.requireNonNull(objectMapper, "ObjectMapper must not be null");
      this.objectMapper = objectMapper;
      this.logger = logger;
    }

    @Override
    public boolean hasError(@NotNull ClientHttpResponse response) throws IOException {
      return !response.getStatusCode().is2xxSuccessful();
    }

    @Override
    @SuppressWarnings("java:S7538")
    public void handleError(@NotNull ClientHttpResponse response) throws IOException {
      String errorBody = new String(response.getBody().readAllBytes());
      try {
        Auth0ErrorResponse auth0Error = this.objectMapper.readValue(errorBody, Auth0ErrorResponse.class);
        this.logger.logError(
            String.format(
                "Auth0 Error: [%d] %s - %s%n",
                auth0Error.getStatus(),
                auth0Error.getMessage(),
                auth0Error.getData()
            )
        );
        throw new Auth0Exception(auth0Error);
      } catch (IOException e) {
        // If parsing to Auth0ErrorResponse fails, throw a generic RuntimeException
        this.logger.logError(String.format("Auth0 Error (unparseable or generic): %s", errorBody));
        throw new Auth0Exception(
            new Auth0ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                String.format("Failed to process Auth0 response: %s", errorBody),
                HttpStatus.INTERNAL_SERVER_ERROR.value()
            )
        );
      }
    }
  }
}
