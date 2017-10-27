package com.ddb.xaplan.cadre.dao;

import com.ddb.xaplan.cadre.entity.IllegalStatisticsDO;
import com.ddb.xaplan.cadre.entity.LetterNatureDO;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IllegalStatisticsDao extends BaseDao<IllegalStatisticsDO>{
    @Query(value = "select year(occur_date),count(*) from illegal_statistics group by year(occur_date)", nativeQuery = true)
    List<Object[]> getGroupYearIllegalCount();

    @Query(value = "select year(occur_date),count(*) from illegal_statistics where area_id = ?1 group by year(occur_date)", nativeQuery = true)
    List<Object[]> getGroupYearIllegalCount(int areaId);

    @Query(value = "select result,count(*) from illegal_statistics where type = '政纪处分' group by result", nativeQuery = true)
    List<Object[]> getGroupPunishmentIllegalCount();

    @Query(value = "select result,count(*) from illegal_statistics where type = '政纪处分' AND  area_id = ?1 group by result", nativeQuery = true)
    List<Object[]> getGroupPunishmentIllegalCount(int areaId);

    @Query(value = "select result,count(*) from illegal_statistics where type = '党纪处分' group by result", nativeQuery = true)
    List<Object[]> getGroupPartyIllegalCount();

    @Query(value = "select result,count(*) from illegal_statistics where type = '党纪处分' and area_id = ?1 group by result", nativeQuery = true)
    List<Object[]> getGroupPartyIllegalCount(int areaId);

    @Query(value = "select brief,count(*) from illegal_statistics where area_id = ?1 group by brief", nativeQuery = true)
    List<Object[]> getGroupBriefIllegalCount(int areaId);

    @Query(value = "select brief,count(*) from illegal_statistics group by brief", nativeQuery = true)
    List<Object[]> getGroupBriefIllegalCount();
}
