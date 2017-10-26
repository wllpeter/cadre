package com.ddb.xaplan.cadre.service;


import com.ddb.xaplan.cadre.entity.OfficerIllegalInfoDO;
import com.ddb.xaplan.cadre.entity.StatisticsBean;

import java.util.List;

/**
 * Created by 付鸣 on 2017/10/17.
 * 违纪涉法
 */
public interface OfficerIllegalInfoService extends BaseService<OfficerIllegalInfoDO>{

    /**
     * 本年违纪记录数量
     */
    public int getIllegalCount(int areaId);

    /**
     * 计算本年与去年相比违纪数量增长比例
     */
    public String getIllegalProportion(int areaId);

    /**
     * 每年违纪数量
     */
    public List<StatisticsBean> getGroupYearIllegalCount(int areaId);

    /**
     * 党纪处分分类
     */
    public List<StatisticsBean> getGroupPunishmentIllegalCount( int areaId);

    /**
     * 性质分类
     */
    public List<StatisticsBean> getGroupBriefIllegalCount(int areaId);



}
