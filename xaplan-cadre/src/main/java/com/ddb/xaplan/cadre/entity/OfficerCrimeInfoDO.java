package com.ddb.xaplan.cadre.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by 付鸣 on 2017/10/17.
 * 立案查处
 */

@Entity
@Table(name = "officer_crime_info")
@Where(clause = "is_deleted=0")
public class OfficerCrimeInfoDO extends BaseEntity{

    //干部信息外键
    private OfficerBasicInfoDO officerBasicInfo;

    //时间
    private Date occurDate;

    //犯罪性质
    private String type;

    //简要案情
    private String brief;

    //判决结果
    private String result;

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
    public Date getOccurDate() {
        return occurDate;
    }
    public void setOccurDate(Date occurDate) {
        this.occurDate = occurDate;
    }

    @Column
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    @Column
    public String getBrief() {
        return brief;
    }
    public void setBrief(String brief) {
        this.brief = brief;
    }

    @Column
    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }
}
