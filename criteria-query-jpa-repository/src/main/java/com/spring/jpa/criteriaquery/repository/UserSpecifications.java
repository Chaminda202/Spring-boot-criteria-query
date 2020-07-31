package com.spring.jpa.criteriaquery.repository;

import com.spring.jpa.criteriaquery.model.UserSearchDTO;
import com.spring.jpa.criteriaquery.model.entity.Address;
import com.spring.jpa.criteriaquery.model.entity.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class UserSpecifications {
    private final String wildcard = "%";

    public Specification<User> getFilter(UserSearchDTO userSearchDTO) {
        return (root, query, cb) -> {
            query.distinct(true);
            root.fetch("addresses");

            //create a new predicate list
            List<Predicate> predicates = new ArrayList<>();
            ListJoin<User, Address> addresses = root.joinList("addresses", JoinType.LEFT);

            if(Objects.nonNull(userSearchDTO)) {
                if(userSearchDTO.getFirstName() != null) {
                    predicates.add(cb.equal(root.get("firstName"), userSearchDTO.getFirstName()));
                }
                if(userSearchDTO.getLastName() != null) {
                    predicates.add(cb.equal(root.get("lastName"), userSearchDTO.getLastName()));
                }
                if(userSearchDTO.getEmail() != null) {
                    predicates.add(cb.equal(root.get("email"), userSearchDTO.getEmail()));
                }
                if(userSearchDTO.getStreet() != null) {
                    predicates.add(cb.equal(addresses.get("street"), userSearchDTO.getStreet()));
                }
                if(userSearchDTO.getCity() != null) {
                    predicates.add(cb.equal(addresses.get("city"), userSearchDTO.getCity()));
                }
            }
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    public Specification<User> filterUserDetails(UserSearchDTO userSearchDTO) {
        return (root, query, cb) -> {
            query.distinct(true);
            List<Predicate> predicates = new ArrayList<>();
            if(Objects.nonNull(userSearchDTO)) {

                if(userSearchDTO.getFirstName() != null) {
                    predicates.add(cb.like(root.get("firstName"), matchingCase(userSearchDTO.getFirstName())));
                }
                if(userSearchDTO.getLastName() != null) {
                    predicates.add(cb.like(root.get("lastName"), matchingCase(userSearchDTO.getLastName())));
                }
                if(userSearchDTO.getEmail() != null) {
                    predicates.add(cb.like(root.get("email"), matchingCase(userSearchDTO.getEmail())));
                }
                if(userSearchDTO.getStreet() != null) {
                    predicates.add(cb.like(root.join("addresses").get("street"), matchingCase(userSearchDTO.getStreet())));
                }
                if(userSearchDTO.getCity() != null) {
                    predicates.add(cb.like(root.join("addresses").get("city"), matchingCase(userSearchDTO.getCity())));
                }
            }
           return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    private String matchingCase(String value) {
        return wildcard + value.trim() + wildcard;
    }
}
