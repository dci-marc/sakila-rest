package org.dcistudent.sakilarest.services.shared;

import com.bugsnag.Bugsnag;
import org.dcistudent.sakilarest.configs.DiscordConfig;
import org.dcistudent.sakilarest.interfaces.services.shared.DiscordServiceInterface;
import org.dcistudent.sakilarest.models.requests.shared.discord.Discord;
import org.dcistudent.sakilarest.models.requests.shared.discord.Embed;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@ConditionalOnProperty(name = "discord.webhook-url", matchIfMissing = false)
public final class DiscordService implements DiscordServiceInterface {

  private final @NotNull DiscordConfig config;
  private final @NotNull RestTemplate template;
  private final @NotNull Bugsnag bugsnag;

  public DiscordService(@NotNull DiscordConfig config, @NotNull RestTemplate template, @NotNull Bugsnag bugsnag) {
    this.config = config;
    this.template = template;
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

    CompletableFuture.runAsync(() -> this.template.postForEntity(this.config.getWebhookUrl(), discord, String.class))
        .exceptionally(throwable -> {
          this.bugsnag.notify(throwable);
          return null;
        });
  }
}
