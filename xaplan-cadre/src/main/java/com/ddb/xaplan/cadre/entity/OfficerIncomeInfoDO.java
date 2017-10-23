package com.ddb.xaplan.cadre.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by 付鸣 on 2017/10/18.
 * 收入信息
 */
@Entity
@Table(name="officer_income_info")
@Where(clause = "is_deleted=0")
public class OfficerIncomeInfoDO extends  BaseEntity{

    //干部信息外键
    private OfficerBasicInfoDO officerBasicInfo;

    private String idCard;//身份证号

    //工资（含津贴、补贴）
    private BigDecimal salary;

    //奖金
    private BigDecimal bonus;

    //其它
    private BigDecimal others;

    //合计
    private BigDecimal joCount;

    //讲学
    private BigDecimal teching;

    //写作
    private BigDecimal writting;

    //咨询
    private BigDecimal consult;

    //审稿
    private BigDecimal auditing;

    //书画
    private BigDecimal art;

    //其它
    private BigDecimal other;

    //合计
    private BigDecimal nonJobCount;

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
    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    @Column
    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Column
    public BigDecimal getBonus() {
        return bonus;
    }

    public void setBonus(BigDecimal bonus) {
        this.bonus = bonus;
    }

    @Column
    public BigDecimal getOthers() {
        return others;
    }

    public void setOthers(BigDecimal others) {
        this.others = others;
    }

    @Column
    public BigDecimal getJoCount() {
        return joCount;
    }

    public void setJoCount(BigDecimal joCount) {
        this.joCount = joCount;
    }

    @Column
    public BigDecimal getTeching() {
        return teching;
    }

    public void setTeching(BigDecimal teching) {
        this.teching = teching;
    }

    @Column
    public BigDecimal getWritting() {
        return writting;
    }

    public void setWritting(BigDecimal writting) {
        this.writting = writting;
    }

    @Column
    public BigDecimal getConsult() {
        return consult;
    }

    public void setConsult(BigDecimal consult) {
        this.consult = consult;
    }

    @Column
    public BigDecimal getAuditing() {
        return auditing;
    }

    public void setAuditing(BigDecimal auditing) {
        this.auditing = auditing;
    }

    @Column
    public BigDecimal getArt() {
        return art;
    }

    public void setArt(BigDecimal art) {
        this.art = art;
    }

    @Column
    public BigDecimal getOther() {
        return other;
    }

    public void setOther(BigDecimal other) {
        this.other = other;
    }

    @Column
    public BigDecimal getNonJobCount() {
        return nonJobCount;
    }

    public void setNonJobCount(BigDecimal nonJobCount) {
        this.nonJobCount = nonJobCount;
    }
}
