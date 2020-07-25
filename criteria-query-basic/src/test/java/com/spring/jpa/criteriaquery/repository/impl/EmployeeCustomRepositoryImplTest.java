package com.spring.jpa.criteriaquery.repository.impl;

import com.spring.jpa.criteriaquery.model.EmployeeDTO;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.spring.jpa.criteriaquery.model.entity.Employee;
import org.springframework.dao.EmptyResultDataAccessException;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmployeeCustomRepositoryImplTest {
    @Autowired
    private EmployeeCustomRepositoryImpl employeeCustomRepositoryImplUnderTest;

    @Test
    @Order(1)
    void testSaveEmployee() {
        // Setup
        final Employee employee = Employee.builder()
                .name("Test")
                .email("tet@gmail.com")
                .dob(LocalDate.of(1995, 1, 1))
                .salary(new BigDecimal("200.00"))
                .build();
        // Run the test
        employeeCustomRepositoryImplUnderTest.saveEmployee(employee);
    }

    @Test
    @Order(2)
    void testAll() {
        Employee expectedResult = Employee.builder()
                .name("Test")
                .email("tet@gmail.com")
                .dob(LocalDate.of(1995, 1, 1))
                .salary(new BigDecimal("200.00"))
                .build();
        List<Employee> employeeList = employeeCustomRepositoryImplUnderTest.all();
        assertNotNull(employeeList);
        assertEquals(1, employeeList.size());
        assertEquals(expectedResult.getName(), employeeList.get(0).getName());
        assertEquals(expectedResult.getEmail(), employeeList.get(0).getEmail());
        assertEquals(expectedResult.getDob(), employeeList.get(0).getDob());
        assertEquals(expectedResult.getSalary(), employeeList.get(0).getSalary());
    }

    @Test
    @Order(3)
    void testFindByName() {
        Employee expectedResult = Employee.builder()
                .name("Test")
                .email("tet@gmail.com")
                .dob(LocalDate.of(1995, 1, 1))
                .salary(new BigDecimal("200.00"))
                .build();
        Employee actualResult = employeeCustomRepositoryImplUnderTest.findByName(expectedResult.getName());
        assertNotNull(actualResult);
        assertEquals(expectedResult.getName(), actualResult.getName());
        assertEquals(expectedResult.getEmail(), actualResult.getEmail());
        assertEquals(expectedResult.getDob(), actualResult.getDob());
        assertEquals(expectedResult.getSalary(), actualResult.getSalary());
    }

    @Test
    @Order(4)
    void testGetEmployeeNames() {
        Employee employee = Employee.builder()
                .name("Test1")
                .email("tet1@gmail.com")
                .dob(LocalDate.of(1995, 1, 1))
                .salary(new BigDecimal("205.00"))
                .build();
        this.employeeCustomRepositoryImplUnderTest.saveEmployee(employee);
        List<String> expectedResult = Arrays.asList("Test", "Test1");

        List<String> actualResult = employeeCustomRepositoryImplUnderTest.getEmployeeNames();
        assertNotNull(actualResult);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    @Order(5)
    void testDeleteEmployee() {
        Employee employee = employeeCustomRepositoryImplUnderTest.findByName("Test1");
        //before delete the employee
        assertNotNull(employee);
        this.employeeCustomRepositoryImplUnderTest.deleteEmployee(employee);
        // after delete the employee
        assertThrows(EmptyResultDataAccessException.class, () -> employeeCustomRepositoryImplUnderTest.findByName("Test1")) ;
    }

    @Test
    @Order(6)
    void testSelectMultipleAttributes() {
        Employee expectedResult = Employee.builder()
                .name("Test")
                .email("tet@gmail.com")
                .dob(LocalDate.of(1995, 1, 1))
                .salary(new BigDecimal("200.00"))
                .build();

        List<Employee> actualResult = employeeCustomRepositoryImplUnderTest.selectMultipleAttributes();

        assertNotNull(actualResult);
        assertEquals(expectedResult.getName(), actualResult.get(0).getName());
        assertEquals(expectedResult.getEmail(), actualResult.get(0).getEmail());
        assertNull(actualResult.get(0).getDob());
        assertEquals(expectedResult.getSalary(), actualResult.get(0).getSalary());
    }

    @Test
    @Order(7)
    void testSelectMultipleAttributesWithoutConversion() {
        Employee expectedResult = Employee.builder()
                .name("Test")
                .email("tet@gmail.com")
                .dob(LocalDate.of(1995, 1, 1))
                .salary(new BigDecimal("200.00"))
                .build();

        List<Employee> actualResult = employeeCustomRepositoryImplUnderTest.selectMultipleAttributesWithoutConversion();

        assertNotNull(actualResult);
        assertEquals(expectedResult.getName(), actualResult.get(0).getName());
        assertEquals(expectedResult.getEmail(), actualResult.get(0).getEmail());
        assertNull(actualResult.get(0).getDob());
        assertEquals(expectedResult.getSalary(), actualResult.get(0).getSalary());
    }

    @Test
    @Order(8)
    void testSelectMultipleAttributesAndMapDTO() {
        EmployeeDTO expectedResult = EmployeeDTO.builder()
                .name("Test")
                .email("tet@gmail.com")
                .salary(new BigDecimal("200.00"))
                .build();

        List<EmployeeDTO> actualResult = employeeCustomRepositoryImplUnderTest.selectMultipleAttributesAndMapDTO();

        assertNotNull(actualResult);
        assertEquals(expectedResult.getName(), actualResult.get(0).getName());
        assertEquals(expectedResult.getEmail(), actualResult.get(0).getEmail());
        assertNull(actualResult.get(0).getDob());
        assertEquals(expectedResult.getSalary(), actualResult.get(0).getSalary());
    }

    @Test
    @Order(9)
    void testSelectMultipleAttributesUsingTupleQuery() {
        Employee expectedResult = Employee.builder()
                .name("Test")
                .email("tet@gmail.com")
                .dob(LocalDate.of(1995, 1, 1))
                .salary(new BigDecimal("200.00"))
                .build();

        List<Employee> actualResult = employeeCustomRepositoryImplUnderTest.selectMultipleAttributesUsingTupleQuery();

        assertNotNull(actualResult);
        assertEquals(expectedResult.getName(), actualResult.get(0).getName());
        assertEquals(expectedResult.getEmail(), actualResult.get(0).getEmail());
        assertNull(actualResult.get(0).getDob());
        assertEquals(expectedResult.getSalary(), actualResult.get(0).getSalary());
    }
}
