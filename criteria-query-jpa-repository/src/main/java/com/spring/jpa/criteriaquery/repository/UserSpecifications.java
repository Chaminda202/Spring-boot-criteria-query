package com.spring.jpa.criteriaquery.repository;

import com.spring.jpa.criteriaquery.model.UserSearchDTO;
import com.spring.jpa.criteriaquery.model.entity.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.SetJoin;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class UserSpecifications {
    private final String wildcard = "%";

    /***
     * Join with mapping table but not retrieve the record from the mapping table
     * @param userSearchDTO
     * @return
     */
    public Specification<User> filterUserAndAddressDetailsRetrieveUser(UserSearchDTO userSearchDTO) {
        return (root, query, cb) -> {
            query.distinct(true);
            SetJoin<Object, Object> addresses = root.joinSet("addresses", JoinType.LEFT);
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
                    predicates.add(cb.like(addresses.get("street"), matchingCase(userSearchDTO.getStreet())));
                }
                if(userSearchDTO.getCity() != null) {
                    predicates.add(cb.like(addresses.get("city"), matchingCase(userSearchDTO.getCity())));
                }
            }
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    /***
     * Join with mapping table and retrieve the record from the mapping table
     * @param userSearchDTO
     * @return
     */
    public Specification<User> filterUserAndAddressDetails(UserSearchDTO userSearchDTO) {
        return (root, query, cb) -> {
            query.distinct(true);
            //create a new predicate list
            List<Predicate> predicates = new ArrayList<>();
            Join<Object, Object> addresses = (Join<Object, Object>) root.fetch("addresses", JoinType.LEFT); //JOIN FETCH clause

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

    private String matchingCase(String value) {
        return wildcard + value.trim() + wildcard;
    }
}
