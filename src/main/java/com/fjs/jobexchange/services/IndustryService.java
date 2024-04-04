package com.fjs.jobexchange.services;

import com.fjs.jobexchange.dtos.ApiException;
import com.fjs.jobexchange.dtos.IndustryDto;
import com.fjs.jobexchange.models.Industry;
import com.fjs.jobexchange.models.Province;
import com.fjs.jobexchange.repositories.IndustryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IndustryService {
    private final IndustryRepository industryRepository;
    public Industry findById(Integer id){
        return industryRepository.findById(id)
                .orElseThrow(() -> new ApiException("Ngành không tồn tại!",HttpStatus.BAD_REQUEST));
    }

    @Transactional
    public Industry create(IndustryDto industryDto) {
        Industry industry = Industry
                .builder()
                .name(industryDto.getName())
                .build();
        try {
            industry = industryRepository.save(industry);
            return industry;
        } catch (Exception e) {
            throw new ApiException("Có lỗi xảy ra khi tạo Industry. Vui lòng thử lại!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
