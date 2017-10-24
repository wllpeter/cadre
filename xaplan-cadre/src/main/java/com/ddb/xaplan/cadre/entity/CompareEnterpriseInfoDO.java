package com.ddb.xaplan.cadre.entity;

import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * author:Zan Yang
 */
@Entity
@Table(name = "compare_enterprise_info")
@Where(clause = "is_deleted = 0")
public class CompareEnterpriseInfoDO extends BaseEntity{
    private String cadreIdCard;

    private String creditCode;

    private String regCode;

    private String name;

    private String regType;

    private String enterpriseType;

    private String ownerName;

    private String ownerId;

    private String capital;

    private String businessRange;

    @Column
    public String getCadreIdCard() {
        return cadreIdCard;
    }

    public void setCadreIdCard(String cadreIdCard) {
        this.cadreIdCard = cadreIdCard;
    }

    @Column
    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }

    @Column
    public String getRegCode() {
        return regCode;
    }

    public void setRegCode(String regCode) {
        this.regCode = regCode;
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column
    public String getRegType() {
        return regType;
    }

    public void setRegType(String regType) {
        this.regType = regType;
    }

    @Column
    public String getEnterpriseType() {
        return enterpriseType;
    }

    public void setEnterpriseType(String enterpriseType) {
        this.enterpriseType = enterpriseType;
    }

    @Column
    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    @Column
    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    @Column
    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    @Column(name="business_range",length = 16777215)
    public String getBusinessRange() {
        return businessRange;
    }

    public void setBusinessRange(String businessRange) {
        this.businessRange = businessRange;
    }
}
