package com.ddb.xaplan.cadre.vo;

/**
 * Created by 王凯斌 on 2017/10/28.
 */
public class ComparedItem {

    Object value;

    Boolean isNormal;

    String source;

    String compared;

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Boolean getNormal() {
        return isNormal;
    }

    public void setNormal(Boolean normal) {
        isNormal = normal;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCompared() {
        return compared;
    }

    public void setCompared(String compared) {
        this.compared = compared;
    }
}
