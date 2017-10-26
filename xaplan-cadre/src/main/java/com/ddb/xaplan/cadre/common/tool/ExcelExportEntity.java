package com.ddb.xaplan.cadre.common.tool;

import java.util.List;

public class ExcelExportEntity<T> {
    private String sheetName;
    private List<String> titles;
    private List<T> datas;
    private String[] args;

    public ExcelExportEntity(String sheetName, List<String> titles, List<T> datas, String[] args) {
        this.sheetName = sheetName;
        this.titles = titles;
        this.datas = datas;
        this.args = args;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public List<String> getTitles() {
        return titles;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    public String[] getArgs() {
        return args;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }
}
