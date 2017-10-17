package com.ddb.xaplan.cadre.entity;

import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * Created by 王凯斌 on 2017/10/17.
 */
@Entity
@Table(name = "sys_area")
@Where(clause = "is_deleted=0")
public class AreaDO extends BaseEntity{

    //名称
    private String name;

    //父级行政区
    private AreaDO parent;

    //等级，县级为3，乡为4，村为5
    private Integer grade;

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    @JoinColumn
    public AreaDO getParent() {
        return parent;
    }

    public void setParent(AreaDO parent) {
        this.parent = parent;
    }

    @Column
    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }
}
