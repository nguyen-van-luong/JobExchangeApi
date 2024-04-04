package com.fjs.jobexchange.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignInRequest {
    @NotBlank(message = "Tài khoản không được để trống")
    String username;
    @NotBlank(message = "Mật khẩu không được để trống")
    String password;
}
