package org.dcistudent.sakilarest.models.requests.shared.discord;

import org.dcistudent.sakilarest.interfaces.models.responses.shared.Buildable;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public final class Discord {

  private final @NotNull String username;
  private final @NotNull List<Embed> embeds;

  public Discord(@NotNull Builder builder) {
    this.username = builder.username;
    this.embeds = builder.embeds;
  }

  public @NotNull String getUsername() {
    return this.username;
  }

  public @NotNull List<Embed> getEmbeds() {
    return this.embeds;
  }

  public static final class Builder implements Buildable<Discord> {
    private @NotNull String username = "";
    private @NotNull List<Embed> embeds = List.of();

    public @NotNull Builder setUsername(@NotNull String username) {
      this.username = username;
      return this;
    }

    public @NotNull Builder setEmbeds(@NotNull List<Embed> embeds) {
      this.embeds = embeds;
      return this;
    }

    public @NotNull Discord build() {
      return new Discord(this);
    }
  }
}
