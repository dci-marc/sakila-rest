package org.dcistudent.sakilarest.normalizers;

import org.jetbrains.annotations.NotNull;

import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

/**
 * Utility class for extracting a safe filename from a user-supplied path string.
 * <p>
 * This implementation hardens against directory traversal attacks
 * (e.g., "../etc/passwd") by performing strict syntactic checks.
 */
public final class PathNormalizer {

  private PathNormalizer() {
    // Utility class: prevent instantiation
  }

  /**
   * Extracts a normalized and safe filename from user input.
   *
   * @param userPath raw user-supplied path string (potentially malicious)
   * @return sanitized filename string
   * @throws SecurityException if the input path is invalid, absolute,
   *                           attempts directory traversal, or results in no filename
   */
  public static @NotNull String getFilename(@NotNull String userPath) {
    try {
      // Parse into a Path object and normalize it.
      // Normalization resolves "." and ".." segments syntactically.
      // Example: "subdir/../file.txt" → "file.txt"
      Path path = Paths.get(userPath).normalize();

      // Reject absolute paths (e.g., "/etc/passwd" or "C:\windows\system32").
      // We only want relative filenames under application control.
      if (path.isAbsolute()) {
        throw new SecurityException("Absolute paths are not allowed.");
      }

      // After normalization, no ".." should remain.
      // If it does, the user attempted directory traversal.
      if (path.toString().contains("..")) {
        throw new SecurityException("Parent directory traversal is not allowed.");
      }

      // Extract only the last component (the filename).
      // Example: "foo/bar.txt" → "bar.txt"
      Path filename = Optional
          .ofNullable(path.getFileName())
          .orElseThrow(() -> new SecurityException("No filename specified."));

      // Ensure the filename is not empty (e.g., user input "foo/")
      if (filename.toString().isEmpty()) {
        throw new SecurityException("Filename is empty.");
      }

      // Return the sanitized filename as plain string.
      return filename.toString();

    } catch (InvalidPathException e) {
      // Happens if the user input contains invalid characters
      // (e.g., NUL bytes, illegal UTF-8 sequences).
      throw new SecurityException("Invalid path.", e);
    }
  }
}
