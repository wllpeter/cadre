package com.ddb.xaplan.cadre.entity;

import com.ddb.xaplan.cadre.enums.Bachelor;
import com.ddb.xaplan.cadre.enums.EducationLevel;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by 陈亚兰 on 2017/10/17.
 */
@Entity
@Table(name="officer_education_info")
@Where(clause = "is_deleted=0")
public class OfficerEducationInfoDO extends BaseEntity{

    private static final long serialVersionUID = 8228100115306758793L;

    private OfficerBasicInfoDO officerBasicInfoDO;//干部信息外键

    private Bachelor bachelor;//学位

    private EducationLevel educationLevel;//学历

    private String institution;//学制

    private Date startDate;//入学时间

    private Date endDate;//毕肄时间

    private String schoolName;//学校名字

    private String discipline;//所学专业

    private String situation;//学业完成

    private Date bachelorDate;//学位授予时间

    private String bachelorOrg;//学位授予单位

    private String certificate;//证书编号

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
    public Bachelor getBachelor() {
        return bachelor;
    }

    public void setBachelor(Bachelor bachelor) {
        this.bachelor = bachelor;
    }

    @Column
    public EducationLevel getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(EducationLevel educationLevel) {
        this.educationLevel = educationLevel;
    }

    @Column
    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
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
    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    @Column
    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    @Column
    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    @Column
    public Date getBachelorDate() {
        return bachelorDate;
    }

    public void setBachelorDate(Date bachelorDate) {
        this.bachelorDate = bachelorDate;
    }

    @Column
    public String getBachelorOrg() {
        return bachelorOrg;
    }

    public void setBachelorOrg(String bachelorOrg) {
        this.bachelorOrg = bachelorOrg;
    }

    @Column
    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }
}
