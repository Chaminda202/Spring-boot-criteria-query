package com.spring.jpa.criteriaquery.repository;

import com.spring.jpa.criteriaquery.model.EmployeeSummaryDTO;
import com.spring.jpa.criteriaquery.model.MultipleEntity;
import com.spring.jpa.criteriaquery.model.entity.Employee;
import com.spring.jpa.criteriaquery.model.entity.Phone;

import java.math.BigDecimal;
import java.util.List;

public interface EmployeeCustomRepository {
    void testSaveMultipleEntities(MultipleEntity multipleEntities);

    /***
     * Returns multiple entities as response. if there is more than one records in each entity, will return
     * more records [ no of record one entity * no of record one entity for two entity response]
     * @return List<MultipleEntity>
     */
    List<MultipleEntity> all();

    /***
     * Returns multiple entities which are fulfilled predicate (conditions) [multiple roots with some conditions]
     * @return List<MultipleEntity>
     */
    List<MultipleEntity> selectEntityFulfillingConditions();

    List<Phone> joinQuery();

    List<Phone> joinQueryAnotherWay();

    /***
     * Eager fetching record when they mapping with OneToMany or ManyToOne
     * @return
     */
    List<Phone> eagerFetchInQuery();

    /***
     * Fetch the records according to the parameter. those parameter are required
     * @param name query parameter value
     * @param salary query parameter value
     * @return
     */
    List<Employee> parameterizedQuery(String name, BigDecimal salary);

    List<Employee> parameterizedQueryAnotherWay(String name, BigDecimal salary);

    /***
     * find the total number of employee using aggregate function
     * @return Long
     */
    Long totalNumberOfEmployee();

    /***
     * find the max salary of employee using aggregate function
     * @return BigDecimal
     */
    BigDecimal findMaxSalaryOfEmployee();

    /***
     * Find multiple aggregation function out put
     * @return EmployeeSummaryDTO
     */
    EmployeeSummaryDTO getEmployeesSummary();

    /***
     * Select the phone details group by employee (Using GroupBy and Having in criteria query)
     * @return List<Phone>
     */
    List<Phone> groupByPhoneDetails();

    /***
     * Order by employee details using salary (OrderBy- ASC/DESC)
     * @return List<Employee>
     */
    List<Employee> orderByEmployeeDetails();
}
