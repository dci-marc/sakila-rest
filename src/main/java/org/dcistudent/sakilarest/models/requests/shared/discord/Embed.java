package org.dcistudent.sakilarest.models.requests.shared.discord;

import org.dcistudent.sakilarest.interfaces.models.responses.shared.Buildable;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public final class Embed {

  private final @NotNull String title;
  private final @NotNull String description;
  private final @NotNull Integer color;
  private final @NotNull List<Field> fields;

  public Embed(@NotNull Builder builder) {
    this.title = builder.title;
    this.description = builder.description;
    this.color = builder.color;
    this.fields = builder.fields;
  }

  public @NotNull String getTitle() {
    return this.title;
  }

  public @NotNull String getDescription() {
    return this.description;
  }

  public @NotNull Integer getColor() {
    return this.color;
  }

  public @NotNull List<Field> getFields() {
    return this.fields;
  }

  public static final class Builder implements Buildable<Embed> {
    private @NotNull String title = "";
    private @NotNull String description = "";
    private @NotNull Integer color = 0;
    private @NotNull List<Field> fields = List.of();

    public @NotNull Builder setTitle(@NotNull String title) {
      this.title = title;
      return this;
    }

    public @NotNull Builder setDescription(@NotNull String description) {
      this.description = description;
      return this;
    }

    public @NotNull Builder setColor(@NotNull Integer color) {
      this.color = color;
      return this;
    }

    public @NotNull Builder setFields(@NotNull List<Field> fields) {
      this.fields = fields;
      return this;
    }

    public @NotNull Embed build() {
      return new Embed(this);
    }
  }
}
