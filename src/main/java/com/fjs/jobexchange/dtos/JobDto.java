package com.fjs.jobexchange.dtos;

import com.fjs.jobexchange.models.Address;
import com.fjs.jobexchange.models.Employer;
import com.fjs.jobexchange.models.IndustrySpecialization;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobDto {

    @NotNull(message = "employer không được để trống")
    private Integer employerId;

    @NotNull(message = "duration không được để trống")
    private Date duration;

    @NotBlank(message = "title không được để trống")
    private String title;

    @NotNull(message = "ageFrom không được để trống")
    private Integer ageFrom;

    @NotNull(message = "ageTo không được để trống")
    private Integer ageTo;

    @NotNull(message = "salary không được để trống")
    private Integer salary;

    @NotNull(message = "experience không được để trống")
    private Integer experience;

    @NotBlank(message = "description không được để trống")
    private String description;
    @NotBlank(message = "requirement không được để trống")
    private String requirement;


    private List<AddressDto> addresses;

    private List<IndustrySpecializationDto> industrySpecializations;

    @NotNull(message = "isPrivate không được để trống")
    private boolean isPrivate;

}
