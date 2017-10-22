package com.ddb.xaplan.cadre.entity;

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

    private AreaDO area;

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

    @Column(length = 50000)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
