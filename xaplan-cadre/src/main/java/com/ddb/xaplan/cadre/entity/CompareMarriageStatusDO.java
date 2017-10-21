package com.ddb.xaplan.cadre.entity;

import com.ddb.xaplan.cadre.enums.MaritalStatus;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "compare_marriage_status")
@Where(clause = "is_deleted = 0")
public class CompareMarriageStatusDO extends BaseEntity{
    private String cadreIdCard;
    private MaritalStatus marriage_status;

    @Column
    public String getCadreIdCard() {
        return cadreIdCard;
    }

    public void setCadreIdCard(String cadreIdCard) {
        this.cadreIdCard = cadreIdCard;
    }

    @Column
    public MaritalStatus getMarriage_status() {
        return marriage_status;
    }

    public void setMarriage_status(MaritalStatus marriage_status) {
        this.marriage_status = marriage_status;
    }
}
