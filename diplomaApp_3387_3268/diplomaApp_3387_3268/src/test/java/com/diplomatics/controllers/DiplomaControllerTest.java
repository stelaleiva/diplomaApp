package com.diplomatics.controllers;


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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class DiplomaControllerTest {

    @Mock
    private Authentication auth ;

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

    public Diploma diplomaCreate(){
        User user=addUser();
        Diploma diploma=new Diploma();
        diploma.setUser(user.getFull_name());
        diploma.setUser_id(user.getId());
        diploma.setObjectives("Testing");
        diploma.setTitle("Testing");
        return diploma;
    }

    @BeforeEach
    public void initSecurityContext() {
        when(auth.getCredentials()).thenReturn("");
        when(auth.getName()).thenReturn("test_user");
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    DiplomaController diplomaController = new DiplomaController();


    @Test
    public void deleteDiploma(){
        diplomaController.diplomaDelete(8,null);
    }

    @Test
    public void addDiploma(){
        Diploma diploma=diplomaCreate();
        diplomaController.saveDiploma(diploma,null);
    }

    @Test
    public void checkDiploma(){
        Diploma diploma=diplomaCreate();
        diplomaController.checkDiploma(diploma);
    }
}