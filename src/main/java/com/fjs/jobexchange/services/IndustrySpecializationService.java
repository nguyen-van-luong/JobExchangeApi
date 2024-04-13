package com.fjs.jobexchange.services;

import com.fjs.jobexchange.dtos.AddressDto;
import com.fjs.jobexchange.dtos.IndustryDto;
import com.fjs.jobexchange.dtos.IndustrySpecializationDto;
import com.fjs.jobexchange.models.*;
import com.fjs.jobexchange.repositories.IndustryRepository;
import com.fjs.jobexchange.repositories.IndustrySpecializationRepository;
import com.fjs.jobexchange.repositories.SpecializationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class IndustrySpecializationService {
    private final IndustrySpecializationRepository industrySpecializationRepository;
    private final IndustryService industryService;
    private final SpecializationService specializationService;
    @Transactional
    public List<IndustrySpecialization> creates(List<IndustrySpecializationDto> industrySpecializationDtos) {

        List<IndustrySpecialization> industrySpecializationList = new ArrayList<>();
        for (IndustrySpecializationDto dto : industrySpecializationDtos) {
            IndustrySpecialization industrySpecialization = new IndustrySpecialization();
            Industry industry= industryService.findById(dto.getIndustry().getId());
            Specialization specialization= specializationService.findById(dto.getSpecialization().getId());
            industrySpecialization.setIndustry(industry);
            industrySpecialization.setSpecialization(specialization);
            industrySpecializationList.add(industrySpecialization);
        }
        return industrySpecializationRepository.saveAll(industrySpecializationList);

    }
}
