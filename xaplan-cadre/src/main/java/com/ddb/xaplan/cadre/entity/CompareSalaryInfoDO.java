package com.ddb.xaplan.cadre.entity;

import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="compare_salary_info")
@Where(clause = "is_deleted = 0")
public class CompareSalaryInfoDO extends BaseEntity {
    private String cadreIdCard;
    private String year;
    private float salaryAmount;

    @Column
    public String getCadreIdCard() {
        return cadreIdCard;
    }

    public void setCadreIdCard(String cadreIdCard) {
        this.cadreIdCard = cadreIdCard;
    }

    @Column
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Column
    public float getSalaryAmount() {
        return salaryAmount;
    }

    public void setSalaryAmount(float salaryAmount) {
        this.salaryAmount = salaryAmount;
    }
}
