package org.dcistudent.sakilarest.models.responses.domain.s3.directories;

import org.dcistudent.sakilarest.interfaces.models.responses.domain.DomainResponse;
import org.dcistudent.sakilarest.models.responses.domain.s3.attributes.*;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;

public final class File implements DomainResponse {

  private final @NotNull Name name;
  private final @NotNull Size size;
  private final @NotNull Mime mime;
  private byte @NotNull [] base64Content = new byte[0];
  private final @NotNull Permission permission;
  private final @NotNull Modified modifiedAt;
  private final @NotNull Created createdAt;

  public File(@NotNull Builder builder) {
    this.name = new Name(builder.name);
    this.size = new Size(builder.size);
    this.mime = new Mime(builder.mime);
    this.base64Content = builder.base64Content;
    this.permission = new Permission(builder.readable, builder.writable, builder.deletable);
    this.modifiedAt = new Modified(builder.modifiedAt);
    this.createdAt = new Created(builder.createdAt);
  }

  public @NotNull Name getName() {
    return this.name;
  }

  public @NotNull Size getSize() {
    return this.size;
  }

  public @NotNull Mime getMime() {
    return this.mime;
  }

  public byte @NotNull [] getBase64Content() {
    return this.base64Content;
  }

  public @NotNull Permission getPermission() {
    return this.permission;
  }

  public @NotNull Modified getModifiedAt() {
    return this.modifiedAt;
  }

  public @NotNull Created getCreatedAt() {
    return this.createdAt;
  }

  public static class Builder {
    private @NotNull String name = "";
    private @NotNull Long size = 0L;
    private @NotNull String mime = "";
    private byte @NotNull [] base64Content = new byte[0];
    private boolean readable = false;
    private boolean writable = false;
    private boolean deletable = false;
    private @NotNull Instant modifiedAt = Instant.now();
    private @NotNull Instant createdAt = Instant.now();

    public @NotNull Builder setName(@NotNull String name) {
      this.name = name;
      return this;
    }

    public @NotNull Builder setSize(@NotNull Long size) {
      this.size = size;
      return this;
    }

    public @NotNull Builder setMime(@NotNull String mime) {
      this.mime = mime;
      return this;
    }

    public @NotNull Builder setBase64Content(byte @NotNull [] base64Content) {
      this.base64Content = base64Content;
      return this;
    }

    public @NotNull Builder setReadable(boolean readable) {
      this.readable = readable;
      return this;
    }

    public @NotNull Builder setWritable(boolean writable) {
      this.writable = writable;
      return this;
    }

    public @NotNull Builder setDeletable(boolean deletable) {
      this.deletable = deletable;
      return this;
    }

    public @NotNull Builder setModifiedAt(@NotNull Instant modifiedAt) {
      this.modifiedAt = modifiedAt;
      return this;
    }

    public @NotNull Builder setCreatedAt(@NotNull Instant createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    public @NotNull File build() {
      return new File(this);
    }
  }
}
