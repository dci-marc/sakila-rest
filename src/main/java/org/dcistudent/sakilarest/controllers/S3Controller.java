package org.dcistudent.sakilarest.controllers;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.dcistudent.sakilarest.factories.ResponseFactory;
import org.dcistudent.sakilarest.interfaces.models.responses.shared.ResponsePayload;
import org.dcistudent.sakilarest.models.requests.s3.S3FileRequest;
import org.dcistudent.sakilarest.models.responses.domain.DirectoryResponse;
import org.dcistudent.sakilarest.models.responses.shared.EmptyResponse;
import org.dcistudent.sakilarest.models.responses.shared.Response;
import org.dcistudent.sakilarest.services.S3Service;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.NoSuchElementException;
import java.util.Objects;

@RestController
@RequestMapping("/s3")
public class S3Controller {

  private final @NotNull S3Service service;

  public S3Controller(@NotNull S3Service service) {
    this.service = service;
  }

  @GetMapping("/files/{path}")
  @ApiResponses(
      value = {
          @ApiResponse(
              responseCode = "200",
              description = "Successfully fetched files from the specified directory.",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = DirectoryResponse.class)
              )
          ),
          @ApiResponse(
              responseCode = "400",
              description = "Directory not found or invalid request.",
              content = @Content(
                  mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                  schema = @Schema(implementation = EmptyResponse.class)
              )
          )
      }
  )
  public @NotNull ResponseEntity<Response<ResponsePayload>> getList(@NotNull @PathVariable String path) {
    try {
      return ResponseEntity.ok(
          ResponseFactory.create(
              HttpStatus.OK,
              "s3:files:fetch:success",
              this.service.getList(path)
          ));
    } catch (NoSuchElementException e) {
      return ResponseEntity
          .badRequest()
          .contentType(MediaType.APPLICATION_PROBLEM_JSON)
          .body(ResponseFactory.create(
              HttpStatus.BAD_REQUEST,
              "s3:files:fetch:not.found",
              EmptyResponse.INSTANCE
          ));
    }
  }

  @GetMapping("/files/{path}/download")
  @ApiResponses(
      value = {
          @ApiResponse(
              responseCode = "200",
              description = "File downloaded successfully.",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = DirectoryResponse.class)
              )
          ),
          @ApiResponse(
              responseCode = "400",
              description = "File not found or invalid request.",
              content = @Content(
                  mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                  schema = @Schema(implementation = EmptyResponse.class)
              )
          )
      }
  )
  public @NotNull ResponseEntity<Response<ResponsePayload>> get(@NotNull @PathVariable String path)
      throws IOException {
    try {
      var file = this.service.get(path);
      return ResponseEntity.ok(
          ResponseFactory.create(
              HttpStatus.OK,
              "s3:files:download:success",
              file
          )
      );
    } catch (NoSuchElementException e) {
      return ResponseEntity
          .badRequest()
          .contentType(MediaType.APPLICATION_PROBLEM_JSON)
          .body(
              ResponseFactory.create(
                  HttpStatus.BAD_REQUEST,
                  "s3:files:download:not.found",
                  EmptyResponse.INSTANCE
              )
          );
    }
  }

  @DeleteMapping("/files/{path}")
  @ApiResponses(
      value = {
          @ApiResponse(
              responseCode = "200",
              description = "File deleted successfully.",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = EmptyResponse.class)
              )
          ),
          @ApiResponse(
              responseCode = "400",
              description = "File not found or invalid request.",
              content = @Content(
                  mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                  schema = @Schema(implementation = EmptyResponse.class)
              )
          )
      }
  )
  public @NotNull ResponseEntity<Response<ResponsePayload>> delete(@NotNull @PathVariable String path) {
    try {
      return ResponseEntity.ok(
          ResponseFactory.create(
              HttpStatus.OK,
              "s3:files:delete:success",
              this.service.delete(path)
          ));
    } catch (NoSuchElementException e) {
      return ResponseEntity
          .badRequest()
          .contentType(MediaType.APPLICATION_PROBLEM_JSON)
          .body(ResponseFactory.create(
              HttpStatus.BAD_REQUEST,
              "s3:files:delete:not.found",
              EmptyResponse.INSTANCE
          ));
    }
  }

  @PutMapping("/files")
  @ApiResponses(
      value = {
          @ApiResponse(
              responseCode = "200",
              description = "File uploaded successfully.",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = EmptyResponse.class)
              )
          ),
          @ApiResponse(
              responseCode = "400",
              description = "Invalid file upload request.",
              content = @Content(
                  mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                  schema = @Schema(implementation = EmptyResponse.class)
              )
          )
      }
  )
  public @NotNull ResponseEntity<Response<ResponsePayload>> putFile(
      @NotNull @RequestParam("file") MultipartFile file,
      @NotNull @RequestParam("filePath") String filePath,
      @NotNull @RequestParam("fileName") String fileName
  ) {
    try {
      S3FileRequest request = new S3FileRequest.Builder()
          .setFilePath(filePath)
          .setFileName(fileName)
          .setContentType(Objects.requireNonNull(file.getContentType()))
          .setBase64Content(Base64.getEncoder().encode(file.getBytes()))
          .build();

      return ResponseEntity.ok(
          ResponseFactory.create(
              HttpStatus.OK,
              "s3:files:put:success",
              this.service.put(request)
          ));
    } catch (IllegalArgumentException e) {
      return ResponseEntity
          .badRequest()
          .contentType(MediaType.APPLICATION_PROBLEM_JSON)
          .body(ResponseFactory.create(
              HttpStatus.BAD_REQUEST,
              "s3:files:put:invalid.argument",
              EmptyResponse.INSTANCE
          ));
    } catch (IllegalStateException | IOException e) {
      return ResponseEntity
          .badRequest()
          .contentType(MediaType.APPLICATION_PROBLEM_JSON)
          .body(ResponseFactory.create(
              HttpStatus.BAD_REQUEST,
              "s3:files:put:invalid.request",
              EmptyResponse.INSTANCE
          ));
    }
  }
}
