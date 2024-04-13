package com.fjs.jobexchange.dtos;

import com.fjs.jobexchange.models.IndustrySpecialization;
import com.fjs.jobexchange.models.Province;
import com.fjs.jobexchange.models.Skill;
import com.fjs.jobexchange.models.Student;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CVDto {

    @NotBlank(message = "Họ và tên không được để trống")
    private String fullname;

    @NotNull(message = "birthday không được để trống")
    private Date birthday;

    @NotBlank(message = "Email không được để trống")
    private String email;

    @NotNull(message = "province không được để trống")
    private Province province;

    @NotBlank(message = "address không được để trống")
    private String address;

    private Boolean sex;

    @NotBlank(message = "phoneNumber không được để trống")
    private String phoneNumber;

    private String positionCurrent;

    private String positionWant;

    @NotBlank(message = "degree không được để trống")
    private String degree;

    private String experience;

    private Integer salaryWant;

    private String workingForm;

    private List<SkillDto> skills;

    @Size(min = 1)
    private List<IndustrySpecializationDto> industrySpecializations;

    private String description;

    private String workExperience;

    private Integer quantity;

    @NotNull
    private boolean isPrivate;

}
