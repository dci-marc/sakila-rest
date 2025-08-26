package org.dcistudent.sakilarest.normalizers;

import org.dcistudent.sakilarest.exceptions.shared.ForbiddenException;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.nio.file.Paths;

public final class PathNormalizer {

  private PathNormalizer() {
  }

  public static @NotNull String getFilename(@NotNull String path) {
    Path normalizedPath = Paths.get(path).normalize();

    if (normalizedPath.startsWith("..") || normalizedPath.isAbsolute() || normalizedPath.toString().contains("../")) {
      throw new ForbiddenException("Illegal path specified.");
    }

    return normalizedPath.getFileName().toString();
  }
}
