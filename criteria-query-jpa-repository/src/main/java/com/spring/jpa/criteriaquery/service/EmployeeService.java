package com.spring.jpa.criteriaquery.service;

import com.spring.jpa.criteriaquery.model.EmployeeSearchDTO;
import com.spring.jpa.criteriaquery.model.MultipleEntity;
import com.spring.jpa.criteriaquery.model.entity.Employee;

import java.math.BigDecimal;
import java.util.List;

public interface EmployeeService {
    MultipleEntity saveMultipleEntity(MultipleEntity multipleEntity);
    List<Employee> findAll();
    List<Employee> eagerFetchInQuery();
    List<Employee> findAllByNameOrSalary(String name, BigDecimal salary);
    List<Employee> findBySearchableFields(EmployeeSearchDTO employeeSearchDTO);
    List<Employee> findBySearchableFieldsInJoinTable(EmployeeSearchDTO employeeSearchDTO);
}
