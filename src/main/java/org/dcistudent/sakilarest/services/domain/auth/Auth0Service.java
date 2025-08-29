package org.dcistudent.sakilarest.services.domain.auth;

import org.dcistudent.sakilarest.configs.Auth0Config;
import org.dcistudent.sakilarest.exceptions.domain.Auth0Exception;
import org.dcistudent.sakilarest.models.responses.error.Auth0ErrorResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

import java.util.Map;
import java.util.Objects;

@Service
public final class Auth0Service {

  private static final @NotNull String STRING_PASSWORD = "password";
  private static final @NotNull String STRING_ACCESS_TOKEN = "access_token";

  private final @NotNull RestClient restClient;
  private final @NotNull Auth0Config config;

  public Auth0Service(@NotNull Auth0Config config) {
    this.config = config;
    this.restClient = RestClient.builder()
        .baseUrl("https://" + config.getDomain())
        .build();
  }

  public @NotNull String getManagementToken() {
    Map<String, String> body = Map.of(
        "grant_type", "client_credentials",
        "client_id", this.config.getMgmtClientId(),
        "client_secret", this.config.getMgmtClientSecret(),
        "audience", this.config.getAudience()
    );

    Map<String, Object> response = Objects.requireNonNullElseGet(
        this.restClient.post()
            .uri("/oauth/token")
            .contentType(MediaType.APPLICATION_JSON)
            .body(body)
            .retrieve()
            .body(Map.class),
        Map::of
    );

    if (response.isEmpty() || !response.containsKey(Auth0Service.STRING_ACCESS_TOKEN)) {
      throw new Auth0Exception(
          new Auth0ErrorResponse(
              HttpStatus.INTERNAL_SERVER_ERROR,
              "auth:management.token:fail",
              HttpStatus.INTERNAL_SERVER_ERROR.value()
          )
      );
    }

    return (String) response.get(Auth0Service.STRING_ACCESS_TOKEN);
  }

  public void registerUser(@NotNull String email, @NotNull String password) {
    String token = this.getManagementToken();

    Map<String, String> body = Map.of(
        "email", email,
        Auth0Service.STRING_PASSWORD, password,
        "connection", this.config.getConnection()
    );

    try {
      this.restClient.post()
          .uri("/api/v2/users")
          .contentType(MediaType.APPLICATION_JSON)
          .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
          .body(body)
          .retrieve()
          .toBodilessEntity();
    } catch (RestClientResponseException e) {
      throw new Auth0Exception(
          new Auth0ErrorResponse(HttpStatus.valueOf(e.getStatusCode().value()))
      );
    }
  }

  public @NotNull String loginUser(@NotNull String username, @NotNull String password) {
    Map<String, String> body = Map.of(
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

    try {
      response = Objects.requireNonNullElseGet(
          this.restClient.post()
              .uri("/oauth/token")
              .contentType(MediaType.APPLICATION_JSON)
              .body(body)
              .retrieve()
              .body(Map.class),
          Map::of
      );

      if (response.isEmpty() || !response.containsKey(Auth0Service.STRING_ACCESS_TOKEN)) {
        throw new Auth0Exception(
            new Auth0ErrorResponse(HttpStatus.SERVICE_UNAVAILABLE)
        );
      }
    } catch (RestClientResponseException e) {
      throw new Auth0Exception(
          new Auth0ErrorResponse(HttpStatus.valueOf(e.getStatusCode().value()))
      );
    }

    return (String) response.get(Auth0Service.STRING_ACCESS_TOKEN);
  }
}
