package org.dcistudent.sakilarest.normalizers;

import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.nio.file.Paths;

public final class PathNormalizer {

  private PathNormalizer() {
  }

  public static @NotNull String getFilename(@NotNull String userPath) {
    Path path = Paths.get(userPath);

    if (path.isAbsolute() || path.toString().contains("..")) {
      throw new SecurityException("Illegal path specified.");
    }

    if (path.toFile().isDirectory()) {
      throw new SecurityException("Path is a directory.");
    }

    return path.normalize().toFile().getName();
  }
}
