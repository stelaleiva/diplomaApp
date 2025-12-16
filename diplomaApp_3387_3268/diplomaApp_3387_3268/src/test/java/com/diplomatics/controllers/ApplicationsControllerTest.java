package com.diplomatics.controllers;

import com.diplomatics.entity.Applications;
import com.diplomatics.entity.Diploma;
import com.diplomatics.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ApplicationsControllerTest {

    ApplicationsController applicationsController=new ApplicationsController();

    public User addUser(){
        User user = new User();
        user.setId(5);
        user.setUserName("test");
        user.setSpeciality("pc prof");
        user.setPassword("test");
        user.setRole("PROF");
        user.setFull_name("Nick Test");
        return user;

    }
    @Mock
    private Authentication auth ;

    @BeforeEach
    public void initSecurityContext() {
        when(auth.getCredentials()).thenReturn("");
        when(auth.getName()).thenReturn("test_user");
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    @Test
    public void addApplication(){
        User user=addUser();
        Applications applications=new Applications();
        applications.setDiploma("Test title diploma");
        applications.setDiploma_id(6);
        applications.setSelected("Όχι");
        applications.setStudent(user.getFull_name());
        applicationsController.showApplications((Model) applications);

    }
}