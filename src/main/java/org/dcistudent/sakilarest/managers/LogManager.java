package org.dcistudent.sakilarest.managers;

import org.dcistudent.sakilarest.repositories.LogRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class LogManager {

  @NotNull LogRepository logRepository;

  public LogManager(@NotNull LogRepository logRepository) {
    this.logRepository = logRepository;
  }

  @Transactional
  public void log(int level, @NotNull String message) {
    this.logRepository.insert(
        level,
        LocalDateTime.now().toString(),
        message
    );
  }
}
