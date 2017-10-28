package com.ddb.xaplan.cadre.service;

import com.ddb.xaplan.cadre.entity.IllegalStatisticsDO;
import com.ddb.xaplan.cadre.entity.LetterNatureDO;
import com.ddb.xaplan.cadre.entity.StatisticsBean;

import java.util.List;

public interface IllegalStatisticsService extends BaseService<IllegalStatisticsDO> {

    List<StatisticsBean> getGroupPunishmentIllegalCount(int areaId);

    List<StatisticsBean> getGroupYearIllegalCount(int areaId);

    List<StatisticsBean> getGroupBriefIllegalCount(int areaId);

    List<StatisticsBean> getGroupPartyIllegalCount(int areaId);
}
