package com.ddb.xaplan.cadre.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * Created by 王凯斌 on 2017/10/18.
 */
@Entity
@Table(name = "officer_stock_info")
@Where(clause = "is_deleted=0")
public class OfficerStockInfoDO extends BaseEntity{

    private OfficerBasicInfoDO officerBasicInfo;

    private String ownerName;

    private String stockName;

    private Integer quantity;

    private Double price;

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
    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    @Column
    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    @Column
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Column
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
