package com.ddb.xaplan.cadre.service;


import com.ddb.xaplan.cadre.entity.OfficerCrimeInfoDO;
import com.ddb.xaplan.cadre.entity.OfficerLettersInfoDO;


/**
 * Created by 付鸣 on 2017/10/25.
 * 信访举报
 */
public interface OfficerLettersInfoService extends BaseService<OfficerLettersInfoDO>{
    /**
     * 本年信访举报的数量
     */
    public int getLettersCount(int areaId);

    /**
     * 本年与去年信访举报数量比例饿
     */
    public String getLettersProportion(int areaId);


}
