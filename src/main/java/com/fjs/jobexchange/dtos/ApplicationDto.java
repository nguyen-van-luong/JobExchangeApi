package com.fjs.jobexchange.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationDto {
    @NotNull(message = "jobId không được để trống")
    private Integer jobId;
    @NotNull(message = "cvId không được để trống")
    private Integer cvId;
    @NotBlank(message = "status không được để trống")
    private String status;

}
