package org.dcistudent.sakilarest.interfaces.services;

import org.dcistudent.sakilarest.models.requests.discord.Embed;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface DiscordServiceInterface {

  void ok(@NotNull List<Embed> fields);

  void error(@NotNull List<Embed> fields);
}
