package org.dcistudent.sakilarest.services;

import org.dcistudent.sakilarest.configs.Auth0Config;
import org.dcistudent.sakilarest.exceptions.Auth0Exception;
import org.dcistudent.sakilarest.models.responses.Auth0ErrorResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.Objects;

@Service
public class Auth0Service {

  private final @NotNull WebClient webClient;
  private final @NotNull Auth0Config config;

  public Auth0Service(@NotNull Auth0Config config, @NotNull WebClient.Builder builder) {
    this.config = config;
    this.webClient = builder.baseUrl("https://" + config.getDomain()).build();
  }

  public @NotNull String getManagementToken() {
    var body = Map.of(
        "grant_type", "client_credentials",
        "client_id", config.getMgmtClientId(),
        "client_secret", config.getMgmtClientSecret(),
        "audience", config.getAudience()
    );

    return Objects.requireNonNull(webClient.post()
        .uri("/oauth/token")
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(body)
        .retrieve()
        .onStatus(status -> !status.is2xxSuccessful(),
            clientResponse -> clientResponse.bodyToMono(String.class).flatMap(errorBody -> {
              System.err.println("Auth0 Management Token Error: " + errorBody);
              return Mono.error(new RuntimeException("Failed to get management token: " + errorBody));
            }))
        .bodyToMono(Map.class)
        .map(resp -> (String) resp.get("access_token"))
        .block());
  }

  public void registerUser(@NotNull String email, @NotNull String password) {
    String token = getManagementToken();

    var body = Map.of(
        "email", email,
        "password", password,
        "connection", config.getConnection()
    );

    webClient.post()
        .uri("/api/v2/users")
        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(body)
        .retrieve()
        .onStatus(status -> !status.is2xxSuccessful(), clientResponse ->
            clientResponse.bodyToMono(Auth0ErrorResponse.class)
                .flatMap(error -> {
                  System.err.printf(
                      "Auth0 Error: [%d] %s - %s%n", error.getStatusCode(), error.getError(), error.getMessage()
                  );
                  return Mono.error(new Auth0Exception(error));
                })
        )
        .bodyToMono(Void.class)
        .block();
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

    return Objects.requireNonNull(webClient.post()
        .uri("/oauth/token")
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(body)
        .retrieve()
        .onStatus(status -> !status.is2xxSuccessful(), clientResponse ->
            clientResponse.bodyToMono(Auth0ErrorResponse.class)
                .flatMap(error -> {
                  System.err.printf(
                      "Auth0 Error: [%d] %s - %s%n", error.getStatusCode(), error.getError(), error.getMessage()
                  );
                  return Mono.error(new Auth0Exception(error));
                })
        )
        .bodyToMono(Map.class)
        .block());
  }
}
