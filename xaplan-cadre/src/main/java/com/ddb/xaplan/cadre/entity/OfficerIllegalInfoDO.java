package com.ddb.xaplan.cadre.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by 付鸣 on 2017/10/17.
 * 违纪涉法
 */


@Entity
@Table(name = "officer_illegal_info")
@Where(clause = "is_deleted=0")
public class OfficerIllegalInfoDO extends BaseEntity {
    //干部信息外键
    private OfficerBasicInfoDO officerBasicInfo;

    //时间
    private Date occur_date;

    //简要案情
    private String brief;

    //处分类别
    private String type;

    //处分结果
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
    public Date getOccur_date() {
        return occur_date;
    }

    public void setOccur_date(Date occur_date) {
        this.occur_date = occur_date;
    }
    @Column
    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    @Column
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    @Column
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
