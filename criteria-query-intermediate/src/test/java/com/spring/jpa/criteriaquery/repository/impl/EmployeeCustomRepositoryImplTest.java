package com.spring.jpa.criteriaquery.repository.impl;

import com.spring.jpa.criteriaquery.enumeration.PhoneType;
import com.spring.jpa.criteriaquery.model.EmployeeSummaryDTO;
import com.spring.jpa.criteriaquery.model.MultipleEntity;
import com.spring.jpa.criteriaquery.model.entity.Call;
import com.spring.jpa.criteriaquery.model.entity.Employee;
import com.spring.jpa.criteriaquery.model.entity.Partner;
import com.spring.jpa.criteriaquery.model.entity.Phone;
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
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmployeeCustomRepositoryImplTest {
    @Autowired
    private EmployeeCustomRepositoryImpl employeeCustomRepositoryImplUnderTest;

    @Test
    @Order(1)
    void testSaveMultipleEntities() {
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
                .number("+94777345623")
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

        Partner partner = Partner.builder()
                .name("Tom")
                .age(25)
                .build();

        MultipleEntity entity = MultipleEntity.builder()
                .employee(employee1)
                .employee2(employee2)
                .partner(partner)
                .build();
        // Run the test
        employeeCustomRepositoryImplUnderTest.testSaveMultipleEntities(entity);
    }

    @Test
    @Order(2)
    void testAll() {
        Employee employee = Employee.builder()
                .name("Tan James")
                .email("james@gmail.com")
                .dob(LocalDate.of(1995, 1, 1))
                .salary(new BigDecimal("200.00"))
                .build();

        Partner partner = Partner.builder()
                .name("Tom")
                .age(25)
                .build();

        MultipleEntity entity = MultipleEntity.builder()
                .employee(employee)
                .partner(partner)
                .build();

        List<MultipleEntity> multipleEntityList = employeeCustomRepositoryImplUnderTest.all();
        assertNotNull(multipleEntityList);
        assertEquals(employee.getName(), multipleEntityList.get(0).getEmployee().getName());
        assertEquals(employee.getEmail(), multipleEntityList.get(0).getEmployee().getEmail());
        assertEquals(employee.getDob(), multipleEntityList.get(0).getEmployee().getDob());
        assertEquals(employee.getSalary(), multipleEntityList.get(0).getEmployee().getSalary());
        // Phone list is not available, because of lazy loading
        // assertNull(multipleEntityList.get(0).getEmployee().getPhones());
        assertEquals(partner.getName(), multipleEntityList.get(0).getPartner().getName());
        assertEquals(partner.getAge(), multipleEntityList.get(0).getPartner().getAge());
    }

    @Test
    @Order(3)
    void testSelectEntityFulfillingConditions() {
        Employee employee = Employee.builder()
                .name("Tan James")
                .email("james@gmail.com")
                .dob(LocalDate.of(1995, 1, 1))
                .salary(new BigDecimal("200.00"))
                .build();

        Phone phone1 = Phone.builder()
                .number("+94717345623")
                .phoneType(PhoneType.MOBILE)
                .employee(employee)
                .build();
        Phone phone2 = Phone.builder()
                .number("+94113456789")
                .phoneType(PhoneType.LAND_LINE)
                .employee(employee)
                .build();

        employee.setPhones(Arrays.asList(phone1, phone2));

        Partner partner = Partner.builder()
                .name("Tom")
                .age(25)
                .build();

        MultipleEntity entity = MultipleEntity.builder()
                .employee(employee)
                .partner(partner)
                .build();

        List<MultipleEntity> multipleEntityList = employeeCustomRepositoryImplUnderTest.selectEntityFulfillingConditions();
        assertNotNull(multipleEntityList);
        assertEquals(employee.getName(), multipleEntityList.get(0).getEmployee().getName());
        assertEquals(employee.getEmail(), multipleEntityList.get(0).getEmployee().getEmail());
        assertEquals(employee.getDob(), multipleEntityList.get(0).getEmployee().getDob());
        assertEquals(employee.getSalary(), multipleEntityList.get(0).getEmployee().getSalary());
        assertNotNull(multipleEntityList.get(0).getEmployee().getPhones());
        assertEquals(phone1.getNumber(), multipleEntityList.get(0).getEmployee().getPhones().get(0).getNumber());
        assertEquals(phone1.getPhoneType(), multipleEntityList.get(0).getEmployee().getPhones().get(0).getPhoneType());
        assertEquals(phone2.getNumber(), multipleEntityList.get(0).getEmployee().getPhones().get(1).getNumber());
        assertEquals(phone2.getPhoneType(), multipleEntityList.get(0).getEmployee().getPhones().get(1).getPhoneType());
        assertEquals(partner.getName(), multipleEntityList.get(0).getPartner().getName());
        assertEquals(partner.getAge(), multipleEntityList.get(0).getPartner().getAge());
    }

    @Test
    @Order(4)
    void testJoinQuery() {
        Employee employee = Employee.builder()
                .name("Tan James")
                .email("james@gmail.com")
                .dob(LocalDate.of(1995, 1, 1))
                .salary(new BigDecimal("200.00"))
                .build();

        Phone phone1 = Phone.builder()
                .number("+94717345623")
                .phoneType(PhoneType.MOBILE)
                .employee(employee)
                .build();
        Phone phone2 = Phone.builder()
                .number("+94113456789")
                .phoneType(PhoneType.LAND_LINE)
                .employee(employee)
                .build();

        employee.setPhones(Arrays.asList(phone1, phone2));

        Partner partner = Partner.builder()
                .name("Tom")
                .age(25)
                .build();

        MultipleEntity entity = MultipleEntity.builder()
                .employee(employee)
                .partner(partner)
                .build();

        List<Phone> phoneList = this.employeeCustomRepositoryImplUnderTest.joinQuery();

        assertNotNull(phoneList);
        assertEquals(employee.getName(), phoneList.get(0).getEmployee().getName());
        assertEquals(employee.getEmail(), phoneList.get(0).getEmployee().getEmail());
        assertEquals(employee.getDob(), phoneList.get(0).getEmployee().getDob());
        assertEquals(employee.getSalary(), phoneList.get(0).getEmployee().getSalary());
        assertNotNull(phoneList.get(0).getCalls());
    }

    @Test
    @Order(5)
    void testJoinQueryAnotherWay() {
        Employee employee = Employee.builder()
                .name("Tan James")
                .email("james@gmail.com")
                .dob(LocalDate.of(1995, 1, 1))
                .salary(new BigDecimal("200.00"))
                .build();

        Phone phone1 = Phone.builder()
                .number("+94717345623")
                .phoneType(PhoneType.MOBILE)
                .build();

        Phone phone2 = Phone.builder()
                .number("+94113456789")
                .phoneType(PhoneType.LAND_LINE)
                .build();

        List<Phone> phoneList = this.employeeCustomRepositoryImplUnderTest.joinQueryAnotherWay();

        assertNotNull(phoneList);
        assertEquals(phone1.getNumber(), phoneList.get(0).getNumber());
        assertEquals(phone1.getPhoneType(), phoneList.get(0).getPhoneType());
        assertEquals(employee.getName(), phoneList.get(0).getEmployee().getName());
        assertEquals(employee.getEmail(), phoneList.get(0).getEmployee().getEmail());
        assertEquals(employee.getDob(), phoneList.get(0).getEmployee().getDob());
        assertEquals(employee.getSalary(), phoneList.get(0).getEmployee().getSalary());

        assertEquals(phone2.getNumber(), phoneList.get(1).getNumber());
        assertEquals(phone2.getPhoneType(), phoneList.get(1).getPhoneType());
        assertEquals(employee.getName(), phoneList.get(1).getEmployee().getName());
        assertEquals(employee.getEmail(), phoneList.get(1).getEmployee().getEmail());
        assertEquals(employee.getDob(), phoneList.get(1).getEmployee().getDob());
        assertEquals(employee.getSalary(), phoneList.get(1).getEmployee().getSalary());
    }

    @Test
    @Order(6)
    void testEagerFetchInQuery() {
        Employee employee = Employee.builder()
                .name("Tan James")
                .email("james@gmail.com")
                .dob(LocalDate.of(1995, 1, 1))
                .salary(new BigDecimal("200.00"))
                .build();

        Phone phone1 = Phone.builder()
                .number("+94717345623")
                .phoneType(PhoneType.MOBILE)
                .employee(employee)
                .build();
        Phone phone2 = Phone.builder()
                .number("+94113456789")
                .phoneType(PhoneType.LAND_LINE)
                .employee(employee)
                .build();

        employee.setPhones(Arrays.asList(phone1, phone2));

        Partner partner = Partner.builder()
                .name("Tom")
                .age(25)
                .build();

        MultipleEntity entity = MultipleEntity.builder()
                .employee(employee)
                .partner(partner)
                .build();

        List<Phone> phoneList = this.employeeCustomRepositoryImplUnderTest.eagerFetchInQuery();

        assertNotNull(phoneList);
        assertEquals(employee.getName(), phoneList.get(0).getEmployee().getName());
        assertEquals(employee.getEmail(), phoneList.get(0).getEmployee().getEmail());
        assertEquals(employee.getDob(), phoneList.get(0).getEmployee().getDob());
        assertEquals(employee.getSalary(), phoneList.get(0).getEmployee().getSalary());
        assertNotNull(phoneList.get(0).getCalls());
    }

    @Test
    @Order(7)
    void testParameterizedQuery() {
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

        List<Employee> employeeList = this.employeeCustomRepositoryImplUnderTest.parameterizedQuery("T", new BigDecimal("150"));

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

    @Test
    @Order(8)
    void testParameterizedQueryAnotherWay() {
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

        List<Employee> employeeList = this.employeeCustomRepositoryImplUnderTest.parameterizedQueryAnotherWay("T", new BigDecimal("150"));

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

    @Test
    @Order(9)
    void testTotalNumberOfEmployee() {
        Long actualResult = this.employeeCustomRepositoryImplUnderTest.totalNumberOfEmployee();
        assertEquals(2, actualResult);
    }

    @Test
    @Order(10)
    void testFindMaxSalaryOfEmployee() {
        BigDecimal actualResult = this.employeeCustomRepositoryImplUnderTest.findMaxSalaryOfEmployee();
        assertEquals(new BigDecimal("250.00"), actualResult);
    }

    @Test
    @Order(11)
    void testGetEmployeesSummary() {
        EmployeeSummaryDTO expectedResult = EmployeeSummaryDTO.builder()
                .totalCountOfEmployees(2L)
                .totalDistinctCountOfEmployees(2L)
                .sumSalaryOfEmployees(new BigDecimal("450.00"))
                .averageSalaryOfEmployees(225.0)
                .maxSalaryOfEmployees(new BigDecimal("250.00"))
                .build();
        EmployeeSummaryDTO actualResult = this.employeeCustomRepositoryImplUnderTest.getEmployeesSummary();
        assertEquals(expectedResult.getTotalCountOfEmployees(), actualResult.getTotalCountOfEmployees());
        assertEquals(expectedResult.getTotalDistinctCountOfEmployees(), actualResult.getTotalDistinctCountOfEmployees());
        assertEquals(expectedResult.getSumSalaryOfEmployees(), actualResult.getSumSalaryOfEmployees());
        assertEquals(expectedResult.getAverageSalaryOfEmployees(), actualResult.getAverageSalaryOfEmployees());
        assertEquals(expectedResult.getMaxSalaryOfEmployees(), actualResult.getMaxSalaryOfEmployees());
    }
}
