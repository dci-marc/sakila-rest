package org.dcistudent.sakilarest.interfaces.services.shared;

import org.dcistudent.sakilarest.models.requests.shared.discord.Field;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface DiscordServiceInterface {
  void ok(@NotNull String message, @NotNull String description);

  void ok(@NotNull String message, @NotNull String description, @NotNull List<Field> fields);

  void error(@NotNull String message, @NotNull String description);

  void error(@NotNull String message, @NotNull String description, @NotNull List<Field> fields);
}
