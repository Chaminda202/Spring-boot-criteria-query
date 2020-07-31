package com.spring.jpa.criteriaquery.service;

import com.spring.jpa.criteriaquery.model.UserSearchDTO;
import com.spring.jpa.criteriaquery.model.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAllBySearchableFields(UserSearchDTO userSearchDTO);
}
