package com.ddb.xaplan.cadre.vo;

/**
 * Created by 王凯斌 on 2017/10/24.
 */
public class DiseaseRankItem {

    private Integer order;

    private String name;

    private Integer nonHosCount;

    private Integer hosCount;

    private Double nonHosAmount;

    private Double hosAmount;

    public DiseaseRankItem(Integer order, String name, Integer nonHosCount, Integer hosCount, Double nonHosAmount, Double hosAmount) {
        this.order = order;
        this.name = name;
        this.nonHosCount = nonHosCount;
        this.hosCount = hosCount;
        this.nonHosAmount = nonHosAmount;
        this.hosAmount = hosAmount;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNonHosCount() {
        return nonHosCount;
    }

    public void setNonHosCount(Integer nonHosCount) {
        this.nonHosCount = nonHosCount;
    }

    public Integer getHosCount() {
        return hosCount;
    }

    public void setHosCount(Integer hosCount) {
        this.hosCount = hosCount;
    }

    public Double getNonHosAmount() {
        return nonHosAmount;
    }

    public void setNonHosAmount(Double nonHosAmount) {
        this.nonHosAmount = nonHosAmount;
    }

    public Double getHosAmount() {
        return hosAmount;
    }

    public void setHosAmount(Double hosAmount) {
        this.hosAmount = hosAmount;
    }
}
