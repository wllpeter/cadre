package com.ddb.xaplan.cadre.entity;


import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * Created by 付鸣 on 2017/10/25.
 * 信访举报统计
 */
@Entity
@Table(name = "officer_letters_info")
@Where(clause = "is_deleted=0")
public class OfficerLettersInfoDO extends  BaseEntity {

    //年份
    private int year;
    //案件数量
    private int count;
    //地区
    private AreaDO areaDO;




    @Column
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    @Column
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @OneToOne
    @JoinColumn
    public AreaDO getAreaDO() {
        return areaDO;
    }

    public void setAreaDO(AreaDO areaDO) {
        this.areaDO = areaDO;
    }
}
