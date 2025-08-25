package org.dcistudent.sakilarest.services;

import org.dcistudent.sakilarest.models.requests.s3.S3FileRequest;
import org.dcistudent.sakilarest.models.responses.domain.s3.Directory;
import org.dcistudent.sakilarest.models.responses.domain.s3.S3FileServiceResponse;
import org.dcistudent.sakilarest.models.responses.domain.s3.directories.File;
import org.dcistudent.sakilarest.models.responses.shared.SuccessResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.Instant;
import java.util.List;

@Service
public final class S3Service {

  private final @NotNull S3ClientService service;

  public S3Service(@NotNull S3ClientService service) {
    this.service = service;
  }

  /**
   * Showcase method to simulate fetching files from a directory in S3.
   */
  public @NotNull Directory getList(@NotNull String directoryName) {
    Directory dir = new Directory(directoryName, "/");
    List<String> fileNames = List.of("file1.txt", "file2.jpg", "file3.pdf");

    fileNames.forEach(fileName -> dir.addFile(
        new File.Builder()
            .setName(fileName)
            .setSize(1024L)
            .setMime("application/octet-stream")
            .setReadable(true)
            .setWritable(true)
            .setDeletable(false)
            .setModifiedAt(Instant.now())
            .setCreatedAt(Instant.now())
            .build()
    ));

    return dir;
  }

  public @NotNull SuccessResponse put(@NotNull S3FileRequest request) {
    return new SuccessResponse.Builder().setSuccess(
        this.service.upload(
            String.format("%s/%s", request.getFilePath(), request.getFileName()),
            new ByteArrayInputStream(request.getBase64Content()),
            request.getBase64Content().length
        )
    ).build();
  }

  public @NotNull File get(@NotNull String path) throws IOException {
    S3FileServiceResponse response = this.service.download(path);

    return new File.Builder()
        .setName(String.valueOf(response.getResponse().metadata().get(S3ClientService.HEADER_FILENAME)))
        .setSize(response.getResponse().contentLength())
        .setMime(response.getResponse().contentType())
        .setBase64Content(response.getContent())
        .setReadable(true)
        .setWritable(true)
        .setDeletable(true)
        .setModifiedAt(response.getResponse().lastModified())
        .setCreatedAt(response.getResponse().lastModified())
        .build();
  }

  public @NotNull SuccessResponse delete(@NotNull String path) {
    return new SuccessResponse.Builder().setSuccess(this.service.delete(path)).build();
  }
}
