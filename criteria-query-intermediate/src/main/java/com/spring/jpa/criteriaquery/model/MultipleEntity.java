package com.spring.jpa.criteriaquery.model;

import com.spring.jpa.criteriaquery.model.entity.Employee;
import com.spring.jpa.criteriaquery.model.entity.Partner;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MultipleEntity {
    private Employee employee;
    private Partner partner;
    private Employee employee2;
}
