package com.ddb.xaplan.cadre.dao;

import com.ddb.xaplan.cadre.entity.LetterNatureDO;
import com.ddb.xaplan.cadre.entity.ReportedDO;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LetterNatureDao extends BaseDao<LetterNatureDO>{
    @Query(value = "select name, sum(count)from letter_nature_info where year = YEAR(SYSDATE()) group by name ",nativeQuery = true)
    List<Object[]> getLetterNatureList();

    @Query(value = "select name, sum(count)from letter_nature_info where year = YEAR(SYSDATE()) and area_id = ?1 group by name",nativeQuery = true)
    List<Object[]> getLetterNatureList(int areaId);

    @Query(value = "select year, sum(count)from letter_nature_info group by year",nativeQuery = true)
    List<Object[]> getLetterNatureByYear();

    @Query(value = "select year, sum(count)from letter_nature_info where area_id = ?1 group by year",nativeQuery = true)
    List<Object[]> getLetterNatureByYear(int areaId);
}
