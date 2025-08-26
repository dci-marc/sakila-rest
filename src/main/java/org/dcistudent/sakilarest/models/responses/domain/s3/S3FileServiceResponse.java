package org.dcistudent.sakilarest.models.responses.domain.s3;

import org.dcistudent.sakilarest.interfaces.models.responses.shared.Buildable;
import org.jetbrains.annotations.NotNull;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

import java.util.Base64;

public final class S3FileServiceResponse {

  private final @NotNull GetObjectResponse response;
  private final @NotNull String content;

  public S3FileServiceResponse(@NotNull Builder builder) {
    this.response = builder.response;
    if (builder.content.isEmpty()) {
      throw new IllegalArgumentException("Content cannot be empty");
    }
    this.content = builder.content;
  }

  public @NotNull GetObjectResponse getResponse() {
    return this.response;
  }

  public @NotNull String getContent() {
    return this.content;
  }

  public static final class Builder implements Buildable<S3FileServiceResponse> {
    private @NotNull GetObjectResponse response = GetObjectResponse.builder().build();
    private @NotNull String content = "";

    public @NotNull Builder setResponse(@NotNull GetObjectResponse response) {
      this.response = response;
      return this;
    }

    public @NotNull Builder setContent(byte @NotNull [] content) {
      this.content = Base64.getEncoder().encodeToString(content);
      return this;
    }

    public @NotNull S3FileServiceResponse build() {
      return new S3FileServiceResponse(this);
    }
  }
}
