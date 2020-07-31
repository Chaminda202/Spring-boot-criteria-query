package com.spring.jpa.criteriaquery.model;

import com.spring.jpa.criteriaquery.enumeration.PhoneType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class EmployeeSearchDTO {
    private String name;
    private String email;
    private LocalDate dob;
    private BigDecimal salary;
    private String number;
    private PhoneType phoneType;
}
