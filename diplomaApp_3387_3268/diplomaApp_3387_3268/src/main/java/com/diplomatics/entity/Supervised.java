package com.diplomatics.entity;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Table(name="supervised")
public class Supervised {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer super_id;

    @Column(nullable = false)
    private Integer diploma_id;

    @Column(length = 50, nullable = false)
    private String diploma;

    @Column(nullable = false)
    private Integer student_id;

    @Column(length = 50, nullable = false)
    private String student;

    @Column(nullable = false)
    private Integer prof_id;

    @Column(length = 50, nullable = false)
    private String prof;

    @Column(precision = 4, scale = 2 )
    private BigDecimal implem_grade;
    @Column(precision = 4, scale = 2 )
    private BigDecimal report_grade;

    @Column(precision = 4, scale = 2 )
    private BigDecimal present_grade;
    @Column(precision = 4, scale = 2 )
    private BigDecimal overall_grade;

    public Supervised(Integer diploma_id, String diploma, Integer student_id, String student, Integer prof_id, String prof) {
        this.diploma_id = diploma_id;
        this.diploma = diploma;
        this.student_id = student_id;
        this.student = student;
        this.prof_id = prof_id;
        this.prof = prof;
    }

    public Integer getSuper_id() {
        return super_id;
    }

    public void setSuper_id(Integer super_id) {
        this.super_id = super_id;
    }

    public Integer getDiploma_id() {
        return diploma_id;
    }

    public void setDiploma_id(Integer diploma_id) {
        this.diploma_id = diploma_id;
    }

    public String getDiploma() {
        return diploma;
    }

    public void setDiploma(String diploma) {
        this.diploma = diploma;
    }

    public Integer getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Integer student_id) {
        this.student_id = student_id;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public Integer getProf_id() {
        return prof_id;
    }

    public void setProf_id(Integer prof_id) {
        this.prof_id = prof_id;
    }

    public String getProf() {
        return prof;
    }

    public void setProf(String prof) {
        this.prof = prof;
    }

    public BigDecimal getImplem_grade() {
        return implem_grade;
    }

    public void setImplem_grade(BigDecimal implem_grade) {
        this.implem_grade = implem_grade;
    }

    public BigDecimal getReport_grade() {
        return report_grade;
    }

    public void setReport_grade(BigDecimal report_grade) {
        this.report_grade = report_grade;
    }

    public BigDecimal getPresent_grade() {
        return present_grade;
    }

    public void setPresent_grade(BigDecimal present_grade) {
        this.present_grade = present_grade;
    }

    public BigDecimal getOverall_grade() {
        return overall_grade;
    }

    public void setOverall_grade(BigDecimal overall_grade) {
        this.overall_grade = overall_grade;
    }
}
