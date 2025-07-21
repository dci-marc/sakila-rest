package org.dcistudent.sakilarest.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.profiles")
public final class ApplicationProfileConfig {

  private String active;
  public static final String PROFILE_DEVELOPMENT = "dev";
  public static final String PROFILE_PRODUCTION = "prod";

  public String getActive() {
    return active;
  }

  public void setActive(String active) {
    this.active = active;
  }

  public Boolean isDevProfile() {
    return PROFILE_DEVELOPMENT.equalsIgnoreCase(this.active);
  }

  public Boolean isProdProfile() {
    return PROFILE_PRODUCTION.equalsIgnoreCase(this.active);
  }
}
