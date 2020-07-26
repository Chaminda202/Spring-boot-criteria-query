package com.spring.jpa.criteriaquery.repository.impl;

import com.spring.jpa.criteriaquery.model.MultipleEntity;
import com.spring.jpa.criteriaquery.model.entity.Employee;
import com.spring.jpa.criteriaquery.model.entity.Partner;
import com.spring.jpa.criteriaquery.model.entity.Phone;
import com.spring.jpa.criteriaquery.repository.EmployeeCustomRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
@Transactional
public class EmployeeCustomRepositoryImpl implements EmployeeCustomRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void testSaveMultipleEntities(MultipleEntity multipleEntities) {
        this.entityManager.persist(multipleEntities.getEmployee());
        this.entityManager.persist(multipleEntities.getEmployee2());
        this.entityManager.persist(multipleEntities.getPartner());
    }

    @Override
    public List<MultipleEntity> all() {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Tuple> tupleQuery = criteriaBuilder.createTupleQuery();
        Root<Employee> employeeRoot = tupleQuery.from(Employee.class);
        Root<Partner> partnerRoot = tupleQuery.from(Partner.class);
        tupleQuery.multiselect(employeeRoot, partnerRoot);

        return this.entityManager.createQuery(tupleQuery).getResultList().stream()
                .map(tuple -> {
                    Employee employee = tuple.get(0, Employee.class);
                    employee.setPhones(employee.getPhones());
                    Partner partner = tuple.get(1, Partner.class);
                    return MultipleEntity.builder()
                            .employee(employee)
                            .partner(partner)
                            .build();
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<MultipleEntity> selectEntityFulfillingConditions() {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Tuple> tupleQuery = criteriaBuilder.createTupleQuery();
        Root<Employee> employeeRoot = tupleQuery.from(Employee.class);
        Root<Partner> partnerRoot = tupleQuery.from(Partner.class);
        tupleQuery.multiselect(employeeRoot, partnerRoot);

        Predicate employeePredicate = criteriaBuilder.and(
                criteriaBuilder.equal(employeeRoot.get("name"), "James"),
                criteriaBuilder.isNotEmpty(employeeRoot.get("phones")));

        Predicate partnerPredicate = criteriaBuilder.and(
                criteriaBuilder.like(partnerRoot.get("name"), "%t%%"),
                criteriaBuilder.equal(partnerRoot.get("version"), 0));

        tupleQuery.where(criteriaBuilder.and(employeePredicate, partnerPredicate));

        return this.entityManager.createQuery(tupleQuery).getResultList().stream()
                .map(tuple -> {
                    Employee employee = tuple.get(0, Employee.class);
                    if (Objects.nonNull(employee)) {
                        List<Phone> phoneList = employee.getPhones();
                        employee.getPhones().addAll(phoneList);
                    }
                    Partner partner = tuple.get(1, Partner.class);
                    return MultipleEntity.builder()
                            .employee(employee)
                            .partner(partner)
                            .build();
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<Phone> joinQuery() {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Phone> criteriaQuery = criteriaBuilder.createQuery(Phone.class);
        Root<Phone> root = criteriaQuery.from(Phone.class);
        root.join("employee");

        criteriaQuery.where(criteriaBuilder.isNotEmpty(root.get("calls")));

        List<Phone> phoneList = this.entityManager.createQuery(criteriaQuery).getResultList().stream()
                 .map(phone -> {
                     Employee employee = phone.getEmployee();
                     employee.setName(phone.getEmployee().getName());
                     phone.setEmployee(employee);
                     return phone;
                 })
                .collect(Collectors.toList());
        return phoneList;
    }

    @Override
    public List<Phone> eagerFetchInQuery() {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Phone> criteriaQuery = criteriaBuilder.createQuery(Phone.class);
        Root<Phone> root = criteriaQuery.from(Phone.class);
        // Eager fetching Employee (ManyToOne) & Call (OneToMany)
        root.fetch("employee");
        root.fetch("calls");
        criteriaQuery.where(criteriaBuilder.isNotEmpty(root.get("calls")));

        return this.entityManager.createQuery(criteriaQuery).getResultList();
    }
}
