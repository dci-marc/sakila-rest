package org.dcistudent.sakilarest.configs;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigGuard {

  @Value("${server:application:name}")
  private @NotNull String serverApplicationName;

  @Value("${spring:datasource:url}")
  private @NotNull String springDatasourceUrl;

  @Value("${auth0:domain}")
  private @NotNull String auth0Domain;

  /**
   * Check if database system configuration was loaded.
   */
  @Value("${spring:flyway:locations}")
  private @NotNull String springFlywayLocations;

  @Bean
  @NotNull
  ApplicationRunner validateConfig() {
    return args -> {
      if (
          this.serverApplicationName.isBlank() ||
              this.springDatasourceUrl.isBlank() ||
              this.auth0Domain.isBlank() ||
              this.springFlywayLocations.isBlank()
      ) {
        throw new IllegalStateException(
            "Configuration properties 'server', 'spring', and 'auth0' must be set." +
                " Please check your application.properties or application.yml file."
        );
      }
    };
  }
}
