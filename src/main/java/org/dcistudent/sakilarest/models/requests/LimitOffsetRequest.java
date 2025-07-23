package org.dcistudent.sakilarest.models.requests;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
public class LimitOffsetRequest {
    @NotNull
    @Min(10)
    private Integer limit = 10;

    @NotNull
    @Min(0)
    private Integer offset = 0;
}