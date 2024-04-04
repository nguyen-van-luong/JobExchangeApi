package com.fjs.jobexchange.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpecializationDto {
    @NotNull
    private Integer industryId;

    @Size(max = 100)
    @NotNull
    private String name;
}
