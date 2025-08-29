package org.dcistudent.sakilarest.factories.domain;

import org.dcistudent.sakilarest.models.requests.domain.s3.S3FileServiceRequest;
import org.dcistudent.sakilarest.models.responses.domain.fs.Directory;
import org.dcistudent.sakilarest.models.responses.domain.fs.directories.File;
import org.dcistudent.sakilarest.models.responses.domain.s3.S3DirectoryServiceResponse;
import org.dcistudent.sakilarest.models.responses.domain.s3.S3FileServiceResponse;
import org.dcistudent.sakilarest.normalizers.PathNormalizer;
import org.jetbrains.annotations.NotNull;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

import java.nio.file.Paths;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public final class S3ResponseFactory {

  private S3ResponseFactory() {
  }

  public static @NotNull File create(@NotNull String path, @NotNull S3FileServiceResponse response) {
    Map<String, String> metadata = response.getResponse().metadata();
    String filename = Optional
        .ofNullable(
            metadata.get(S3FileServiceRequest.METADATA_FILENAME_KEY)
        ).orElse(PathNormalizer.getFilename(path));

    return new File.Builder()
        .setName(filename)
        .setSize(response.getResponse().contentLength())
        .setMime(metadata.get(S3FileServiceRequest.METADATA_CONTENT_TYPE_KEY))
        .setBase64Content(response.getContent())
        .setReadable(true)
        .setWritable(true)
        .setDeletable(true)
        .setModifiedAt(response.getResponse().lastModified())
        .setCreatedAt(response.getResponse().lastModified())
        .build();
  }

  public static @NotNull Directory create(@NotNull String path, @NotNull S3DirectoryServiceResponse response) {
    return new Directory.Builder()
        .setName(Paths.get(path).getFileName().toString())
        .setPath(path)
        .setFiles(
            response.getFiles().stream()
                .map(s3FileServiceResponse -> {
                  GetObjectResponse objectResponse = s3FileServiceResponse.getResponse();
                  Map<String, String> metadata = objectResponse.metadata();
                  String filename = Optional
                      .ofNullable(
                          metadata.get(S3FileServiceRequest.METADATA_FILENAME_KEY)
                      ).orElse(PathNormalizer.getFilename(path));

                  return new File.Builder()
                      .setName(filename)
                      .setSize(objectResponse.contentLength())
                      .setMime(metadata.get(S3FileServiceRequest.METADATA_CONTENT_TYPE_KEY))
                      .setReadable(true)
                      .setWritable(true)
                      .setDeletable(true)
                      .setModifiedAt(objectResponse.lastModified())
                      .setCreatedAt(objectResponse.lastModified())
                      .setBase64Content("") // No content for directory listing
                      .build();
                })
                .collect(Collectors.toSet())
        ).build();
  }
}
