package com.spring.jpa.criteriaquery.repository.impl;

import com.spring.jpa.criteriaquery.model.EmployeeDTO;
import com.spring.jpa.criteriaquery.model.entity.Employee;
import com.spring.jpa.criteriaquery.repository.EmployeeCustomRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Transactional
public class EmployeeCustomRepositoryImpl implements EmployeeCustomRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveEmployee(Employee employee) {
        this.entityManager.persist(employee);
    }

    @Override
    public List<Employee> all() {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);
        criteriaQuery.select(root);
        return this.entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public Employee findByName(String name) {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);
        criteriaQuery.where(criteriaBuilder.equal(root.get("name"), name));
        return this.entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    @Override
    public List<String> getEmployeeNames() {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<String> criteriaQuery = criteriaBuilder.createQuery(String.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);
        criteriaQuery.select(root.get("name"));
        return this.entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public void deleteEmployee(Employee employee) {
        this.entityManager.remove(this.entityManager.contains(employee) ? employee : this.entityManager.merge(employee));
    }

    @Override
    public List<Employee> selectMultipleAttributes() {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);

        Root<Employee> root = criteriaQuery.from(Employee.class);
        Path<Object> namePath = root.get("name");
        Path<Object> emailPath = root.get("email");
        Path<Object> salaryPath = root.get("salary");

        // criteriaQuery.select(criteriaBuilder.array(namePath, emailPath, salaryPath)); // first way
        criteriaQuery.multiselect(namePath, emailPath, salaryPath); // Second way

        return this.entityManager.createQuery(criteriaQuery).getResultList().stream()
                .map(object -> Employee.builder()
                        .name((String) object[0])
                        .email((String) object[1])
                        .salary((BigDecimal) object[2])
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<Employee> selectMultipleAttributesWithoutConversion() {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();

        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);

        Root<Employee> root = criteriaQuery.from(Employee.class);
        Path<Object> namePath = root.get("name");
        Path<Object> emailPath = root.get("email");
        Path<Object> salaryPath = root.get("salary");

        // this scenario need to create three arguments constructor in Employee entity
        criteriaQuery.select(criteriaBuilder.construct(Employee.class, namePath, emailPath, salaryPath));
        return this.entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<EmployeeDTO> selectMultipleAttributesAndMapDTO() {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();

        CriteriaQuery<EmployeeDTO> criteriaQuery = criteriaBuilder.createQuery(EmployeeDTO.class);

        Root<Employee> root = criteriaQuery.from(Employee.class);
        Path<Object> namePath = root.get("name");
        Path<Object> emailPath = root.get("email");
        Path<Object> salaryPath = root.get("salary");

        criteriaQuery.select(criteriaBuilder.construct(EmployeeDTO.class, namePath, emailPath, salaryPath));
        return this.entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<Employee> selectMultipleAttributesUsingTupleQuery() {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Tuple> tupleQuery = criteriaBuilder.createTupleQuery();
        Root<Employee> root = tupleQuery.from(Employee.class);

        // define the fields which are going to select
        Path<Object> namePath = root.get("name");
        Path<Object> emailPath = root.get("email");
        Path<Object> salaryPath = root.get("salary");

        tupleQuery.multiselect(namePath, emailPath, salaryPath);

        // first way
        /*return this.entityManager.createQuery(tupleQuery).getResultList().stream()
                .map(tuple -> Employee.builder()
                        .name((String) tuple.get(namePath))
                        .email((String) tuple.get(emailPath))
                        .salary((BigDecimal)tuple.get(salaryPath))
                        .build())
                .collect(Collectors.toList());*/

        return this.entityManager.createQuery(tupleQuery).getResultList().stream()
                .map(tuple -> Employee.builder()
                        .name(tuple.get(0, String.class))
                        .email(tuple.get(1, String.class))
                        .salary(tuple.get(2, BigDecimal.class))
                        .build())
                .collect(Collectors.toList());
    }
}
