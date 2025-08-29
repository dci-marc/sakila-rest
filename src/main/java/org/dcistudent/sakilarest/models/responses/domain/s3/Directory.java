package org.dcistudent.sakilarest.models.responses.domain.s3;

import org.dcistudent.sakilarest.interfaces.models.responses.domain.DomainResponse;
import org.dcistudent.sakilarest.models.responses.domain.s3.directories.File;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashSet;
import java.util.Set;

public final class Directory implements DomainResponse {

  private @NotNull String name;
  private @NotNull String path;
  private @NotNull Set<File> files;
  private int totalFiles;

  public Directory(@NotNull Builder builder) {
    this.name = builder.name;
    this.path = builder.path;
    this.files = builder.files;
    this.totalFiles = this.files.size();
  }

  public boolean isEmpty() {
    return this.files.isEmpty();
  }

  public @NotNull String getName() {
    return this.name;
  }

  public void setName(@NotNull String name) {
    this.name = name;
  }

  public @NotNull String getPath() {
    return this.path;
  }

  public void setPath(@NotNull String path) {
    this.path = path;
  }

  public @NotNull Set<File> getFiles() {
    return this.files;
  }

  public void setFiles(@NotNull Set<File> files) {
    this.files = files;
    this.totalFiles = files.size();
  }

  public int getTotalFiles() {
    return this.totalFiles;
  }

  public static final class Builder {
    private @NotNull String name = "";
    private @NotNull String path = "";
    private @NotNull Set<File> files = new LinkedHashSet<>();

    public @NotNull Builder setName(@NotNull String name) {
      this.name = name;
      return this;
    }

    public @NotNull Builder setPath(@NotNull String path) {
      this.path = path;
      return this;
    }

    public @NotNull Builder setFiles(@NotNull Set<File> files) {
      this.files = files;
      return this;
    }

    public @NotNull Directory build() {
      return new Directory(this);
    }
  }
}
