package com.ddb.xaplan.cadre.service;

import com.ddb.xaplan.cadre.entity.ReportedDO;
import com.ddb.xaplan.cadre.entity.StatisticsBean;

import java.util.List;

public interface ReportedService extends BaseService<ReportedDO>{
    List<StatisticsBean> getBeReportedList(int areaId);
}
