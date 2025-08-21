package org.dcistudent.sakilarest.models.responses.domain.s3;

import org.dcistudent.sakilarest.models.responses.domain.DomainResponse;
import org.dcistudent.sakilarest.models.responses.domain.s3.directories.File;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class Directory implements DomainResponse {

  private @NotNull String name;
  private @NotNull String path;
  private @NotNull Set<File> files = new HashSet<>();
  private int totalFiles = 0;

  public Directory(@NotNull String name, @NotNull String path) {
    this.name = name;
    this.path = path;
  }

  public boolean isEmpty() {
    return this.files.isEmpty();
  }

  public void addFile(@NotNull File file) {
    this.files.add(file);
    this.totalFiles++;
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
}
