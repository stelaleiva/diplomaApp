package com.diplomatics.controllers;

import com.diplomatics.entity.Supervised;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.math.BigDecimal;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class SupervisedControllerTest {

    SupervisedController supervisedController=new SupervisedController();

    @Test
    public void saveForm(){
        Supervised supervised=new Supervised();
        supervised.setDiploma("Testing");
        supervised.setDiploma_id(4);
        supervised.setStudent("Student_Name");
        supervised.setStudent_id(6);
        supervised.setProf("Prof_Name");
        supervised.setProf_id(10);
        supervised.setImplem_grade(BigDecimal.valueOf(8.7));
        supervised.setPresent_grade(BigDecimal.valueOf(6.9));
        supervised.setReport_grade(BigDecimal.valueOf(8.0));
        BigDecimal val= BigDecimal.valueOf(0.7 * 8.7 +0.15 * 8.0 + 0.15 * 6.9);
        supervised.setOverall_grade(val);
        supervisedController.saveForm(supervised,null);
    }



}