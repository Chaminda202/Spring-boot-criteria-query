package com.spring.jpa.criteriaquery.repository;

import com.spring.jpa.criteriaquery.model.entity.Address;
import com.spring.jpa.criteriaquery.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>, JpaSpecificationExecutor<Address> {
}
