package com.spring.jpa.entitygraph.service.impl;

import com.spring.jpa.entitygraph.model.entity.User;
import com.spring.jpa.entitygraph.service.UserService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceImplTest {
    @Autowired
    private UserService userService;

    @Order(1)
    @Test
    void testFindUserWithPhoneById() {
        User user = this.userService.findUserWithPhoneById(1L);
        assertNotNull(user);
        assertNotNull(user.getPhones());
        assertEquals(2, user.getPhones().size());
    }

    @Order(2)
    @Test
    void testFindUserWithPhoneAndCallById() {
        User user = this.userService.findUserWithPhoneAndCallById(1L);
        assertNotNull(user);
        assertNotNull(user.getPhones());
    }

    @Order(3)
    @Test
    void testFindAllUserWithPhone() {
        List<User> userList = this.userService.findAllUserWithPhone();
        assertNotNull(userList);
        assertEquals(5, userList.size());
        userList.forEach(user -> {
            assertNotNull(user.getPhones());
        });
    }

    @Order(4)
    @Test
    void testFindAllUserWithPhoneAndCall() {
        List<User> userList = this.userService.findAllUserWithPhoneAndCall();
        assertNotNull(userList);
        assertEquals(5, userList.size());
        userList.forEach(user -> {
            assertNotNull(user.getPhones());
            user.getPhones().forEach(phone -> {
                assertNotNull(phone.getCalls());
            });
        });
    }

    @Order(5)
    @Test
    void testFindUserWithPhoneByIdNamedQuery() {
        User user = this.userService.findUserWithPhoneByIdNamedQuery(1L);
        assertNotNull(user);
        assertNotNull(user.getPhones());
        assertEquals(2, user.getPhones().size());
    }

    @Order(6)
    @Test
    void testFindUserWithPhoneAndCallByIdNamedQuery() {
        User user = this.userService.findUserWithPhoneAndCallByIdNamedQuery(1L);
        assertNotNull(user);
        assertNotNull(user.getPhones());
    }

    @Order(7)
    @Test
    void testFindAllUserWithPhonedNamedQuery() {
        List<User> userList = this.userService.findAllUserWithPhoneNamedQuery();
        assertNotNull(userList);
        assertEquals(5, userList.size());
        userList.forEach(user -> {
            assertNotNull(user.getPhones());
        });
    }

    @Order(8)
    @Test
    void testFindAllUserWithPhoneAndCallNamedQuery() {
        List<User> userList = this.userService.findAllUserWithPhoneAndCallNamedQuery();
        assertNotNull(userList);
        assertEquals(5, userList.size());
        userList.forEach(user -> {
            assertNotNull(user.getPhones());
            user.getPhones().forEach(phone -> {
                assertNotNull(phone.getCalls());
            });
        });
    }

    @Order(9)
    @Test
    void testFindUserWithPhoneByIdCriteriaAPI() {
        User user = this.userService.findUserWithPhoneByIdCriteriaAPI(1L);
        assertNotNull(user);
        assertNotNull(user.getPhones());
        assertEquals(2, user.getPhones().size());
    }

    @Order(10)
    @Test
    void testFindUserWithPhoneAndCallByIdCriteriaAPI() {
        User user = this.userService.findUserWithPhoneAndCallByIdCriteriaAPI(1L);
        assertNotNull(user);
        assertNotNull(user.getPhones());
    }

    @Order(11)
    @Test
    void testFindAllUserWithPhonedCriteriaAPI() {
        List<User> userList = this.userService.findAllUserWithPhoneCriteriaAPI();
        assertNotNull(userList);
        assertEquals(5, userList.size());
        userList.forEach(user -> {
            assertNotNull(user.getPhones());
        });
    }

    @Order(12)
    @Test
    void testFindAllUserWithPhoneAndCallCriteriaAPI() {
        List<User> userList = this.userService.findAllUserWithPhoneAndCallCriteriaAPI();
        assertNotNull(userList);
        assertEquals(5, userList.size());
        userList.forEach(user -> {
            assertNotNull(user.getPhones());
            user.getPhones().forEach(phone -> {
                assertNotNull(phone.getCalls());
            });
        });
    }

    @Order(13)
    @Test
    void testFindByIdEntityGraphAnnotation() {
        User user = this.userService.findByIdEntityGraphAnnotation(1L);
        assertNotNull(user);
        assertNotNull(user.getPhones());
        user.getPhones().forEach(phone -> {
            assertNotNull(phone.getCalls());
        });
    }

    @Order(14)
    @Test
    void testFindAllEntityGraphAnnotation() {
        List<User> userList = this.userService.findAllEntityGraphAnnotation();
        assertNotNull(userList);
        assertEquals(5, userList.size());
        userList.forEach(user -> {
            assertNotNull(user.getPhones());
            user.getPhones().forEach(phone -> {
                assertNotNull(phone.getCalls());
            });
        });
    }
}
