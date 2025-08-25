package org.dcistudent.sakilarest.configs;

import org.jetbrains.annotations.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "discord")
public final class DiscordConfig {

  private @NotNull String name;
  private @NotNull String webhookUrl;

  public DiscordConfig() {
    this.name = "";
    this.webhookUrl = "";
  }

  public @NotNull String getName() {
    return this.name;
  }

  public void setName(@NotNull String name) {
    this.name = name;
  }

  public @NotNull String getWebhookUrl() {
    return this.webhookUrl;
  }

  public void setWebhookUrl(@NotNull String webhookUrl) {
    this.webhookUrl = webhookUrl;
  }
}
