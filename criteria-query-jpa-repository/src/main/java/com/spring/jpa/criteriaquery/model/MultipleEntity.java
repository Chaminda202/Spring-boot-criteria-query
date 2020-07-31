package com.spring.jpa.criteriaquery.model;

import com.spring.jpa.criteriaquery.model.entity.Employee;
import com.spring.jpa.criteriaquery.model.entity.Partner;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class MultipleEntity {
    private List<Employee> employeeList;
    private Employee employee;
    private Partner partner;
    private Employee employee2;
}
