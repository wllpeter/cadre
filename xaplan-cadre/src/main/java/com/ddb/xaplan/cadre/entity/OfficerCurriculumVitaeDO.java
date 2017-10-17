package com.ddb.xaplan.cadre.entity;

import com.ddb.xaplan.cadre.enums.TitleLevel;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by 王凯斌 on 2017/10/17.
 */
@Entity
@Table(name = "officer_curriculum_vitae")
@Where(clause = "is_deleted=0")
public class OfficerCurriculumVitaeDO extends BaseEntity{

    private OfficerBasicInfoDO officerBasicInfo;

    private Date startDate;

    private Date endDate;

    private String organization;

    private String title;

    private TitleLevel titleLevel;

    @Column
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
