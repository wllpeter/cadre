package com.ddb.xaplan.cadre.service.impl;

import com.ddb.xaplan.cadre.dao.OfficerIllegalInfoDao;
import com.ddb.xaplan.cadre.entity.OfficerIllegalInfoDO;
import com.ddb.xaplan.cadre.entity.StatisticsBean;
import com.ddb.xaplan.cadre.service.OfficerIllegalInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 付鸣 on 2017/10/17.
 * 违纪涉法
 */

@Service("officerIllegalInfoServiceImpl")
public class OfficerIllegalInfoServiceImpl extends BaseServiceImpl<OfficerIllegalInfoDO> implements OfficerIllegalInfoService{

    @Autowired
    private OfficerIllegalInfoDao officerIllegalInfoDao;

    /**
     * 本年各县违纪记录数量
     */
    @Override
    public int getIllegalCount(int areaId) {
        if(areaId==0){
            return this.officerIllegalInfoDao.getSumIllegalCount(0);
        }else {
            return this.officerIllegalInfoDao.getIllegalCount(areaId, 0);
        }
    }

    /**
     * 计算本年与去年相比违纪数量增长比例
     */
    @Override
    public String getIllegalProportion(int areaId) {
        double year;
        double yest;
        if(areaId==0){
            year = this.officerIllegalInfoDao.getSumIllegalCount(0);
            yest = this.officerIllegalInfoDao.getSumIllegalCount(1);
        }else {
            year = this.officerIllegalInfoDao.getIllegalCount(areaId, 0);
            yest = this.officerIllegalInfoDao.getIllegalCount(areaId, 1);
        }
        if(yest==0){
            return "-";
        }else {
            int proportion = (int) Math.ceil(((year - yest) / yest * 100.0));
            return Integer.toString(proportion);
        }
    }

    /**
     * 每年违纪数量
     */
    @Override
    public List<StatisticsBean> getGroupYearIllegalCount(int areaId) {
        List<Object[]> objList=new ArrayList<Object[]>();
        if(areaId==0){
            objList= this.officerIllegalInfoDao.getGroupYearSumIllegalCount();
        }else{
            objList= this.officerIllegalInfoDao.getGroupYearIllegalCount(areaId);
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
     * 党纪处分分类
     */
    @Override
    public List<StatisticsBean> getGroupPunishmentIllegalCount(int areaId) {
        List<Object[]> objList=new ArrayList<Object[]>();
        if(areaId==0){
            objList= this.officerIllegalInfoDao.getGroupPunishmentSumIllegalCount();
        }else{
            objList= this.officerIllegalInfoDao.getGroupPunishmentIllegalCount(areaId);
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
     * 性质分类
     */
    @Override
    public List<StatisticsBean> getGroupBriefIllegalCount(int areaId) {
        List<Object[]> objList=new ArrayList<Object[]>();
        if(areaId==0){
            objList= this.officerIllegalInfoDao.getGroupBriefSumIllegalCount();
        }else{
            objList= this.officerIllegalInfoDao.getGroupBriefIllegalCount(areaId);
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
