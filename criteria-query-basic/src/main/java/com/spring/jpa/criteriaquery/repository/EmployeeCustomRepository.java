package com.spring.jpa.criteriaquery.repository;

import com.spring.jpa.criteriaquery.model.EmployeeDTO;
import com.spring.jpa.criteriaquery.model.entity.Employee;

import java.util.List;

public interface EmployeeCustomRepository {
    void saveEmployee(Employee employee);
    List<Employee> all();
    Employee findByName(String name);
    List<String> getEmployeeNames();
    void deleteEmployee(Employee employee);
    /**
     * Select only specific attributes (name, salary, email) and needs to do some conversion
     * to convert Object[] array to Employee entity
     * @return list of Employee
     */
    List<Employee> selectMultipleAttributes();
    /**
     * Select only specific attributes (name, salary, email) and does not do conversion
     * for that should be have a constructor which is accepted those three attribute in Employee entity
     * @return list of Employee
     */
    List<Employee> selectMultipleAttributesWithoutConversion();
    List<EmployeeDTO> selectMultipleAttributesAndMapDTO();
    List<Employee> selectMultipleAttributesUsingTupleQuery();
}
