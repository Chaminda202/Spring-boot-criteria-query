package com.spring.jpa.criteriaquery.service.impl;

import com.spring.jpa.criteriaquery.enumeration.PhoneType;
import com.spring.jpa.criteriaquery.model.EmployeeSearchDTO;
import com.spring.jpa.criteriaquery.model.MultipleEntity;
import com.spring.jpa.criteriaquery.model.entity.Call;
import com.spring.jpa.criteriaquery.model.entity.Employee;
import com.spring.jpa.criteriaquery.model.entity.Phone;
import com.spring.jpa.criteriaquery.service.EmployeeService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmployeeServiceImplTest {
    @Autowired
    private EmployeeService employeeService;

    @Order(1)
    @Test
    void testTestSaveMultipleEntity() {
        // Setup
        Employee employee1 = Employee.builder()
                .name("Tan James")
                .email("james@gmail.com")
                .dob(LocalDate.of(1995, 1, 1))
                .salary(new BigDecimal("200.00"))
                .build();

        Employee employee2 = Employee.builder()
                .name("Taniya Hil")
                .email("taniya@gmail.com")
                .dob(LocalDate.of(1996, 1, 1))
                .salary(new BigDecimal("250.00"))
                .build();

        Phone phone1 = Phone.builder()
                .number("+94717345623")
                .phoneType(PhoneType.MOBILE)
                .employee(employee1)
                .build();
        // employee1.getPhones().add(phone1);

        Phone phone2 = Phone.builder()
                .number("+94113456789")
                .phoneType(PhoneType.LAND_LINE)
                .employee(employee1)
                .build();
        // employee1.getPhones().add(phone2);
        employee1.setPhones(Arrays.asList(phone1, phone2));

        Phone phone3 = Phone.builder()
                .number("+94777345456")
                .phoneType(PhoneType.MOBILE)
                .employee(employee2)
                .build();
        // employee2.getPhones().add(phone3);
        employee2.setPhones(Arrays.asList(phone3));

        Call call1 = Call.builder()
                .date(LocalDateTime.now())
                .duration(125)
                .phone(phone1)
                .build();
        // phone1.getCalls().add(call1);

        Call call2 = Call.builder()
                .date(LocalDateTime.now())
                .duration(189)
                .phone(phone1)
                .build();
        // phone1.getCalls().add(call2);
        phone1.setCalls(Arrays.asList(call1, call2));

        Call call3 = Call.builder()
                .date(LocalDateTime.now())
                .duration(340)
                .phone(phone2)
                .build();
        // phone2.getCalls().add(call3);
        phone2.setCalls(Arrays.asList(call3));

        Call call4 = Call.builder()
                .date(LocalDateTime.now())
                .duration(503)
                .phone(phone3)
                .build();
        // phone3.getCalls().add(call4);
        phone3.setCalls(Arrays.asList(call4));

        MultipleEntity entity = MultipleEntity.builder()
                .employeeList(Arrays.asList(employee1, employee2))
                .build();

        // Run the test
        MultipleEntity actualResult = this.employeeService.saveMultipleEntity(entity);

        // Verify the results
        assertNotNull(actualResult);
        assertEquals(employee1.getName(), actualResult.getEmployeeList().get(0).getName());
        assertEquals(employee1.getEmail(), actualResult.getEmployeeList().get(0).getEmail());
        assertEquals(employee1.getDob(), actualResult.getEmployeeList().get(0).getDob());
        assertEquals(employee1.getSalary(), actualResult.getEmployeeList().get(0).getSalary());

        assertEquals(employee2.getName(), actualResult.getEmployeeList().get(1).getName());
        assertEquals(employee2.getEmail(), actualResult.getEmployeeList().get(1).getEmail());
        assertEquals(employee2.getDob(), actualResult.getEmployeeList().get(1).getDob());
        assertEquals(employee2.getSalary(), actualResult.getEmployeeList().get(1).getSalary());
    }

    @Order(2)
    @Test
    void testFindAll() {
        Employee employee1 = Employee.builder()
                .name("Tan James")
                .email("james@gmail.com")
                .dob(LocalDate.of(1995, 1, 1))
                .salary(new BigDecimal("200.00"))
                .build();

        Employee employee2 = Employee.builder()
                .name("Taniya Hil")
                .email("taniya@gmail.com")
                .dob(LocalDate.of(1996, 1, 1))
                .salary(new BigDecimal("250.00"))
                .build();

        List<Employee> employeeList = this.employeeService.findAll();

        assertNotNull(employeeList);

        assertEquals(employee1.getName(), employeeList.get(0).getName());
        assertEquals(employee1.getEmail(), employeeList.get(0).getEmail());
        assertEquals(employee1.getDob(), employeeList.get(0).getDob());
        assertEquals(employee1.getSalary(), employeeList.get(0).getSalary());

        assertEquals(employee2.getName(), employeeList.get(1).getName());
        assertEquals(employee2.getEmail(), employeeList.get(1).getEmail());
        assertEquals(employee2.getDob(), employeeList.get(1).getDob());
        assertEquals(employee2.getSalary(), employeeList.get(1).getSalary());
    }

    @Order(3)
    @Test
    void testEagerFetchInQuery() {
        Employee employee1 = Employee.builder()
                .name("Tan James")
                .email("james@gmail.com")
                .dob(LocalDate.of(1995, 1, 1))
                .salary(new BigDecimal("200.00"))
                .build();

        Employee employee2 = Employee.builder()
                .name("Taniya Hil")
                .email("taniya@gmail.com")
                .dob(LocalDate.of(1996, 1, 1))
                .salary(new BigDecimal("250.00"))
                .build();

        Phone phone1 = Phone.builder()
                .number("+94717345623")
                .phoneType(PhoneType.MOBILE)
                .employee(employee1)
                .build();

        Phone phone2 = Phone.builder()
                .number("+94113456789")
                .phoneType(PhoneType.LAND_LINE)
                .employee(employee1)
                .build();

        Phone phone3 = Phone.builder()
                .number("+94777345456")
                .phoneType(PhoneType.MOBILE)
                .employee(employee2)
                .build();

        List<Employee> employeeList = this.employeeService.eagerFetchInQuery();
        assertNotNull(employeeList);

        assertEquals(employee1.getName(), employeeList.get(0).getName());
        assertEquals(employee1.getEmail(), employeeList.get(0).getEmail());
        assertEquals(employee1.getDob(), employeeList.get(0).getDob());
        assertEquals(employee1.getSalary(), employeeList.get(0).getSalary());
        assertNotNull(employeeList.get(0).getPhones());
        assertEquals(phone1.getNumber(), employeeList.get(0).getPhones().get(0).getNumber());
        assertEquals(phone1.getPhoneType(), employeeList.get(0).getPhones().get(0).getPhoneType());
        assertEquals(phone2.getNumber(), employeeList.get(0).getPhones().get(1).getNumber());
        assertEquals(phone2.getPhoneType(), employeeList.get(0).getPhones().get(1).getPhoneType());

        assertEquals(employee2.getName(), employeeList.get(1).getName());
        assertEquals(employee2.getEmail(), employeeList.get(1).getEmail());
        assertEquals(employee2.getDob(), employeeList.get(1).getDob());
        assertEquals(employee2.getSalary(), employeeList.get(1).getSalary());
        assertNotNull(employeeList.get(1).getPhones());
        assertEquals(phone3.getNumber(), employeeList.get(1).getPhones().get(0).getNumber());
        assertEquals(phone3.getPhoneType(), employeeList.get(1).getPhones().get(0).getPhoneType());
    }

    @Order(3)
    @Test
    void testFindAllByNameOrSalary() {
        Employee employee1 = Employee.builder()
                .name("Tan James")
                .email("james@gmail.com")
                .dob(LocalDate.of(1995, 1, 1))
                .salary(new BigDecimal("200.00"))
                .build();

        Employee employee2 = Employee.builder()
                .name("Taniya Hil")
                .email("taniya@gmail.com")
                .dob(LocalDate.of(1996, 1, 1))
                .salary(new BigDecimal("250.00"))
                .build();

        List<Employee> employeeList = this.employeeService.findAllByNameOrSalary(employee1.getName(), employee2.getSalary());
        assertNotNull(employeeList);

        assertEquals(employee1.getName(), employeeList.get(0).getName());
        assertEquals(employee1.getEmail(), employeeList.get(0).getEmail());
        assertEquals(employee1.getDob(), employeeList.get(0).getDob());
        assertEquals(employee1.getSalary(), employeeList.get(0).getSalary());

        assertEquals(employee2.getName(), employeeList.get(1).getName());
        assertEquals(employee2.getEmail(), employeeList.get(1).getEmail());
        assertEquals(employee2.getDob(), employeeList.get(1).getDob());
        assertEquals(employee2.getSalary(), employeeList.get(1).getSalary());

        List<Employee> employeeList1 = this.employeeService.findAllByNameOrSalary(employee1.getName(), new BigDecimal("20.00"));
        assertNotNull(employeeList);

        assertEquals(employee1.getName(), employeeList1.get(0).getName());
        assertEquals(employee1.getEmail(), employeeList1.get(0).getEmail());
        assertEquals(employee1.getDob(), employeeList1.get(0).getDob());
        assertEquals(employee1.getSalary(), employeeList1.get(0).getSalary());

    }

    @Order(4)
    @Test
    void testFindBySearchableFields() {
        Employee employee1 = Employee.builder()
                .name("Tan James")
                .email("james@gmail.com")
                .dob(LocalDate.of(1995, 1, 1))
                .salary(new BigDecimal("200.00"))
                .build();

        Employee employee2 = Employee.builder()
                .name("Taniya Hil")
                .email("taniya@gmail.com")
                .dob(LocalDate.of(1996, 1, 1))
                .salary(new BigDecimal("250.00"))
                .build();

        EmployeeSearchDTO employeeSearchDTO = EmployeeSearchDTO.builder()
                .name("Tan")
                .dob(employee1.getDob())
                .salary(employee1.getSalary())
                .build();

        List<Employee> employeeList = this.employeeService.findBySearchableFields(employeeSearchDTO);

        assertNotNull(employeeList);

        assertEquals(employee1.getName(), employeeList.get(0).getName());
        assertEquals(employee1.getEmail(), employeeList.get(0).getEmail());
        assertEquals(employee1.getDob(), employeeList.get(0).getDob());
        assertEquals(employee1.getSalary(), employeeList.get(0).getSalary());
    }

    @Order(5)
    @Test
    void testFindBySearchableFieldsInJoinTable() {
        Employee employee1 = Employee.builder()
                .name("Tan James")
                .email("james@gmail.com")
                .dob(LocalDate.of(1995, 1, 1))
                .salary(new BigDecimal("200.00"))
                .build();

        Employee employee2 = Employee.builder()
                .name("Taniya Hil")
                .email("taniya@gmail.com")
                .dob(LocalDate.of(1996, 1, 1))
                .salary(new BigDecimal("250.00"))
                .build();

        EmployeeSearchDTO employeeSearchDTO = EmployeeSearchDTO.builder()
                .name("Tan")
                .dob(employee1.getDob())
                .salary(employee1.getSalary())
                .build();

        EmployeeSearchDTO employeeSearchDTO1 = EmployeeSearchDTO.builder()
                .name("T")
                .phoneType(PhoneType.MOBILE)
                .build();

        List<Employee> employeeList1 = this.employeeService.findBySearchableFieldsInJoinTable(employeeSearchDTO1);
        assertNotNull(employeeList1);

        assertEquals(employee1.getName(), employeeList1.get(0).getName());
        assertEquals(employee1.getEmail(), employeeList1.get(0).getEmail());
        assertEquals(employee1.getDob(), employeeList1.get(0).getDob());
        assertEquals(employee1.getSalary(), employeeList1.get(0).getSalary());

        assertEquals(employee2.getName(), employeeList1.get(1).getName());
        assertEquals(employee2.getEmail(), employeeList1.get(1).getEmail());
        assertEquals(employee2.getDob(), employeeList1.get(1).getDob());
        assertEquals(employee2.getSalary(), employeeList1.get(1).getSalary());
    }
}
