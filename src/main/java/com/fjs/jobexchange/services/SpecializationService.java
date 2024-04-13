package com.fjs.jobexchange.services;

import com.fjs.jobexchange.dtos.ApiException;
import com.fjs.jobexchange.dtos.ProvinceDto;
import com.fjs.jobexchange.dtos.SpecializationDto;
import com.fjs.jobexchange.models.Province;
import com.fjs.jobexchange.models.Specialization;
import com.fjs.jobexchange.repositories.ProvinceRepository;
import com.fjs.jobexchange.repositories.SpecializationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SpecializationService {
    private final SpecializationRepository specializationRepository;

    public Specialization findById(Integer id){
        return specializationRepository.findById(id)
                .orElseThrow(() -> new ApiException("Chuyên ngành không tồn tại!",HttpStatus.BAD_REQUEST));
    }

    public List<Specialization> findByIndustryId(Integer id) {
        return specializationRepository.findByIndustryId(id)
                .orElseThrow(() -> new ApiException("Chuyên ngành không tồn tại!",HttpStatus.BAD_REQUEST));
    }

    @Transactional
    public Specialization create(SpecializationDto specializationDto) {
        Specialization  specialization = Specialization
                .builder()
                .name(specializationDto.getName())
                .industryId(specializationDto.getIndustryId())
                .updatedAt(new Date().toInstant())
                .build();
        try {
            specialization = specializationRepository.save(specialization);
            return specialization;
        } catch (Exception e) {
            throw new ApiException("Có lỗi xảy ra khi tạo Specialization . Vui lòng thử lại!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
