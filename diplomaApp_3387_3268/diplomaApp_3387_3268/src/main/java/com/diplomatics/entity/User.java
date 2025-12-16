package com.diplomatics.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(precision = 4, scale = 2 )
    private BigDecimal avg_grade;

    @Column(nullable = false, unique = true, length = 50)
    private String userName;

    @Column(length = 255, nullable = false)
    private String password;

    @Column(length = 50, nullable = false)
    private String full_name;

    @Column(length = 10, nullable = false)
    private String role;

    @Column
    private Integer years;

    @Column
    private Integer remain_courses;

    @Column
    private String speciality;

    private boolean enabled;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getAvg_grade() { return avg_grade; }

    public void setAvg_grade(BigDecimal avg_grade) {
        this.avg_grade = avg_grade;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getYears() {
        return years;
    }

    public void setYears(Integer years) {
        this.years = years;
    }

    public Integer getRemain_courses() {
        return remain_courses;
    }

    public void setRemain_courses(Integer remain_courses) {
        this.remain_courses = remain_courses;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    @Override
    public String toString() {
        return full_name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    public Boolean getEnabled() { return enabled; }
}
