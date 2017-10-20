package com.ddb.xaplan.cadre.entity;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by 王凯斌 on 2017/10/20.
 */
@Entity
@Table(name = "medical_reimburse")
@Where(clause = "is_deleted=0")
public class MedicalReimburseDO extends BaseEntity {

    //报销人名称
    private String name;

    //报销人身份证
    private String idCard;

    //是否住院
    private Boolean isHospitalized;

    //住院天数
    private Integer hospitalizedDuration;

    //报销金额
    private BigDecimal amount;

    //病情
    private String diseaseName;

    //组织
    private String organization;

    //地区
    private AreaDO area;

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column
    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    @Column
    public Boolean getHospitalized() {
        return isHospitalized;
    }

    public void setHospitalized(Boolean hospitalized) {
        isHospitalized = hospitalized;
    }

    @Column
    public Integer getHospitalizedDuration() {
        return hospitalizedDuration;
    }

    public void setHospitalizedDuration(Integer hospitalizedDuration) {
        this.hospitalizedDuration = hospitalizedDuration;
    }

    @Column
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Column
    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    @Column
    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    @ManyToOne
    @JoinColumn
    public AreaDO getArea() {
        return area;
    }

    public void setArea(AreaDO area) {
        this.area = area;
    }
}
