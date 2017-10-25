package com.ddb.xaplan.cadre.service;


import com.ddb.xaplan.cadre.entity.OfficerCrimeInfoDO;


/**
 * Created by 付鸣 on 2017/10/17.
 * 立案查处
 */
public interface OfficerCrimeInfoService extends BaseService<OfficerCrimeInfoDO>{

    /**
     * 本年立案查处记录数量
     */
    public int getCrimeCount(int areaId);

    /**
     * 计算本年与去年相比立案查处数量增长比例
     */
    public String getCrimeProportion(int areaId);


}
