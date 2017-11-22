package com.ddb.xaplan.cadre.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by 付鸣 on 2017/10/17.
 * 培训信息
 */
@Entity
@Table(name="officer_training_info")
@Where(clause = "is_deleted=0")
public class OfficerTrainingInfoDO extends BaseEntity{
    //干部信息外键
    private OfficerBasicInfoDO officerBasicInfo;

    //培训时间
    private Date trainingDate;

    //培训名称
    private String name;

    //培训班次
    private String clazz;

    //网络培训总学时
    private Integer hours;


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
    public Date getTrainingDate() {
        return trainingDate;
    }

    public void setTrainingDate(Date trainingDate) {
        this.trainingDate = trainingDate;
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column
    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    @Column
    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }
}
