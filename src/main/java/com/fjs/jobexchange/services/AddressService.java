package com.fjs.jobexchange.services;

import com.fjs.jobexchange.dtos.AddressDto;
import com.fjs.jobexchange.dtos.ApiException;
import com.fjs.jobexchange.dtos.IndustryDto;
import com.fjs.jobexchange.models.Address;
import com.fjs.jobexchange.models.Industry;
import com.fjs.jobexchange.models.Province;
import com.fjs.jobexchange.repositories.AddressRepository;
import com.fjs.jobexchange.repositories.IndustryRepository;
import com.fjs.jobexchange.repositories.ProvinceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;
    private final ProvinceService provinceService;
    @Transactional
    public List<Address> creates(List<AddressDto> addressListDtos) {

        List<Address> addresses = new ArrayList<>();
        for (AddressDto dto : addressListDtos) {
            Address address = new Address();
            Province province= provinceService.findById(dto.getProvince().getId());
            address.setProvince(province);
            address.setAddress(dto.getAddress());
            addresses.add(address);
        }
        return addressRepository.saveAll(addresses);
    }
}
