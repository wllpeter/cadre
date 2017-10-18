package com.ddb.xaplan.cadre.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by 付鸣 on 2017/10/18.
 * 房产信息
 */
@Entity
@Table(name = "officer_estate_info")
@Where(clause = "is_deleted=0")
public class OfficerEstateInfoDO extends BaseEntity{
    //干部信息外键
    private OfficerBasicInfoDO officerBasicInfo;

    //产权人姓名
    private String owerName;

    //产权人身份证号
    private String ownerId;

    //关系
    private String relation;

    //房产来源（去向）
    private String source;

    //具体地址
    private String address;

    //建筑面积
    private double proportion;

    //房产性质
    private String type;

    //交易时间
    private Date dealDate;

    //交易价格
    private BigDecimal price;

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
    public String getOwerName() {
        return owerName;
    }

    public void setOwerName(String owerName) {
        this.owerName = owerName;
    }

    @Column
    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    @Column
    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    @Column
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Column
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column
    public double getProportion() {
        return proportion;
    }

    public void setProportion(double proportion) {
        this.proportion = proportion;
    }

    @Column
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column
    public Date getDealDate() {
        return dealDate;
    }

    public void setDealDate(Date dealDate) {
        this.dealDate = dealDate;
    }

    @Column
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
