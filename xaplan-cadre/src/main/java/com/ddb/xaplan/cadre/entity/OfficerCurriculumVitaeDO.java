package com.ddb.xaplan.cadre.entity;

import com.ddb.xaplan.cadre.enums.TitleLevel;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by 王凯斌 on 2017/10/17.
 */
@Entity
@Table(name = "officer_curriculum_vitae")
@Where(clause = "is_deleted=0")
public class OfficerCurriculumVitaeDO extends BaseEntity{

    private OfficerBasicInfoDO officerBasicInfo;

    private Date startDate;//开始时间

    private Date endDate;//结束时间

    private String organization;//单位

    private String title;//职务

    private TitleLevel titleLevel;//职级

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
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Column
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Column
    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    @Column
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column
    public TitleLevel getTitleLevel() {
        return titleLevel;
    }

    public void setTitleLevel(TitleLevel titleLevel) {
        this.titleLevel = titleLevel;
    }
}
