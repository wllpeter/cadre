package com.ddb.xaplan.cadre.entity;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

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

    //花费总额
    private BigDecimal expense_amount;

    //报销金额
    private BigDecimal reimbursement_amount;

    //病情
    private String diseaseName;

    //组织
    private String organization;

    //地区
    private AreaDO area;

    //地区ids
    private String areaIds;

    //地址
    private String address;

    //入院时间
    private Date startDate;

    //出院时间
    private Date endDate;

    //补偿时间
    private Date occurDate;

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
    public BigDecimal getExpense_amount() {
        return expense_amount;
    }

    public void setExpense_amount(BigDecimal expense_amount) {
        this.expense_amount = expense_amount;
    }

    @Column
    public BigDecimal getReimbursement_amount() {
        return reimbursement_amount;
    }

    public void setReimbursement_amount(BigDecimal reimbursement_amount) {
        this.reimbursement_amount = reimbursement_amount;
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

    @Column
    public String getAreaIds() {
        return areaIds;
    }

    public void setAreaIds(String areaIds) {
        this.areaIds = areaIds;
    }

    @Column
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
    public Date getOccurDate() {
        return occurDate;
    }

    public void setOccurDate(Date occurDate) {
        this.occurDate = occurDate;
    }
}
