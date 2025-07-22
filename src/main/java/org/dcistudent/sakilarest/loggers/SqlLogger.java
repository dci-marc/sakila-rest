package org.dcistudent.sakilarest.loggers;

import org.dcistudent.sakilarest.managers.LogManager;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class SqlLogger {

  private final @NotNull LogManager logManager;

  public SqlLogger(@NotNull LogManager logManager) {
    this.logManager = logManager;
  }

  public void logDebug(@NotNull String message) {
    this.logManager.log(Level.DEBUG.get(), message);
  }

  public void logInfo(@NotNull String message) {
    this.logManager.log(Level.INFO.get(), message);
  }

  public void logWarn(@NotNull String message) {
    this.logManager.log(Level.WARN.get(), message);
  }

  public void logError(@NotNull String message) {
    this.logManager.log(Level.ERROR.get(), message);
  }


  public void logFatal(@NotNull String message) {
    this.logManager.log(Level.FATAL.get(), message);
  }

  public enum Level {
    DEBUG(0),
    INFO(1),
    WARN(2),
    ERROR(3),
    FATAL(4);

    private final int value;

    Level(int value) {
      this.value = value;
    }

    public int get() {
      return this.value;
    }
  }
}

