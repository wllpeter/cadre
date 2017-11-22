package com.ddb.xaplan.cadre.vo;

/**
 * Created by 王凯斌 on 2017/10/28.
 */
public class ComparedItem {

    Object value;

    Boolean isNormal;

    String describe;

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

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public static ComparedItem normal(Object value){
        ComparedItem item = new ComparedItem();
        item.setValue(value);
        item.setNormal(true);
        return item;
    }

    public static ComparedItem abnormal(Object value,String describe){
        ComparedItem item = new ComparedItem();
        item.setValue(value);
        item.setNormal(false);
        item.setDescribe(describe);
        return item;
    }
}
