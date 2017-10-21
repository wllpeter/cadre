package com.ddb.xaplan.cadre.entity;

import com.ddb.xaplan.cadre.enums.Gender;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * author:Zan Yang
 */
@Entity
@Table(name = "compare_enterprise_info")
@Where(clause = "is_delete = 0")
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

    private String range;

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

    @Column
    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }
}
