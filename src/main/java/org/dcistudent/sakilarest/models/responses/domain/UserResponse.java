package org.dcistudent.sakilarest.models.responses.domain;

import org.jetbrains.annotations.NotNull;

public record UserResponse(@NotNull String email) implements DomainResponse {
}
