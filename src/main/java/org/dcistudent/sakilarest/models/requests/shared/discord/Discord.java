package org.dcistudent.sakilarest.models.requests.shared.discord;

import org.dcistudent.sakilarest.interfaces.models.responses.shared.Buildable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public final class Discord {

  private final @NotNull String username;
  private final @NotNull List<Embed> embeds;
  private @NotNull Integer fieldsColor;

  public Discord(@NotNull Builder builder) {
    this.username = builder.username;
    this.embeds = builder.embeds;
    this.fieldsColor = 0;
  }

  public @NotNull String getUsername() {
    return this.username;
  }

  public @NotNull List<Embed> getEmbeds() {
    return this.embeds;
  }

  public @NotNull Integer getFieldsColor() {
    return this.fieldsColor;
  }

  public @NotNull Discord setFieldsColor(@NotNull Integer color) {
    this.fieldsColor = color;
    return this;
  }

  public static final class Builder implements Buildable<Discord> {
    private @NotNull String username = "";
    private @NotNull List<Embed> embeds = new ArrayList<>();

    public @NotNull Builder setUsername(@NotNull String username) {
      this.username = username;
      return this;
    }

    public @NotNull Builder setEmbeds(@NotNull List<Embed> embeds) {
      embeds.forEach(this::addEmbed);
      return this;
    }

    private @NotNull Builder addEmbed(@NotNull Embed embed) {
      this.embeds.add(embed);
      return this;
    }

    public @NotNull Discord build() {
      return new Discord(this);
    }
  }
}
