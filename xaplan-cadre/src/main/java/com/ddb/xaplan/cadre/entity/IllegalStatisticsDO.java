package com.ddb.xaplan.cadre.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "illegal_statistics")
public class IllegalStatisticsDO extends  BaseEntity{
    private String name;
    private Date occurDate;
    private String brief;
    private String result;
    private String type;
    private AreaDO area;
    @JoinColumn
    @ManyToOne
    public AreaDO getArea() {
        return area;
    }

    public void setArea(AreaDO area) {
        this.area = area;
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column
    public Date getOccurDate() {
        return occurDate;
    }

    public void setOccurDate(Date occurDate) {
        this.occurDate = occurDate;
    }

    @Column
    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    @Column
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Column
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
