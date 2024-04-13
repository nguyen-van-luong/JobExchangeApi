package com.fjs.jobexchange.services;

import com.fjs.jobexchange.dtos.AddressDto;
import com.fjs.jobexchange.dtos.ApiException;
import com.fjs.jobexchange.dtos.IndustryDto;
import com.fjs.jobexchange.dtos.SkillDto;
import com.fjs.jobexchange.models.Address;
import com.fjs.jobexchange.models.Industry;
import com.fjs.jobexchange.models.Province;
import com.fjs.jobexchange.models.Skill;
import com.fjs.jobexchange.repositories.IndustryRepository;
import com.fjs.jobexchange.repositories.SkillRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class SkillService {
    private final SkillRepository skillRepository;

    public List<Skill> findAll(){
        return skillRepository.findAll();
    }
    public Skill findById(Integer id){
        return skillRepository.findById(id)
                .orElseThrow(() -> new ApiException(" Kỹ năng không tồn tại!", HttpStatus.BAD_REQUEST));
    }

    @Transactional
    public List<Skill> creates(List<SkillDto> skillDtos) {

        List<Skill> skills = new ArrayList<>();
        for (SkillDto dto : skillDtos) {
            Skill skill = new Skill();
            skill.setName(dto.getName());
            skills.add(skill);
        }
        return skillRepository.saveAll(skills);
    }

}
