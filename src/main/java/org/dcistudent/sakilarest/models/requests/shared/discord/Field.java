package org.dcistudent.sakilarest.models.requests.shared.discord;

import org.dcistudent.sakilarest.interfaces.models.responses.shared.Buildable;
import org.jetbrains.annotations.NotNull;

public final class Field {

  private final @NotNull String name;
  private final @NotNull String value;
  private final boolean inline;

  public Field(@NotNull Builder builder) {
    this.name = builder.name;
    this.value = builder.value;
    this.inline = builder.inline;
  }

  public @NotNull String getName() {
    return this.name;
  }

  public @NotNull String getValue() {
    return this.value;
  }

  public boolean isInline() {
    return this.inline;
  }

  public static final class Builder implements Buildable<Field> {
    private @NotNull String name = "";
    private @NotNull String value = "";
    private boolean inline = true;

    public @NotNull Builder setName(@NotNull String name) {
      this.name = name;
      return this;
    }

    public @NotNull Builder setValue(@NotNull String value) {
      this.value = value;
      return this;
    }

    public @NotNull Builder setInline(boolean inline) {
      this.inline = inline;
      return this;
    }

    public @NotNull Field build() {
      return new Field(this);
    }
  }
}
