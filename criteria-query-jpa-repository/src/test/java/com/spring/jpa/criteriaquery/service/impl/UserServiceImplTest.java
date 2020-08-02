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
    void testFilterUserAndAddressDetailsRetrieveUser() {
        UserSearchDTO userSearchDTO = UserSearchDTO.builder()
                .firstName("John")
                .city("Budapest")
                .street("East Sturbridge Garth")
                .build();
        List<User> userList = this.userService.filterUserAndAddressDetailsRetrieveUser(userSearchDTO);
        assertNotNull(userList);
    }

    @Test
    @Order(2)
    void testFilterUserAndAddressDetailsRetrieveAll() {
        UserSearchDTO userSearchDTO = UserSearchDTO.builder()
                .firstName("John")
                .city("Budapest")
                .street("East Sturbridge Garth")
                .build();
        List<User> userList = this.userService.filterUserAndAddressDetailsRetrieveAll(userSearchDTO);
        assertNotNull(userList);
        userList.forEach(user -> {
            assertNotNull(user.getAddresses());
        });
    }
}
