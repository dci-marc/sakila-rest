package org.dcistudent.sakilarest.services.s3;

import org.dcistudent.sakilarest.configs.S3Config;
import org.dcistudent.sakilarest.models.requests.s3.S3FileServiceRequest;
import org.dcistudent.sakilarest.models.responses.s3.S3DirectoryServiceResponse;
import org.dcistudent.sakilarest.models.responses.s3.S3FileServiceResponse;
import org.dcistudent.sakilarest.normalizers.PathNormalizer;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@Service
public final class S3ClientService {

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

  public @NotNull S3DirectoryServiceResponse list(@NotNull String path) {
    path = PathNormalizer.getDirectory(path);
    ListObjectsV2Request request = ListObjectsV2Request.builder()
        .bucket(this.config.getBucket())
        .prefix(path)
        .build();

    S3DirectoryServiceResponse.Builder directory = new S3DirectoryServiceResponse.Builder();
    this.client.listObjectsV2(request)
        .contents()
        .forEach(s3Object -> {
          S3FileServiceResponse file = new S3FileServiceResponse.Builder()
              .setResponse(GetObjectResponse.builder()
                  .metadata(Map.of(
                      S3FileServiceRequest.METADATA_FILENAME_KEY, PathNormalizer.getFilename(s3Object.key()),
                      S3FileServiceRequest.METADATA_CONTENT_TYPE_KEY, "application/octet-stream"
                  ))
                  .contentLength(s3Object.size())
                  .lastModified(s3Object.lastModified())
                  .build())
              .setContent(new byte[0])
              .build();
          directory.addFile(file);
        });

    return directory.build();
  }

  public boolean upload(
      @NotNull String path,
      @NotNull InputStream inputStream,
      @NotNull String contentType,
      long contentLength
  ) {
    S3FileServiceRequest request = new S3FileServiceRequest.Builder()
        .setBucket(this.config.getBucket())
        .setPath(path)
        .setInputStream(inputStream)
        .setContentLength(contentLength)
        .setContentType(contentType)
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
