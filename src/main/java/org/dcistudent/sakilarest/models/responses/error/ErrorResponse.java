package org.dcistudent.sakilarest.models.responses.error;

import org.dcistudent.sakilarest.models.responses.ResponsePayload;
import org.jetbrains.annotations.NotNull;

public record ErrorResponse(@NotNull String message) implements ResponsePayload {
}
