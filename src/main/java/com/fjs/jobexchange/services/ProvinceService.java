package com.fjs.jobexchange.services;

import com.fjs.jobexchange.dtos.ApiException;
import com.fjs.jobexchange.dtos.ProvinceDto;
import com.fjs.jobexchange.models.Province;
import com.fjs.jobexchange.repositories.ProvinceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProvinceService {
    private final ProvinceRepository provinceRepository;

    public Province findById(Integer id){
        return provinceRepository.findById(id)
                .orElseThrow(() -> new ApiException("Tỉnh/Thành phố không tồn tại!",HttpStatus.BAD_REQUEST));
    }

    @Transactional
    public Province create(ProvinceDto provinceDto) {
        Province province = Province
                .builder()
                .name(provinceDto.getName())
                .build();
        try {
            province = provinceRepository.save(province);
            return province;
        } catch (Exception e) {
            throw new ApiException("Có lỗi xảy ra khi tạo Province. Vui lòng thử lại!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
