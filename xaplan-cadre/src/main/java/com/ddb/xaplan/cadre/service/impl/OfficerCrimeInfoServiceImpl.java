package com.ddb.xaplan.cadre.service.impl;

import com.ddb.xaplan.cadre.dao.OfficerCrimeInfoDao;
import com.ddb.xaplan.cadre.entity.OfficerCrimeInfoDO;
import com.ddb.xaplan.cadre.entity.StatisticsBean;
import com.ddb.xaplan.cadre.service.OfficerCrimeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 付鸣 on 2017/10/17.
 * 立案查处
 */

@Service("officerCrimeInfoServiceImpl")
public class OfficerCrimeInfoServiceImpl extends BaseServiceImpl<OfficerCrimeInfoDO> implements OfficerCrimeInfoService {
    @Autowired
    private OfficerCrimeInfoDao officerCrimeInfoDao;
    /**
     * 本年立案查处记录数量
     */
    @Override
    public int getCrimeCount(int areaId) {
        if(areaId==0){
            return this.officerCrimeInfoDao.getSumCrimeCount(0);
        }else {
            return this.officerCrimeInfoDao.getCrimeCount(areaId, 0);
        }
    }

    /**
     * 计算本年与去年相比立案查处数量增长比例
     */
    @Override
    public String getCrimeProportion(int areaId) {
        double year;
        double yest;
        if(areaId==0){
            year = this.officerCrimeInfoDao.getSumCrimeCount(0);
            yest = this.officerCrimeInfoDao.getSumCrimeCount(1);
        }else {
            year = this.officerCrimeInfoDao.getCrimeCount(areaId, 0);
            yest = this.officerCrimeInfoDao.getCrimeCount(areaId, 1);
        }
        if(yest==0){
            return "-";
        }else {
            int proportion = (int)Math.ceil(((year - yest) / yest * 100.0));
            return Integer.toString(proportion);
        }
    }

    /**
     * 每年立案数量
     */
    @Override
    public List<StatisticsBean> getGroupYearCrimeCount(int areaId) {
        List<Object[]> objList=new ArrayList<Object[]>();
        if(areaId==0){
            objList= this.officerCrimeInfoDao.getGroupYearSumCrimeCount();
        }else{
            objList= this.officerCrimeInfoDao.getGroupYearCrimeCount(areaId);
        }
        List<StatisticsBean> list=new ArrayList<StatisticsBean>();
        for (Object[] item: objList) {
            StatisticsBean statis=new StatisticsBean();
            statis.setName(item[0].toString());
            statis.setCount(Integer.parseInt(item[1].toString()));
            list.add(statis);
        }
        return list;
    }

    /**
     * 犯罪分类数量
     */
    @Override
    public List<StatisticsBean> getGroupCrimeCount(int areaId) {
        List<Object[]> objList=new ArrayList<Object[]>();
        if(areaId==0){
            objList= this.officerCrimeInfoDao.getGroupSumCrimeCount();
        }else{
            objList= this.officerCrimeInfoDao.getGroupCrimeCount(areaId);
        }
        List<StatisticsBean> list=new ArrayList<StatisticsBean>();
        for (Object[] item: objList) {
            StatisticsBean statis=new StatisticsBean();
            statis.setName(item[0].toString());
            statis.setCount(Integer.parseInt(item[1].toString()));
            list.add(statis);
        }
        return list;
    }


}
