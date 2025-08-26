package org.dcistudent.sakilarest.services.shared;

import com.bugsnag.Bugsnag;
import org.dcistudent.sakilarest.configs.DiscordConfig;
import org.dcistudent.sakilarest.interfaces.services.shared.DiscordServiceInterface;
import org.dcistudent.sakilarest.models.requests.shared.discord.Discord;
import org.dcistudent.sakilarest.models.requests.shared.discord.Embed;
import org.dcistudent.sakilarest.models.requests.shared.discord.Field;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@ConditionalOnProperty(name = "discord.webhook-url", havingValue = "", matchIfMissing = false)
public final class DiscordService implements DiscordServiceInterface {

  private final @NotNull DiscordConfig config;
  private final @NotNull RestTemplate template;
  private final @NotNull Bugsnag bugsnag;

  public DiscordService(@NotNull DiscordConfig config, @NotNull RestTemplate template, @NotNull Bugsnag bugsnag) {
    this.config = config;
    this.template = template;
    this.bugsnag = bugsnag;
  }

  public void ok(@NotNull String message, @NotNull String description) {
    this.ok(message, description, List.of());
  }

  public void ok(@NotNull String message, @NotNull String description, @NotNull List<Field> fields) {
    this.send(message, description, fields, 0x33EE33);
  }

  public void error(@NotNull String message, @NotNull String description) {
    this.error(message, description, List.of());
  }

  public void error(@NotNull String message, @NotNull String description, @NotNull List<Field> fields) {
    this.send(message, description, fields, 0xEE3333);
  }

  private void send(
      @NotNull String message,
      @NotNull String description,
      @NotNull List<Field> fields,
      @NotNull Integer color
  ) {
    Discord discord = new Discord.Builder()
        .setUsername(this.config.getName())
        .setEmbeds(
            List.of(new Embed.Builder()
                .setTitle(message)
                .setDescription(description)
                .setFields(fields)
                .build()
            )
        )
        .build()
        .setFieldsColor(color);

    CompletableFuture.runAsync(() -> this.template.postForEntity(this.config.getWebhookUrl(), discord, String.class))
        .exceptionally(throwable -> {
          this.bugsnag.notify(throwable);
          return null;
        });
  }
}
