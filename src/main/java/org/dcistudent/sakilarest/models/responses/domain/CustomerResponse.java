package org.dcistudent.sakilarest.models.responses.domain;

import org.jetbrains.annotations.NotNull;

public record CustomerResponse(
    @NotNull String firstName,
    @NotNull String lastName,
    @NotNull String email,
    @NotNull Boolean active,
    @NotNull String createDate,
    @NotNull String lastUpdate
) implements DomainResponse {
}
