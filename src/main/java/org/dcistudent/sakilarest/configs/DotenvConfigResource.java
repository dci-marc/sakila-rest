package org.dcistudent.sakilarest.configs;

import org.jetbrains.annotations.NotNull;
import org.springframework.boot.context.config.ConfigDataResource;

public class DotenvConfigResource extends ConfigDataResource {

  private final @NotNull String directory;
  private final @NotNull String filename;

  public DotenvConfigResource(@NotNull String directory, @NotNull String filename) {
    this.directory = directory;
    this.filename = filename;
  }

  public @NotNull String getDirectory() {
    return this.directory;
  }

  public @NotNull String getFilename() {
    return this.filename;
  }
}
