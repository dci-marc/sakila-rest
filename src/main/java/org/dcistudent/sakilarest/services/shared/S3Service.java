package org.dcistudent.sakilarest.services.shared;

import org.dcistudent.sakilarest.exceptions.shared.NotFoundException;
import org.dcistudent.sakilarest.models.requests.domain.s3.S3FileRequest;
import org.dcistudent.sakilarest.models.requests.domain.s3.S3FileServiceRequest;
import org.dcistudent.sakilarest.models.responses.domain.s3.Directory;
import org.dcistudent.sakilarest.models.responses.domain.s3.S3FileServiceResponse;
import org.dcistudent.sakilarest.models.responses.domain.s3.directories.File;
import org.dcistudent.sakilarest.models.responses.shared.EmptyResponse;
import org.dcistudent.sakilarest.models.responses.shared.SuccessResponse;
import org.dcistudent.sakilarest.normalizers.PathNormalizer;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.Instant;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    byte[] content = Base64.getDecoder().decode(request.getBase64Content());

    return new SuccessResponse.Builder().setSuccess(
        this.service.upload(
            String.format("%s/%s", request.getFilePath(), request.getFileName()),
            new ByteArrayInputStream(content),
            request.getContentType().get(),
            content.length
        )
    ).build();
  }

  public @NotNull File get(@NotNull String path) throws IOException {
    S3FileServiceResponse response = this.service.download(path);
    if (response.getContent().isEmpty()) {
      throw new NotFoundException("s3:file:download:not.found", EmptyResponse.INSTANCE);
    }

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

  public @NotNull SuccessResponse delete(@NotNull String path) {
    if (!this.service.delete(path)) {
      throw new NotFoundException("s3:file:delete:not.found", EmptyResponse.INSTANCE);
    }

    return new SuccessResponse.Builder().setSuccess(true).build();
  }
}
