package com.fjs.jobexchange.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SkillDto {
    @NotBlank(message = " Tên kĩ năng không được để trống")
    private String name;
}
