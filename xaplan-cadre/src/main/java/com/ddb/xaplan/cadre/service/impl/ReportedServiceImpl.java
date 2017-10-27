package com.ddb.xaplan.cadre.service.impl;

import com.ddb.xaplan.cadre.dao.ReportedDao;
import com.ddb.xaplan.cadre.entity.ReportedDO;
import com.ddb.xaplan.cadre.entity.StatisticsBean;
import com.ddb.xaplan.cadre.service.BaseService;
import com.ddb.xaplan.cadre.service.ReportedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("reportedServiceImpl")
public class ReportedServiceImpl extends BaseServiceImpl<ReportedDO> implements ReportedService {
    @Autowired
    private ReportedDao reportedDao;
    @Override
    public List<StatisticsBean> getBeReportedList(int areaId) {
        List<Object[]> ret;
        if (areaId == 0) {
            ret = this.reportedDao.getBeReportedList();
        } else {
            ret = this.reportedDao.getBeReportedList(areaId);
        }
        if(null != ret && ret.size() > 0 ){
            List<StatisticsBean> result = new ArrayList<>();
            for(Object[] item : ret){
                result.add(new StatisticsBean(item[0].toString(),Integer.valueOf(item[1].toString())));
                continue;
            }
            return result;
        }
        return null;
    }

    @Override
    public List<StatisticsBean> getBeReportedByYear(int areaId) {
        List<Object[]> ret;
        if (areaId == 0) {
            ret = this.reportedDao.getBeReportedByYear();
        } else {
            ret = this.reportedDao.getBeReportedByYear(areaId);
        }
        if(null != ret && ret.size() > 0 ){
            List<StatisticsBean> result = new ArrayList<>();
            for(Object[] item : ret){
                result.add(new StatisticsBean(item[0].toString(),Integer.valueOf(item[1].toString())));
                continue;
            }
            return result;
        }
        return null;
    }
}
