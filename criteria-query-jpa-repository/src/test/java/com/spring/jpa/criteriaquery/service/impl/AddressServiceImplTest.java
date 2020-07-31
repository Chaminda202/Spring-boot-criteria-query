package com.spring.jpa.criteriaquery.service.impl;

import com.spring.jpa.criteriaquery.model.AddressSearchDTO;
import com.spring.jpa.criteriaquery.model.entity.Address;
import com.spring.jpa.criteriaquery.service.AddressService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AddressServiceImplTest {
    @Autowired
    private AddressService addressService;

    @Test
    @Order(1)
    void testFindAllBySearchableFields(){
        AddressSearchDTO addressSearchDTO = AddressSearchDTO.builder()
                .city("Buda")
                .build();

        List<Address> addressList = this.addressService.findAllBySearchableFields(addressSearchDTO);
        assertNotNull(addressList);
    }
}
