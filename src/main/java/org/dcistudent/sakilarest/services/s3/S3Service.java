package org.dcistudent.sakilarest.services.s3;

import org.dcistudent.sakilarest.exceptions.NotFoundException;
import org.dcistudent.sakilarest.factories.responses.S3ResponseFactory;
import org.dcistudent.sakilarest.models.requests.s3.S3FileRequest;
import org.dcistudent.sakilarest.models.responses.EmptyResponse;
import org.dcistudent.sakilarest.models.responses.SuccessResponse;
import org.dcistudent.sakilarest.models.responses.s3.S3DirectoryServiceResponse;
import org.dcistudent.sakilarest.models.responses.s3.S3FileServiceResponse;
import org.dcistudent.sakilarest.models.responses.vfs.Directory;
import org.dcistudent.sakilarest.models.responses.vfs.directories.File;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;

@Service
public final class S3Service {

  private final @NotNull S3ClientService service;

  public S3Service(@NotNull S3ClientService service) {
    this.service = service;
  }

  public @NotNull Directory getList(@NotNull String path) {
    S3DirectoryServiceResponse response = this.service.list(path);
    if (response.getFiles().isEmpty()) {
      throw new NotFoundException("s3:files:list:not.found", EmptyResponse.INSTANCE);
    }

    return S3ResponseFactory.create(path, response);
  }

  public @NotNull SuccessResponse put(@NotNull S3FileRequest request) {
    byte[] content = Base64.getDecoder().decode(request.getBase64Content());

    return new SuccessResponse.Builder().setSuccess(
        this.service.upload(
            request.getFilePath().isBlank() ?
                request.getFileName() :
                String.format("%s/%s", request.getFilePath(), request.getFileName()),
            new ByteArrayInputStream(content),
            request.getContentType().get(),
            content.length
        )
    ).build();
  }

  public @NotNull File get(@NotNull String path) throws IOException {
    S3FileServiceResponse response = this.service.download(path);
    if (response.getResponse().contentLength() == 0) {
      throw new NotFoundException("s3:file:download:not.found", EmptyResponse.INSTANCE);
    }

    return S3ResponseFactory.create(path, response);
  }

  public @NotNull SuccessResponse delete(@NotNull String path) {
    if (!this.service.delete(path)) {
      throw new NotFoundException("s3:file:delete:not.found", EmptyResponse.INSTANCE);
    }

    return new SuccessResponse.Builder().setSuccess(true).build();
  }
}
