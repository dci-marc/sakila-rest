package org.dcistudent.sakilarest.services;

import org.dcistudent.sakilarest.configs.S3Config;
import org.dcistudent.sakilarest.models.requests.s3.S3FileServiceRequest;
import org.dcistudent.sakilarest.models.responses.domain.s3.S3FileServiceResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

import java.io.IOException;
import java.io.InputStream;

@Service
public final class S3ClientService {

  public static final @NotNull String HEADER_FILENAME = "x-amz-meta-filename";

  private final @NotNull S3Config config;
  private final @NotNull S3Client client;

  public S3ClientService(@NotNull S3Config config) {
    this.config = config;
    this.client = S3Client.builder()
        .region(config.getRegion())
        .credentialsProvider(() -> AwsBasicCredentials.create(
            config.getAccessKey(), config.getSecretKey())
        )
        .endpointOverride(config.getUrl())
        .forcePathStyle(true)
        .build();
  }

  public boolean upload(@NotNull String path, @NotNull InputStream inputStream, long contentLength) {
    S3FileServiceRequest request = new S3FileServiceRequest.Builder()
        .setBucket(this.config.getBucket())
        .setPath(path)
        .setInputStream(inputStream)
        .setContentLength(contentLength)
        .build();

    return this.client
        .putObject(request.put(), request.body())
        .sdkHttpResponse()
        .isSuccessful();
  }

  public @NotNull S3FileServiceResponse download(@NotNull String path) throws IOException {
    S3FileServiceRequest request = new S3FileServiceRequest.Builder()
        .setBucket(this.config.getBucket())
        .setPath(path)
        .build();

    try (ResponseInputStream<GetObjectResponse> response = this.client.getObject(request.get())) {
      return new S3FileServiceResponse.Builder()
          .setResponse(response.response())
          .setContent(response.readAllBytes())
          .build();
    } catch (IOException e) {
      throw new IOException("Failed to read S3 object content", e);
    }
  }

  public boolean delete(@NotNull String path) {
    S3FileServiceRequest request = new S3FileServiceRequest.Builder()
        .setBucket(this.config.getBucket())
        .setPath(path)
        .build();

    return this.client.deleteObject(request.delete()).sdkHttpResponse().isSuccessful();
  }
}
