package com.ddb.xaplan.cadre.entity;

/**
 * Created by 付鸣 on 2017/10/26.
 * 封装统计结果实体
 */
public class StatisticsBean {
    private String name;
    private int count;

    public StatisticsBean() {
    }

    public StatisticsBean(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
