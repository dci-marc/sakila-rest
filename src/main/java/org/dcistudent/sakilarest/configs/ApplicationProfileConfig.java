package org.dcistudent.sakilarest.configs;

import org.jetbrains.annotations.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.profiles")
public final class ApplicationProfileConfig {

  private @NotNull String active;
  public static final @NotNull String PROFILE_DEVELOPMENT;
  public static final @NotNull String PROFILE_PRODUCTION;

  static {
    PROFILE_DEVELOPMENT = "dev";
    PROFILE_PRODUCTION = "prod";
  }

  public ApplicationProfileConfig() {
    this.active = "";
  }

  public @NotNull String getActive() {
    return this.active;
  }

  public void setActive(@NotNull String active) {
    this.active = active;
  }

  public Boolean isDevProfile() {
    return PROFILE_DEVELOPMENT.equalsIgnoreCase(this.active);
  }

  public Boolean isProdProfile() {
    return PROFILE_PRODUCTION.equalsIgnoreCase(this.active);
  }
}
