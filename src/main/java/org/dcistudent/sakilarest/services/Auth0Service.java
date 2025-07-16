package org.dcistudent.sakilarest.services;

import org.dcistudent.sakilarest.configs.Auth0Config;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public class Auth0Service {

  private final WebClient webClient;
  private final Auth0Config config;

  public Auth0Service(Auth0Config config, WebClient.Builder builder) {
    this.config = config;
    this.webClient = builder.baseUrl("https://" + config.getDomain()).build();
  }

  public String getManagementToken() {
    var body = Map.of(
        "grant_type", "client_credentials",
        "client_id", config.getMgmtClientId(),
        "client_secret", config.getMgmtClientSecret(),
        "audience", config.getAudience()
    );

    return webClient.post()
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
        .block();
  }

  public void registerUser(String email, String password) {
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
        .onStatus(status -> !status.is2xxSuccessful(),
            clientResponse -> clientResponse.bodyToMono(String.class).flatMap(errorBody -> {
              System.err.println("Auth0 Register User Error: " + errorBody);
              return Mono.error(new RuntimeException("Failed to register user: " + errorBody));
            }))
        .bodyToMono(Void.class)
        .block();
  }

  public Map loginUser(String username, String password) {
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

    return webClient.post()
        .uri("/oauth/token")
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(body)
        .retrieve()
        .onStatus(status -> !status.is2xxSuccessful(),
            clientResponse -> clientResponse.bodyToMono(String.class).flatMap(errorBody -> {
              System.err.println("Auth0 Login User Error: " + errorBody); // Crucial for debugging 500s
              return Mono.error(new RuntimeException("Auth0 login failed: " + errorBody));
            }))
        .bodyToMono(Map.class)
        .block();
  }
}
