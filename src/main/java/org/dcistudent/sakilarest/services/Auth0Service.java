package org.dcistudent.sakilarest.services;

import org.dcistudent.sakilarest.configs.Auth0Config;
import org.dcistudent.sakilarest.exceptions.Auth0Exception;
import org.dcistudent.sakilarest.models.responses.error.Auth0ErrorResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class Auth0Service {

  private static final @NotNull String STRING_PASSWORD = "password";
  private static final @NotNull String STRING_ACCESS_TOKEN = "access_token";

  private final @NotNull RestTemplate restTemplate;
  private final @NotNull Auth0Config config;

  public Auth0Service(
      @NotNull Auth0Config config,
      @NotNull RestTemplateBuilder builder
  ) {
    this.config = config;
    this.restTemplate = builder
        .rootUri("https://" + config.getDomain())
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

    Map<String, Object> response = this.restTemplate.postForObject(
        "/oauth/token",
        requestEntity,
        Map.class
    );

    if (response == null || !response.containsKey(Auth0Service.STRING_ACCESS_TOKEN)) {
      throw new Auth0Exception(
          new Auth0ErrorResponse(
              HttpStatus.INTERNAL_SERVER_ERROR.value(),
              "auth:management.token:fail",
              HttpStatus.INTERNAL_SERVER_ERROR.value()
          )
      );
    }

    return (String) response.get(Auth0Service.STRING_ACCESS_TOKEN);
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

    try {
      this.restTemplate.exchange(
          "/api/v2/users",
          HttpMethod.POST,
          requestEntity,
          Void.class
      );
    } catch (RestClientResponseException e) {
      throw new Auth0Exception(
          new Auth0ErrorResponse(e.getStatusCode().value())
      );
    }
  }

  public @NotNull String loginUser(@NotNull String username, @NotNull String password) {
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
    Map<String, Object> response;

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(body, headers);

    try {
      response = this.restTemplate.postForObject(
          "/oauth/token",
          requestEntity,
          Map.class
      );

      if (response == null || !response.containsKey(Auth0Service.STRING_ACCESS_TOKEN)) {
        throw new Auth0Exception(
            new Auth0ErrorResponse(HttpStatus.SERVICE_UNAVAILABLE.value())
        );
      }
    } catch (RestClientResponseException e) {
      throw new Auth0Exception(
          new Auth0ErrorResponse(e.getStatusCode().value())
      );
    }

    return (String) response.get(Auth0Service.STRING_ACCESS_TOKEN);
  }
}
