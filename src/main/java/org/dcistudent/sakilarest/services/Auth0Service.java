package org.dcistudent.sakilarest.services;

import org.dcistudent.sakilarest.configs.Auth0Config;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
}
