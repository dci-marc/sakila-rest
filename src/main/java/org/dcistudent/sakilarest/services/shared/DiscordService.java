package org.dcistudent.sakilarest.services.shared;

import com.bugsnag.Bugsnag;
import org.dcistudent.sakilarest.configs.DiscordConfig;
import org.dcistudent.sakilarest.interfaces.services.shared.DiscordServiceInterface;
import org.dcistudent.sakilarest.models.requests.shared.discord.Discord;
import org.dcistudent.sakilarest.models.requests.shared.discord.Embed;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@ConditionalOnProperty(name = "discord.webhook-url", matchIfMissing = false)
public final class DiscordService implements DiscordServiceInterface {

  private final @NotNull DiscordConfig config;
  private final @NotNull RestClient restClient;
  private final @NotNull Bugsnag bugsnag;

  public DiscordService(@NotNull DiscordConfig config, @NotNull Bugsnag bugsnag) {
    this.config = config;
    this.restClient = RestClient.builder()
        .baseUrl(this.config.getWebhookUrl())
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .build();
    this.bugsnag = bugsnag;
  }

  public void ok(@NotNull List<Embed> embeds) {
    this.send(embeds, "33EE33");
  }

  public void error(@NotNull List<Embed> embeds) {
    this.send(embeds, "EE3333");
  }

  private void send(@NotNull List<Embed> embeds, @NotNull String color) {
    List<Embed> coloredEmbeds = embeds.stream().map(
        embed -> new Embed.Builder()
            .setTitle(embed.getTitle())
            .setDescription(embed.getDescription())
            .setColor(color)
            .setFields(embed.getFields())
            .build()
    ).toList();

    Discord discord = new Discord.Builder()
        .setUsername(this.config.getName())
        .setEmbeds(coloredEmbeds)
        .build();

    CompletableFuture.runAsync(() -> this.restClient.post()
        .body(discord)
        .retrieve()
        .toBodilessEntity()
    ).exceptionally(throwable -> {
      this.bugsnag.notify(throwable);
      return null;
    });
  }
}
