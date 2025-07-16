package org.dcistudent.sakilarest.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "auth0")
public final class Auth0Config {

  private String domain;
  private String audience;
  private String issuer;
  private String mgmtClientId;
  private String mgmtClientSecret;
  private String appClientId;
  private String appClientSecret;
  private String connection;

  public String getDomain() {
    return this.domain;
  }

  public void setDomain(String domain) {
    this.domain = domain;
  }

  public String getAudience() {
    return this.audience;
  }

  public void setAudience(String audience) {
    this.audience = audience;
  }

  public String getIssuer() {
    return this.issuer;
  }

  public void setIssuer(String issuer) {
    this.issuer = issuer;
  }

  public String getMgmtClientId() {
    return this.mgmtClientId;
  }

  public void setMgmtClientId(String mgmtClientId) {
    this.mgmtClientId = mgmtClientId;
  }

  public String getMgmtClientSecret() {
    return this.mgmtClientSecret;
  }

  public void setMgmtClientSecret(String mgmtClientSecret) {
    this.mgmtClientSecret = mgmtClientSecret;
  }

  public String getAppClientId() {
    return this.appClientId;
  }

  public void setAppClientId(String appClientId) {
    this.appClientId = appClientId;
  }

  public String getAppClientSecret() {
    return this.appClientSecret;
  }

  public void setAppClientSecret(String appClientSecret) {
    this.appClientSecret = appClientSecret;
  }

  public String getConnection() {
    return this.connection;
  }

  public void setConnection(String connection) {
    this.connection = connection;
  }
}
