package com.spring.jpa.criteriaquery.repository;

import com.spring.jpa.criteriaquery.model.EmployeeSearchDTO;
import com.spring.jpa.criteriaquery.model.entity.Employee;
import com.spring.jpa.criteriaquery.model.entity.Phone;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EmployeeSpecifications {

    public static Specification<Employee> eagerFetch() {
        return (root, cq, cb) -> {
            root.fetch("phones");
            cq.distinct(true);
            return null;
        };
    }

    public static Specification<Employee> dynamicQuery(EmployeeSearchDTO employeeSearchDTO) {
        return (root, cq, cb) -> {
            //create a new predicate list
            List<Predicate> predicates = new ArrayList<>();

            //add add criteria to predicates
            if(Objects.nonNull(employeeSearchDTO)) {
                if(employeeSearchDTO.getName() != null ) {
                    predicates.add(cb.like(root.get("name"), "%"+employeeSearchDTO.getName() +"%"));
                }
                if(employeeSearchDTO.getEmail() != null) {
                    predicates.add(cb.equal(root.get("email"), employeeSearchDTO.getEmail()));
                }
                if(employeeSearchDTO.getSalary()!= null) {
                    predicates.add(cb.equal(root.get("salary"), employeeSearchDTO.getSalary()));
                }
                if(employeeSearchDTO.getDob() != null) {
                    predicates.add(cb.equal(root.get("dob"), employeeSearchDTO.getDob()));
                }
            }
            return cb.and(predicates.toArray(new Predicate[0]));
            //return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    public static Specification<Employee> dynamicQueryWithJoin(EmployeeSearchDTO employeeSearchDTO) {
        return (root, cq, cb) -> {
            cq.distinct(true);
            root.fetch("phones");
            //create a new predicate list
            List<Predicate> predicates = new ArrayList<>();

            ListJoin<Employee, Phone> phones = root.joinList("phones", JoinType.LEFT);
            //add add criteria to predicates
            if(Objects.nonNull(employeeSearchDTO)) {
                if(employeeSearchDTO.getName() != null ) {
                    predicates.add(cb.like(root.get("name"), "%"+employeeSearchDTO.getName() +"%"));
                }
                if(employeeSearchDTO.getEmail() != null) {
                    predicates.add(cb.equal(root.get("email"), employeeSearchDTO.getEmail()));
                }
                if(employeeSearchDTO.getSalary()!= null) {
                    predicates.add(cb.equal(root.get("salary"), employeeSearchDTO.getSalary()));
                }
                if(employeeSearchDTO.getDob() != null) {
                    predicates.add(cb.equal(root.get("dob"), employeeSearchDTO.getDob()));
                }
                if(employeeSearchDTO.getNumber() != null) {
                    predicates.add(cb.equal(phones.get("number"), employeeSearchDTO.getNumber()));
                }
                if(employeeSearchDTO.getPhoneType() != null) {
                    predicates.add(cb.equal(phones.get("phoneType"), employeeSearchDTO.getPhoneType()));
                }
            }
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}
