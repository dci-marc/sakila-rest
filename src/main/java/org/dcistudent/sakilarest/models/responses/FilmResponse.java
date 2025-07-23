package org.dcistudent.sakilarest.models.responses;

import org.jetbrains.annotations.NotNull;

public record FilmResponse(
    @NotNull String title,
    @NotNull String description,
    int releaseYear,
    int length,
    @NotNull String rating,
    @NotNull String specialFeatures,
    @NotNull String lastUpdate
) {
}
