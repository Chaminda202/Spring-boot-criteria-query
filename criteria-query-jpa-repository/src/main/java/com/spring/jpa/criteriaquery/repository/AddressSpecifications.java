package com.spring.jpa.criteriaquery.repository;

import com.spring.jpa.criteriaquery.model.AddressSearchDTO;
import com.spring.jpa.criteriaquery.model.entity.Address;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class AddressSpecifications {
    private final String wildcard = "%";

    public Specification<Address> filterAddressDetails(AddressSearchDTO addressSearchDTO) {
        return (root, query, cb) -> {
            query.distinct(true);
            List<Predicate> predicates = new ArrayList<>();
            if(Objects.nonNull(addressSearchDTO)) {

                if(StringUtils.isNoneBlank(addressSearchDTO.getFirstName())) {
                    predicates.add(cb.like(root.join("user").get("firstName"), matchingCase(addressSearchDTO.getFirstName())));
                }
                if(StringUtils.isNoneBlank(addressSearchDTO.getLastName())) {
                    predicates.add(cb.like(root.join("user").get("lastName"), matchingCase(addressSearchDTO.getLastName())));
                }
                if(StringUtils.isNoneBlank(addressSearchDTO.getEmail())) {
                    predicates.add(cb.like(root.join("user").get("email"), matchingCase(addressSearchDTO.getEmail())));
                }
                if(StringUtils.isNoneBlank(addressSearchDTO.getStreet())) {
                    predicates.add(cb.like(root.get("street"), matchingCase(addressSearchDTO.getStreet())));
                }
                if(StringUtils.isNoneBlank(addressSearchDTO.getCity())) {
                    predicates.add(cb.like(root.get("city"), matchingCase(addressSearchDTO.getCity())));
                }
            }
           return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    private String matchingCase(String value) {
        return wildcard + value.trim() + wildcard;
    }
}
