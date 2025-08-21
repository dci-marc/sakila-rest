package org.dcistudent.sakilarest.services;

import org.dcistudent.sakilarest.models.responses.domain.s3.Directory;
import org.dcistudent.sakilarest.models.responses.domain.s3.directories.File;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public final class S3Service {

  public Directory getFiles(@NotNull String directoryName) {
    Directory dir = new Directory(directoryName, "/");
    List<String> fileNames = List.of("file1.txt", "file2.jpg", "file3.pdf");

    fileNames.forEach(fileName -> dir.addFile(
        new File.Builder()
            .setName(fileName)
            .setSize(1024)
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
}
