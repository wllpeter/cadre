package com.ddb.xaplan.cadre.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * Created by 陈亚兰 on 2017/10/18.
 */
@Entity
@Table(name="officer_abroad_deposit_info")
@Where(clause = "is_deleted=0")
public class OfficerAbroadDepositInfoDO extends BaseEntity {

    private static final long serialVersionUID = -1021024046233463765L;

    private OfficerBasicInfoDO officerBasicInfoDO;//干部信息，外键

    private String ownerName;//存款人姓名

    private String ownerArea;//存款国家（地区）

    private String orgName;//开户银行或金融机构

    private String currency;//币种

    private Float amount;//金额

    @JsonBackReference
    @OneToOne
    @JoinColumn
    public OfficerBasicInfoDO getOfficerBasicInfoDO() {
        return officerBasicInfoDO;
    }

    public void setOfficerBasicInfoDO(OfficerBasicInfoDO officerBasicInfoDO) {
        this.officerBasicInfoDO = officerBasicInfoDO;
    }

    @Column
    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    @Column
    public String getOwnerArea() {
        return ownerArea;
    }

    public void setOwnerArea(String ownerArea) {
        this.ownerArea = ownerArea;
    }

    @Column
    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    @Column
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Column
    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }
}
