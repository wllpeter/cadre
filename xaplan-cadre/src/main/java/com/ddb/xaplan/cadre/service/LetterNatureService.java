package com.ddb.xaplan.cadre.service;

import com.ddb.xaplan.cadre.entity.LetterNatureDO;
import com.ddb.xaplan.cadre.entity.StatisticsBean;

import java.util.List;

public interface LetterNatureService extends BaseService<LetterNatureDO> {
    List<StatisticsBean> getNatureList(int areaId);

    List<StatisticsBean> getNaturesByYear(int areaId);
}
