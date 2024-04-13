package com.fjs.jobexchange.dtos;

import com.fjs.jobexchange.models.Province;
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
public class AddressDto {

    @NotNull(message = "Province không được để trống")
    private Province province;

    @Size(max = 200)
    private String address;
}
