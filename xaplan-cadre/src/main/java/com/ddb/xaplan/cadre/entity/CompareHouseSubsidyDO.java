package com.ddb.xaplan.cadre.entity;

import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by 陈亚兰 on 2017/10/21.
 * 危房补助对比
 */
@Entity
@Table(name="compare_house_subsidy")
@Where(clause = "is_deleted=0")
public class CompareHouseSubsidyDO extends BaseEntity{
    private static final long serialVersionUID = -7878644913662438194L;

    private String name;

    private String address;

    private String year;

    private String level;//危房等级

    private Float amount;//金额

    private String idCard;//身份证

    @Column
    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Column
    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Column
    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }
}
