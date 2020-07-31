package com.spring.jpa.criteriaquery.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressSearchDTO {
    private String street;
    private String city;
    private String email;
    private String firstName;
    private String lastName;
}
