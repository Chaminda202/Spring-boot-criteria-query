package com.spring.jpa.criteriaquery.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserSearchDTO {
    private String search;
    private String email;
    private String firstName;
    private String lastName;
    private String street;
    private String city;
}
