package org.dcistudent.sakilarest.services.shared;

import org.dcistudent.sakilarest.configs.DiscordConfig;
import org.dcistudent.sakilarest.models.requests.shared.discord.Discord;
import org.dcistudent.sakilarest.models.requests.shared.discord.Embed;
import org.dcistudent.sakilarest.models.requests.shared.discord.Field;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public final class DiscordService {

  private final @NotNull DiscordConfig config;
  private final @NotNull RestTemplate template;

  public DiscordService(@NotNull DiscordConfig config, @NotNull RestTemplate template) {
    this.config = config;
    this.template = template;
  }

  public void ok(@NotNull String message, @NotNull String description) {
    CompletableFuture.runAsync(() -> this.template.postForEntity(
        this.config.getWebhookUrl(),
        new Discord.Builder()
            .setUsername(this.config.getName())
            .setEmbeds(
                List.of(new Embed.Builder()
                    .setTitle(message)
                    .setDescription(description)
                    .setColor(0x33EE33)
                    .build()
                )
            )
            .build(),
        String.class));
  }

  public void ok(@NotNull String message, @NotNull String description, @NotNull List<Field> fields) {
    CompletableFuture.runAsync(() -> this.template.postForEntity(
        this.config.getWebhookUrl(),
        new Discord.Builder()
            .setUsername(this.config.getName())
            .setEmbeds(
                List.of(new Embed.Builder()
                    .setTitle(message)
                    .setDescription(description)
                    .setColor(0x33EE33)
                    .setFields(fields)
                    .build()
                )
            )
            .build(),
        String.class));
  }

  public void error(@NotNull String message, @NotNull String description) {
    CompletableFuture.runAsync(() -> this.template.postForEntity(
        this.config.getWebhookUrl(),
        new Discord.Builder()
            .setUsername(this.config.getName())
            .setEmbeds(
                List.of(new Embed.Builder()
                    .setTitle(message)
                    .setDescription(description)
                    .setColor(0xEE3333)
                    .setFields(List.of(
                        new Field.Builder()
                            .setName("Name")
                            .setValue("Value")
                            .setInline(true)
                            .build()
                    ))
                    .build()
                )
            )
            .build(),
        String.class));
  }

  public void error(@NotNull String message, @NotNull String description, @NotNull List<Field> fields) {
    CompletableFuture.runAsync(() -> this.template.postForEntity(
        this.config.getWebhookUrl(),
        new Discord.Builder()
            .setUsername(this.config.getName())
            .setEmbeds(
                List.of(new Embed.Builder()
                    .setTitle(message)
                    .setDescription(description)
                    .setColor(0xEE3333)
                    .setFields(fields)
                    .build()
                )
            )
            .build(),
        String.class));
  }
}
