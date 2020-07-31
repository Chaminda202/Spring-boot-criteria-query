package com.spring.jpa.criteriaquery.service.impl;

import com.spring.jpa.criteriaquery.model.UserSearchDTO;
import com.spring.jpa.criteriaquery.model.entity.User;
import com.spring.jpa.criteriaquery.service.UserService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceImplTest {
    @Autowired
    private UserService userService;

    @Test
    @Order(1)
    void testFindAllBySearchableFields(){
        UserSearchDTO userSearchDTO = UserSearchDTO.builder()
                .city("Buda")
                .build();
        List<User> userList = this.userService.findAllBySearchableFields(userSearchDTO);
        assertNotNull(userList);
    }
}
