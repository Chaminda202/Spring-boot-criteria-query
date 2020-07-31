package com.spring.jpa.criteriaquery.service;

import com.spring.jpa.criteriaquery.model.AddressSearchDTO;
import com.spring.jpa.criteriaquery.model.entity.Address;

import java.util.List;

public interface AddressService {
    List<Address> findAllBySearchableFields(AddressSearchDTO addressSearchDTO);
}
