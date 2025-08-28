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
 * (e.g., "../etc/passwd") by validating the raw input *before* normalization.
 */
public final class PathNormalizer {

  private PathNormalizer() {
    // Utility class: prevent instantiation
  }

  public static @NotNull String getDirectory(@NotNull String userPath) {
    try {
      // Step 1: Validate raw user input string before normalization
      // -----------------------------------------------------------
      // Reject Unix-style absolute paths ("/etc/passwd").
      if (userPath.startsWith("/") || userPath.startsWith("\\")) {
        throw new SecurityException("Absolute paths are not allowed.");
      }

      // Reject Windows absolute paths like "C:\foo" or "C:/foo"
      // Regex explanation:
      //   ^[a-zA-Z]:     → drive letter with colon
      //   [\\\\/]        → must be followed by either "\" or "/"
      if (userPath.matches("^[a-zA-Z]:[\\\\/].*")) {
        throw new SecurityException("Absolute Windows paths are not allowed.");
      }

      // Explicitly reject any unnormalized ".." segment.
      // Example: "../../../etc/passwd"
      if (userPath.contains("..")) {
        throw new SecurityException("Parent directory traversal is not allowed.");
      }

      // Step 2: Convert to Path and normalize
      // -----------------------------------------------------------
      // Only after we know the input contains no traversal attempts,
      // we normalize to clean up redundant "./" segments.
      Path path = Paths.get(userPath).normalize();

      // Step 3: Extract the directory safely
      // -----------------------------------------------------------
      Path parent = Optional
          .ofNullable(path.getParent())
          .orElseThrow(() -> new SecurityException("No directory specified."));

      return parent.toString();

    } catch (InvalidPathException e) {
      // Happens if the user input contains invalid characters
      // (e.g., NUL bytes, malformed UTF-8).
      throw new SecurityException("Invalid path.", e);
    }
  }

  /**
   * Extracts a sanitized filename from user input.
   *
   * @param userPath raw user-supplied path string (potentially malicious)
   * @return sanitized filename string
   * @throws SecurityException if the input path is invalid, absolute,
   *                           attempts directory traversal, or results in no filename
   */
  public static @NotNull String getFilename(@NotNull String userPath) {
    try {
      // Step 1: Validate raw user input string before normalization
      // -----------------------------------------------------------
      // Reject Unix-style absolute paths ("/etc/passwd").
      if (userPath.startsWith("/") || userPath.startsWith("\\")) {
        throw new SecurityException("Absolute paths are not allowed.");
      }

      // Reject Windows absolute paths like "C:\foo" or "C:/foo"
      // Regex explanation:
      //   ^[a-zA-Z]:     → drive letter with colon
      //   [\\\\/]        → must be followed by either "\" or "/"
      if (userPath.matches("^[a-zA-Z]:[\\\\/].*")) {
        throw new SecurityException("Absolute Windows paths are not allowed.");
      }

      // Explicitly reject any unnormalized ".." segment.
      // Example: "../../../etc/passwd"
      if (userPath.contains("..")) {
        throw new SecurityException("Parent directory traversal is not allowed.");
      }

      // Step 2: Convert to Path and normalize
      // -----------------------------------------------------------
      // Only after we know the input contains no traversal attempts,
      // we normalize to clean up redundant "./" segments.
      Path path = Paths.get(userPath).normalize();

      // Step 3: Extract the filename safely
      // -----------------------------------------------------------
      Path filename = Optional
          .ofNullable(path.getFileName())
          .orElseThrow(() -> new SecurityException("No filename specified."));

      if (filename.toString().isEmpty()) {
        throw new SecurityException("Filename is empty.");
      }

      return filename.toString();

    } catch (InvalidPathException e) {
      // Happens if the user input contains invalid characters
      // (e.g., NUL bytes, malformed UTF-8).
      throw new SecurityException("Invalid path.", e);
    }
  }
}
