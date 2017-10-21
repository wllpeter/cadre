package com.ddb.xaplan.cadre.entity;

import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by 陈亚兰 on 2017/10/21.
 * 补助信息
 */
@Entity
@Table(name="compare_subsidy_info")
@Where(clause = "is_deleted=0")
public class CompareSubsidyInfoDO extends BaseEntity{
    private static final long serialVersionUID = -3716132997370442506L;
    private String name;

    private String idCard;

    private String type;

    private String address;

    private String treatment;//享受待遇

    private Float amount;//发放金额

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column
    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    @Column
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column
    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    @Column
    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }
}
