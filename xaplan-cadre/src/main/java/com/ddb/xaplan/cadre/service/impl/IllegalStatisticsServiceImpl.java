package com.ddb.xaplan.cadre.service.impl;

import com.ddb.xaplan.cadre.dao.IllegalStatisticsDao;
import com.ddb.xaplan.cadre.dao.LetterNatureDao;
import com.ddb.xaplan.cadre.entity.IllegalStatisticsDO;
import com.ddb.xaplan.cadre.entity.LetterNatureDO;
import com.ddb.xaplan.cadre.entity.StatisticsBean;
import com.ddb.xaplan.cadre.service.IllegalStatisticsService;
import com.ddb.xaplan.cadre.service.LetterNatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("IllegalStatisticsServiceImpl")
public class IllegalStatisticsServiceImpl extends BaseServiceImpl<IllegalStatisticsDO> implements IllegalStatisticsService{
    @Autowired
    private IllegalStatisticsDao illegalStatisticsDao;

    @Override
    public List<StatisticsBean> getGroupPunishmentIllegalCount(int areaId) {
        List<Object[]> objList=new ArrayList<Object[]>();
        if(areaId==0){
            objList= this.illegalStatisticsDao.getGroupPunishmentIllegalCount();
        }else{
            objList= this.illegalStatisticsDao.getGroupPunishmentIllegalCount(areaId);
        }
        if(null == objList || objList.size() == 0){
            return null;
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

    @Override
    public List<StatisticsBean> getGroupYearIllegalCount(int areaId) {
        List<Object[]> objList=new ArrayList<Object[]>();
        if(areaId==0){
            objList= this.illegalStatisticsDao.getGroupYearIllegalCount();
        }else{
            objList= this.illegalStatisticsDao.getGroupYearIllegalCount(areaId);
        }
        if(null == objList || objList.size() == 0){
            return null;
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

    @Override
    public List<StatisticsBean> getGroupBriefIllegalCount(int areaId) {
        List<Object[]> objList=new ArrayList<Object[]>();
        if(areaId==0){
            objList= this.illegalStatisticsDao.getGroupBriefIllegalCount();
        }else{
            objList= this.illegalStatisticsDao.getGroupBriefIllegalCount(areaId);
        }
        if(null == objList || objList.size() == 0){
            return null;
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

    @Override
    public List<StatisticsBean> getGroupPartyIllegalCount(int areaId) {
        List<Object[]> objList=new ArrayList<Object[]>();
        if(areaId==0){
            objList= this.illegalStatisticsDao.getGroupPartyIllegalCount();
        }else{
            objList= this.illegalStatisticsDao.getGroupPartyIllegalCount(areaId);
        }
        if(null == objList || objList.size() == 0){
            return null;
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
