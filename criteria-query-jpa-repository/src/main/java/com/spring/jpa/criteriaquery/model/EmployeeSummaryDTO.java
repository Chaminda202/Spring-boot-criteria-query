package com.spring.jpa.criteriaquery.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@Getter
public class EmployeeSummaryDTO {
    private Long totalCountOfEmployees;
    private Long totalDistinctCountOfEmployees;
    private BigDecimal sumSalaryOfEmployees;
    private Double averageSalaryOfEmployees;
    private BigDecimal maxSalaryOfEmployees;
}
