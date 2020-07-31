package com.spring.jpa.criteriaquery.service.impl;

import com.spring.jpa.criteriaquery.model.AddressSearchDTO;
import com.spring.jpa.criteriaquery.model.entity.Address;
import com.spring.jpa.criteriaquery.repository.AddressRepository;
import com.spring.jpa.criteriaquery.repository.AddressSpecifications;
import com.spring.jpa.criteriaquery.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressService {
    private AddressRepository addressRepository;
    private AddressSpecifications addressSpecifications;

    @Override
    public List<Address> findAllBySearchableFields(AddressSearchDTO addressSearchDTO) {
        return this.addressRepository.findAll(this.addressSpecifications.filterAddressDetails(addressSearchDTO));
    }
}
