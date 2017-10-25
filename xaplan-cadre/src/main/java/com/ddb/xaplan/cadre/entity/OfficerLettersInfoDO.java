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
    private AreaDO area;
    //来访
    private int visitCount;
    //来信
    private int inLetterCount;
    //上级转来
    private int hightLevelTransferCount;
    //电话
    private int phoneCount;
    //网络与其它
    private int networkOtherCount;

    @OneToOne
    @JoinColumn
    public AreaDO getArea() {
        return area;
    }

    public void setArea(AreaDO area) {
        this.area = area;
    }
    @Column
    public int getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(int visitCount) {
        this.visitCount = visitCount;
    }
    @Column
    public int getInLetterCount() {
        return inLetterCount;
    }

    public void setInLetterCount(int inLetterCount) {
        this.inLetterCount = inLetterCount;
    }
    @Column
    public int getHightLevelTransferCount() {
        return hightLevelTransferCount;
    }

    public void setHightLevelTransferCount(int hightLevelTransferCount) {
        this.hightLevelTransferCount = hightLevelTransferCount;
    }
    @Column
    public int getPhoneCount() {
        return phoneCount;
    }

    public void setPhoneCount(int phoneCount) {
        this.phoneCount = phoneCount;
    }
    @Column
    public int getNetworkOtherCount() {
        return networkOtherCount;
    }

    public void setNetworkOtherCount(int networkOtherCount) {
        this.networkOtherCount = networkOtherCount;
    }

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



}
