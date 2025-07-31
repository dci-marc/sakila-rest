package org.dcistudent.sakilarest.models.responses.domain;

import org.dcistudent.sakilarest.models.responses.StaffResponse;
import org.jetbrains.annotations.NotNull;

public record StoresResponse(
    @NotNull String lastUpdate,
    @NotNull StaffResponse staff
) implements DomainResponse {
}
