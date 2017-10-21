package com.ddb.xaplan.cadre.entity;

import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by 陈亚兰 on 2017/10/21.
 * 助学金信息对比
 */
@Entity
@Table(name="compare_scholarship")
@Where(clause = "is_deleted=0")
public class CompareScholarshipDO extends BaseEntity {

    private static final long serialVersionUID = 2493875789699925599L;

    private String name;

    private String type;//助学金类别

    private String year;//年度

    private Float amount;//金额

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Column
    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }
}
