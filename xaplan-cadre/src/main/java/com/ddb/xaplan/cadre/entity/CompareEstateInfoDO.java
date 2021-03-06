package com.ddb.xaplan.cadre.entity;

import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="compare_estate_info")
@Where(clause = "is_deleted = 0")
public class CompareEstateInfoDO extends BaseEntity {
    private String cadreIdCard;//干部身份证
    private String owerName;//户主名字
    private String ownerId;//户主身份证
    private String relation;//关系
    private String source;//房屋来源
    private String address;//地址
    private String proportion;//面积
    private String type;//类型
    private Date dealDate;//交易日期
    private String price;//价格

    @Column
    public String getCadreIdCard() {
        return cadreIdCard;
    }

    public void setCadreIdCard(String cadreIdCard) {
        this.cadreIdCard = cadreIdCard;
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
    public String getProportion() {
        return proportion;
    }

    public void setProportion(String proportion) {
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
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
