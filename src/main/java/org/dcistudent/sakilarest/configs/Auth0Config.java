package org.dcistudent.sakilarest.configs;

import org.jetbrains.annotations.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "auth0")
public final class Auth0Config {

  private @NotNull String domain;
  private @NotNull String audience;
  private @NotNull String issuer;
  private @NotNull String mgmtClientId;
  private @NotNull String mgmtClientSecret;
  private @NotNull String appClientId;
  private @NotNull String appClientSecret;
  private @NotNull String connection;

  public Auth0Config() {
    this.domain = "";
    this.audience = "";
    this.issuer = "";
    this.mgmtClientId = "";
    this.mgmtClientSecret = "";
    this.appClientId = "";
    this.appClientSecret = "";
    this.connection = "";
  }

  public @NotNull String getDomain() {
    return this.domain;
  }

  public void setDomain(@NotNull String domain) {
    this.domain = domain;
  }

  public @NotNull String getAudience() {
    return this.audience;
  }

  public void setAudience(@NotNull String audience) {
    this.audience = audience;
  }

  public @NotNull String getIssuer() {
    return this.issuer;
  }

  public void setIssuer(@NotNull String issuer) {
    this.issuer = issuer;
  }

  public @NotNull String getMgmtClientId() {
    return this.mgmtClientId;
  }

  public void setMgmtClientId(@NotNull String mgmtClientId) {
    this.mgmtClientId = mgmtClientId;
  }

  public @NotNull String getMgmtClientSecret() {
    return this.mgmtClientSecret;
  }

  public void setMgmtClientSecret(@NotNull String mgmtClientSecret) {
    this.mgmtClientSecret = mgmtClientSecret;
  }

  public @NotNull String getAppClientId() {
    return this.appClientId;
  }

  public void setAppClientId(@NotNull String appClientId) {
    this.appClientId = appClientId;
  }

  public @NotNull String getAppClientSecret() {
    return this.appClientSecret;
  }

  public void setAppClientSecret(@NotNull String appClientSecret) {
    this.appClientSecret = appClientSecret;
  }

  public @NotNull String getConnection() {
    return this.connection;
  }

  public void setConnection(@NotNull String connection) {
    this.connection = connection;
  }
}
