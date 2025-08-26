package org.dcistudent.sakilarest.normalizers;

import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.nio.file.Paths;

public final class PathNormalizer {

  private PathNormalizer() {
  }

  public static @NotNull String getFilename(@NotNull String path) {
    Path normalizedPath = Paths.get(path).normalize();

    if (normalizedPath.isAbsolute() || normalizedPath.toString().contains("..")) {
      throw new SecurityException("Illegal path specified.");
    }

    if (normalizedPath.toFile().isDirectory()) {
      throw new SecurityException("Path is a directory.");
    }

    return normalizedPath.toFile().getName();
  }
}
