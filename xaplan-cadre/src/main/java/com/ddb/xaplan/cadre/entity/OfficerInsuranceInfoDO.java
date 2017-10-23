package com.ddb.xaplan.cadre.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * Created by 陈亚兰 on 2017/10/18.
 */
@Entity
@Table(name="officer_insurance_info")
@Where(clause = "is_deleted=0")
public class OfficerInsuranceInfoDO extends BaseEntity{

    private static final long serialVersionUID = 5494873650682305597L;

    private OfficerBasicInfoDO officerBasicInfoDO; //干部信息 ，外键

    private String ownerName;//投保人姓名

    private String insurance;//保险公司名称

    private String number;//保单号

    private String companyName;//保险公司名称

    private Float amount;//积累缴纳保费用、投资金

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
    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    @Column
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Column
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Column
    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }
}
