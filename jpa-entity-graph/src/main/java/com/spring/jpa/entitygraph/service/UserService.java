package com.spring.jpa.entitygraph.service;

import com.spring.jpa.entitygraph.model.entity.User;

import java.util.List;

public interface UserService {
    User findUserWithPhoneById(Long id);
    User findUserWithPhoneAndAddressById(Long id);
    User findUserWithPhoneAndCallById(Long id);
    List<User> findAllUserWithPhone();
    List<User> findAllUserWithPhoneAndCall();
    User findUserWithPhoneByIdNamedQuery(Long id);
    User findUserWithPhoneAndCallByIdNamedQuery(Long id);
    List<User> findAllUserWithPhoneNamedQuery();
    List<User> findAllUserWithPhoneAndCallNamedQuery();
    User findUserWithPhoneByIdCriteriaAPI(Long id);
    User findUserWithPhoneAndCallByIdCriteriaAPI(Long id);
    List<User> findAllUserWithPhoneCriteriaAPI();
    List<User> findAllUserWithPhoneAndCallCriteriaAPI();
    User findByIdEntityGraphAnnotation(Long id); //override the spring JPA findById() method using entity graph annotation [@EntityGraph]
    List<User> findAllEntityGraphAnnotation(); //override the spring JPA findById() method using entity graph annotation [@EntityGraph]
}
