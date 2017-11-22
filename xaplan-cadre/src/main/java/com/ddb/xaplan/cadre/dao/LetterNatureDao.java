package com.ddb.xaplan.cadre.dao;

import com.ddb.xaplan.cadre.entity.LetterNatureDO;
import com.ddb.xaplan.cadre.entity.OfficerLettersInfoDO;
import com.ddb.xaplan.cadre.entity.ReportedDO;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LetterNatureDao extends BaseDao<LetterNatureDO>{
    @Query(value = "select * from officer_letters_info where year=YEAR(SYSDATE()) ",nativeQuery = true)
    OfficerLettersInfoDO getLetterNatureList();

    @Query(value = "select * from officer_letters_info where year=YEAR(SYSDATE()) and area_id = ?1",nativeQuery = true)
    OfficerLettersInfoDO getLetterNatureList(int areaId);

    @Query(value = "select year,count from officer_letters_info",nativeQuery = true)
    List<Object[]> getLetterNatureByYear();

    @Query(value = "select year,count from officer_letters_info where area_id = ?1",nativeQuery = true)
    List<Object[]> getLetterNatureByYear(int areaId);
}
