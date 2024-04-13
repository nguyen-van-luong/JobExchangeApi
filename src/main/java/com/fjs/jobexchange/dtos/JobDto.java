package com.fjs.jobexchange.dtos;

import com.fjs.jobexchange.models.Address;
import com.fjs.jobexchange.models.Employer;
import com.fjs.jobexchange.models.IndustrySpecialization;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotNull(message = "Hạn nộp hồ sơ không được để trống")
    private Date duration;

    @NotBlank(message = "Tiêu đề không được để trống")
    private String title;

    private Integer ageFrom;

    private Integer ageTo;

    private Integer salaryFrom;

    private Integer salaryTo;

    private Boolean sex;

    private Integer quantity;

    private String degree;

    private String workingForm;

    @NotNull(message = "experience không được để trống")
    private String experience;

    @NotBlank(message = "description không được để trống")
    private String description;
    @NotBlank(message = "requirement không được để trống")
    private String requirement;

    @Size(min = 1, message = "")
    private List<AddressDto> addresses;

    private List<IndustrySpecializationDto> industrySpecializations;

    @NotNull(message = "isPrivate không được để trống")
    private boolean isPrivate;

}
