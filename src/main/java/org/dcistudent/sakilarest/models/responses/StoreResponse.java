package org.dcistudent.sakilarest.models.responses;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public record StoreResponse(
    @NotNull String lastUpdate,
    @NotNull StaffResponse staff,
    @NotNull List<CustomerResponse> customers,
    @NotNull List<FilmResponse> films
) implements DomainResponse {
}
