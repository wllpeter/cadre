package com.ddb.xaplan.cadre.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by 陈亚兰 on 2017/10/18.
 * 审计
 */
@Entity
@Table(name="officer_audit_info")
@Where(clause = "is_deleted=0")
public class OfficerAuditInfoDO extends BaseEntity {

    private static final long serialVersionUID = -5492637344111607009L;

    private OfficerBasicInfoDO officerBasicInfoDO;//干部信息外键

    private String year;//年度

    private String reportNumber;//审计报告文书

    private String organization;//被审计单位

    private String title;//职务

    private Date startDate;//任职时间

    private String performance;//主要业绩

    private String problem;//存在问题

    private Double amount;//金额

    private String evaluation;//审计评价

    @JsonBackReference
    @OneToOne
    @JoinColumn
    public OfficerBasicInfoDO getOfficerBasicInfoDO() {
        return officerBasicInfoDO;
    }

    public void setOfficerBasicInfoDO(OfficerBasicInfoDO officerBasicInfoDO) {
        this.officerBasicInfoDO = officerBasicInfoDO;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getReportNumber() {
        return reportNumber;
    }

    public void setReportNumber(String reportNumber) {
        this.reportNumber = reportNumber;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Column(name="performance",length = 16777215)
    public String getPerformance() {
        return performance;
    }

    public void setPerformance(String performance) {
        this.performance = performance;
    }

    @Column(name="problem",length = 16777215)
    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Column(name="evaluation",length = 16777215)
    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }
}
