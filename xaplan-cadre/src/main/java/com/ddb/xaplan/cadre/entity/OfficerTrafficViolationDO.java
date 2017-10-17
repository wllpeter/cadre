package com.ddb.xaplan.cadre.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * Author:Zan Yang
 * Title:交通违章
 */
@Entity
@Table(name = "officer_traffic_violation")
@Where(clause = "is_deleted=0")
public class OfficerTrafficViolationDO extends  BaseEntity{
    private OfficerBasicInfoDO officerBasicInfo;
    private String year;
    private Integer quantity;
    private Integer point;
    private Integer amount;
    @JsonBackReference
    @ManyToOne
    @JoinColumn
    public OfficerBasicInfoDO getOfficerBasicInfo() {
        return officerBasicInfo;
    }

    public void setOfficerBasicInfo(OfficerBasicInfoDO officerBasicInfo) {
        this.officerBasicInfo = officerBasicInfo;
    }
    @Column
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
    @Column
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    @Column
    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }
    @Column
    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
