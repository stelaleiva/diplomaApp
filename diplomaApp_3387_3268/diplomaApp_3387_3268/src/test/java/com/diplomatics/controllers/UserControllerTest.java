package com.diplomatics.controllers;

import com.diplomatics.dao.UserDao;
import com.diplomatics.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class UserControllerTest {

    @Mock
    private Authentication auth ;

    @Mock
    private BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    @BeforeEach
    public void initSecurityContext() {
        when(auth.getCredentials()).thenReturn("");
        when(auth.getName()).thenReturn("anonymousUser");
        SecurityContextHolder.getContext().setAuthentication(auth);
        when(passwordEncoder.encode("test")).thenReturn("test");
    }

    @MockBean
    UserDao userDao;

    UserController userController=new UserController();

    @Test
    public void addUser(){
        User user = new User();
        user.setUserName("test");
        user.setSpeciality("pc prof");
        user.setPassword("test");
        user.setRole("PROF");
        user.setFull_name("Nick Test");
        userController.saveUser(user);
    }
}