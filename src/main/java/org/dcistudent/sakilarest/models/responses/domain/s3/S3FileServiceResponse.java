package org.dcistudent.sakilarest.models.responses.domain.s3;

import org.jetbrains.annotations.NotNull;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

public class S3FileServiceResponse {

  private GetObjectResponse response = null;
  private byte @NotNull [] content = new byte[0];

  public S3FileServiceResponse(@NotNull Builder builder) {
    this.response = builder.response;
    this.content = builder.content;
  }

  public @NotNull GetObjectResponse getResponse() {
    return this.response;
  }

  public byte @NotNull [] getContent() {
    return this.content;
  }

  public static class Builder {
    private GetObjectResponse response = null;
    private byte @NotNull [] content = new byte[0];

    public @NotNull Builder setResponse(@NotNull GetObjectResponse response) {
      this.response = response;
      return this;
    }

    public @NotNull Builder setContent(byte @NotNull [] content) {
      this.content = content;
      return this;
    }

    public @NotNull S3FileServiceResponse build() {
      return new S3FileServiceResponse(this);
    }
  }
}
