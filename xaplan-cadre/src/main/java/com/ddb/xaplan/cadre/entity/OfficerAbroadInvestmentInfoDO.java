package com.ddb.xaplan.cadre.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by 付鸣 on 2017/10/18.
 * 国外投资信息
 */
@Entity
@Table(name="officer_abroad_investment_info")
@Where(clause = "is_deleted=0")
public class OfficerAbroadInvestmentInfoDO extends BaseEntity{
    //干部信息外键
    private OfficerBasicInfoDO officerBasicInfo;

    //投资人姓名
    private String ownerName;

    //投资的国家（地区）及城市
    private String ownerArea;

    //投资情况
    private String situation;

    //币种
    private String currency;

    //投资金额
    private BigDecimal amount;

    //干部信息   一对一的关系
    @JsonBackReference
    @OneToOne
    @JoinColumn
    public OfficerBasicInfoDO getOfficerBasicInfo() {
        return officerBasicInfo;
    }
    public void setOfficerBasicInfo(OfficerBasicInfoDO officerBasicInfo) {
        this.officerBasicInfo = officerBasicInfo;
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
    public String getSituation() {
        return situation;
    }
    public void setSituation(String situation) {
        this.situation = situation;
    }

    @Column
    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Column
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
