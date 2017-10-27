package com.ddb.xaplan.cadre.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

@ApiModel(value = "ReimburseDetailVO",description = "报销排名返回模型")
public class ReimburseDetailVO {
    //排名
    @ApiModelProperty(value = "排名")
    private int rank;
    //名称
    @ApiModelProperty(value = "名称")
    private String name;
    //身份证
    @ApiModelProperty(value = "身份证")
    private String idCard;
    //报销次数
    @ApiModelProperty(value = "报销次数")
    private int reimburseTime;
    //报销金额
    @ApiModelProperty(value = "报销金额")
    private BigDecimal reimburseAmount;
    //住院天数
    @ApiModelProperty(value = "住院天数")
    private int hospitalizedDuration;
    //门诊次数
    @ApiModelProperty(value = "门诊次数")
    private int clinicTime;
    //病因
    @ApiModelProperty(value = "病因")
    private String diseaseName;
    //住院次数
    @ApiModelProperty(value = "住院次数")
    private int hospitalizedTime;
    public ReimburseDetailVO() {
    }

    public ReimburseDetailVO(String name, String idCard, int reimburseTime, BigDecimal reimburseAmount, int hospitalizedDuration, int clinicTime, String diseaseName, int hospitalizedTime) {
        this.name = name;
        this.idCard = idCard;
        this.reimburseTime = reimburseTime;
        this.reimburseAmount = reimburseAmount;
        this.hospitalizedDuration = hospitalizedDuration;
        this.clinicTime = clinicTime;
        this.diseaseName = diseaseName;
        this.hospitalizedTime = hospitalizedTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public int getReimburseTime() {
        return reimburseTime;
    }

    public void setReimburseTime(int reimburseTime) {
        this.reimburseTime = reimburseTime;
    }

    public BigDecimal getReimburseAmount() {
        return reimburseAmount;
    }

    public void setReimburseAmount(BigDecimal reimburseAmount) {
        this.reimburseAmount = reimburseAmount;
    }

    public int getHospitalizedDuration() {
        return hospitalizedDuration;
    }

    public void setHospitalizedDuration(int hospitalizedDuration) {
        this.hospitalizedDuration = hospitalizedDuration;
    }

    public int getClinicTime() {
        return clinicTime;
    }

    public void setClinicTime(int clinicTime) {
        this.clinicTime = clinicTime;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public int getHospitalizedTime() {
        return hospitalizedTime;
    }

    public void setHospitalizedTime(int hospitalizedTime) {
        this.hospitalizedTime = hospitalizedTime;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
