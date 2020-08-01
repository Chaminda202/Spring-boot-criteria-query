package com.spring.jpa.entitygraph.service.impl;

import com.spring.jpa.entitygraph.model.entity.User;
import com.spring.jpa.entitygraph.repository.UserRepository;
import com.spring.jpa.entitygraph.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.spring.jpa.entitygraph.enumeration.UserAttributeNode.USER_WITH_PHONE_AND_CALL_NODE;
import static com.spring.jpa.entitygraph.enumeration.UserAttributeNode.USER_WITH_PHONE_NODE;
import static com.spring.jpa.entitygraph.enumeration.UserEntityGraph.USER_WITH_PHONE;
import static com.spring.jpa.entitygraph.enumeration.UserEntityGraph.USER_WITH_PHONE_AND_ADDRESS;
import static com.spring.jpa.entitygraph.enumeration.UserEntityGraph.USER_WITH_PHONE_AND_CALL;
import static com.spring.jpa.entitygraph.enumeration.UserNamedEntity.USER_WITH_PHONE_AND_CALL_NAMED_QUERY;
import static com.spring.jpa.entitygraph.enumeration.UserNamedEntity.USER_WITH_PHONE_AND_CALL_NAMED_QUERY_ALL;
import static com.spring.jpa.entitygraph.enumeration.UserNamedEntity.USER_WITH_PHONE_NAMED_QUERY;
import static com.spring.jpa.entitygraph.enumeration.UserNamedEntity.USER_WITH_PHONE_NAMED_QUERY_ALL;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    @Override
    public User findUserWithPhoneById(Long id) {
        return this.userRepository.findByIdAndGraph(id, USER_WITH_PHONE.getName());
    }

    @Override // this approach can not be used
    public User findUserWithPhoneAndAddressById(Long id) {
        return this.userRepository.findByIdAndGraph(id, USER_WITH_PHONE_AND_ADDRESS.getName());
    }

    @Override
    public User findUserWithPhoneAndCallById(Long id) {
        return this.userRepository.findByIdAndGraph(id, USER_WITH_PHONE_AND_CALL.getName());
    }

    @Override
    public List<User> findAllUserWithPhone() {
        return this.userRepository.findAllByGraph(USER_WITH_PHONE.getName());
    }

    @Override
    public List<User> findAllUserWithPhoneAndCall() {
        return this.userRepository.findAllByGraph(USER_WITH_PHONE_AND_CALL.getName());
    }

    @Override
    public User findUserWithPhoneByIdNamedQuery(Long id) {
        return this.userRepository.findByIdAndNamedQuery(id, USER_WITH_PHONE_NAMED_QUERY.getName());
    }

    @Override
    public User findUserWithPhoneAndCallByIdNamedQuery(Long id) {
        return this.userRepository.findByIdAndNamedQuery(id, USER_WITH_PHONE_AND_CALL_NAMED_QUERY.getName());
    }

    @Override
    public List<User> findAllUserWithPhoneNamedQuery() {
        return this.userRepository.findAllByNamedQuery(USER_WITH_PHONE_NAMED_QUERY_ALL.getName());
    }

    @Override
    public List<User> findAllUserWithPhoneAndCallNamedQuery() {
        return this.userRepository.findAllByNamedQuery(USER_WITH_PHONE_AND_CALL_NAMED_QUERY_ALL.getName());
    }

    @Override
    public User findUserWithPhoneByIdCriteriaAPI(Long id) {
        return this.userRepository.findByIdWithCriteriaAPI(id, USER_WITH_PHONE_NODE);
    }

    @Override
    public User findUserWithPhoneAndCallByIdCriteriaAPI(Long id) {
        return this.userRepository.findByIdWithCriteriaAPI(id, USER_WITH_PHONE_AND_CALL_NODE);
    }

    @Override
    public List<User> findAllUserWithPhoneCriteriaAPI() {
        return this.userRepository.findAllWithCriteriaAPI(USER_WITH_PHONE_NODE);
    }

    @Override
    public List<User> findAllUserWithPhoneAndCallCriteriaAPI() {
        return this.userRepository.findAllWithCriteriaAPI(USER_WITH_PHONE_AND_CALL_NODE);
    }
}
