package com.diplomatics.entity;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Table(name = "applications")
public class Applications {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer app_id;

    @Column(nullable = false, length = 50)
    private String diploma;

    @Column(nullable = false)
    private Integer diploma_id;

    @Column(nullable = false, length = 50)
    private String student;

    @Column(nullable = false)
    private Integer student_id;

    @Column(nullable = false)
    private String selected;

    public Integer getApp_id() {
        return app_id;
    }

    public void setApp_id(Integer app_id) {
        this.app_id = app_id;
    }

    public String getDiploma() {
        return diploma;
    }

    public void setDiploma(String diploma) {
        this.diploma = diploma;
    }

    public Integer getDiploma_id() {
        return diploma_id;
    }

    public void setDiploma_id(Integer diploma_id) {
        this.diploma_id = diploma_id;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public Integer getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Integer student_id) {
        this.student_id = student_id;
    }

    public String getSelected() { return selected; }

    public void setSelected(String selected) { this.selected = selected; }
}
