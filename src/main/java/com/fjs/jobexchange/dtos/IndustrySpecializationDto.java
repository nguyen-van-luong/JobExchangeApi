package com.fjs.jobexchange.dtos;

import com.fjs.jobexchange.models.Industry;
import com.fjs.jobexchange.models.Specialization;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IndustrySpecializationDto {
    @NotNull(message = "industry không được để trống")
    private Industry industry;
    @NotNull(message = "specialization không được để trống")
    private Specialization specialization;
}
