package com.ddb.xaplan.cadre.entity;

import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by 陈亚兰 on 2017/10/21.
 * 优辅信息比对
 */
@Entity
@Table(name="compare_special_care")
@Where(clause = "is_deleted=0")
public class CompareSpecialCareDO extends BaseEntity{

    private static final long serialVersionUID = 8652257050774755288L;

    private String name;//姓名

    private String idCard;//身份证号

    private String type;//性质

    private String level;//等级

    private Float amount;//优抚款

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
    public String getType() {
        return type;
    }

    @Column
    public void setType(String type) {
        this.type = type;
    }

    @Column
    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Column
    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }
}
