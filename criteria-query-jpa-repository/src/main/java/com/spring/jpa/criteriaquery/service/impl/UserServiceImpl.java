package com.spring.jpa.criteriaquery.service.impl;

import com.spring.jpa.criteriaquery.model.UserSearchDTO;
import com.spring.jpa.criteriaquery.model.entity.User;
import com.spring.jpa.criteriaquery.repository.UserRepository;
import com.spring.jpa.criteriaquery.repository.UserSpecifications;
import com.spring.jpa.criteriaquery.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private UserSpecifications userSpecifications;

    @Override
    public List<User> filterUserAndAddressDetailsRetrieveAll(UserSearchDTO userSearchDTO) {
        return this.userRepository.findAll(this.userSpecifications.filterUserAndAddressDetails(userSearchDTO));
    }

    @Override
    public List<User> filterUserAndAddressDetailsRetrieveUser(UserSearchDTO userSearchDTO) {
        return this.userRepository.findAll(this.userSpecifications.filterUserAndAddressDetailsRetrieveUser(userSearchDTO));
    }
}
