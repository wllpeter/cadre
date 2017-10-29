package com.ddb.xaplan.cadre.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by 王凯斌 on 2017/10/17.
 * 奖励
 */
@Entity
@Table(name = "officer_prize_info")
@Where(clause = "is_deleted=0")
public class OfficerPrizeInfoDO extends BaseEntity{

    private OfficerBasicInfoDO officerBasicInfo;

    private Date prizeDate;//获奖时间

    private String name;//获奖名称

    private String organization;//单位

    private String reason;//原因

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
    public Date getPrizeDate() {
        return prizeDate;
    }

    public void setPrizeDate(Date prizeDate) {
        this.prizeDate = prizeDate;
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column
    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    @Column
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
