package com.spring.jpa.criteriaquery.service.impl;

import com.spring.jpa.criteriaquery.model.EmployeeSearchDTO;
import com.spring.jpa.criteriaquery.model.MultipleEntity;
import com.spring.jpa.criteriaquery.model.entity.Employee;
import com.spring.jpa.criteriaquery.repository.EmployeeRepository;
import com.spring.jpa.criteriaquery.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static com.spring.jpa.criteriaquery.repository.EmployeeSpecifications.dynamicQuery;
import static com.spring.jpa.criteriaquery.repository.EmployeeSpecifications.dynamicQueryWithJoin;
import static com.spring.jpa.criteriaquery.repository.EmployeeSpecifications.eagerFetch;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
    @Override
    public MultipleEntity saveMultipleEntity(MultipleEntity multipleEntity) {
        return MultipleEntity.builder()
                .employeeList(this.employeeRepository.saveAll(multipleEntity.getEmployeeList()))
                .build();
    }

    @Override
    public List<Employee> findAll() {
        return this.employeeRepository.findAll();
    }

    @Override
    public List<Employee> eagerFetchInQuery() {
        return this.employeeRepository.findAll(eagerFetch());
    }

    @Override
    public List<Employee> findAllByNameOrSalary(String name, BigDecimal salary) {
        return this.employeeRepository.findAllByNameOrSalary(name, salary);
    }

    @Override
    public List<Employee> findBySearchableFields(EmployeeSearchDTO employeeSearchDTO) {
        return this.employeeRepository.findAll(dynamicQuery(employeeSearchDTO));
    }

    @Override
    public List<Employee> findBySearchableFieldsInJoinTable(EmployeeSearchDTO employeeSearchDTO) {
        return this.employeeRepository.findAll(dynamicQueryWithJoin(employeeSearchDTO));
    }
}
