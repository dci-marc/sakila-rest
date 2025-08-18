package org.dcistudent.sakilarest.configs;

import com.bugsnag.Bugsnag;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BugsnagConfig {

  @Value("${bugsnag.key:}")
  private @NotNull String bugsnagApiKey;

  @Bean
  public @NotNull Bugsnag bugsnag() {
    return new Bugsnag(this.bugsnagApiKey);
  }
}
