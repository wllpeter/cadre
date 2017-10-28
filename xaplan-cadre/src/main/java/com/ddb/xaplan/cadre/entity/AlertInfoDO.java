package com.ddb.xaplan.cadre.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by 王凯斌 on 2017/10/19.
 */
@Entity
@Table(name = "alert_info")
@Where(clause = "is_deleted=0")
public class AlertInfoDO extends BaseEntity {

    private OfficerBasicInfoDO officerBasicInfo;

    public enum AlertType {

        BASIC,//基础数据异常

        REGISTER,//申报数据异常

        CORRUPTION//微腐败预警
    }

    private String photo;

    private AlertInfoDO.AlertType alertType;

    private Date occurDate;

    private String name;

    private String idCard;

    private String organization;

    private String title;

    private String content;

    private String description;

    private Integer amount;

    private AreaDO area;

    private String areaIds;

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
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Column
    public AlertType getAlertType() {
        return alertType;
    }

    public void setAlertType(AlertType alertType) {
        this.alertType = alertType;
    }

    @Column
    public Date getOccurDate() {
        return occurDate;
    }

    public void setOccurDate(Date occurDate) {
        this.occurDate = occurDate;
    }

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
    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    @Column
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(length = 500000)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column
    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
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

    @Transient
    public String getAreaName() {

        String result = null;

        try{
            AreaDO areaDO = getArea();
            while(areaDO!=null){
                result = areaDO.getName();
                areaDO = areaDO.getParent();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

    @Transient
    public String getOfficerId() {

        return getOfficerBasicInfo()==null?String.valueOf(getOfficerBasicInfo().getId()):null;
    }

}
