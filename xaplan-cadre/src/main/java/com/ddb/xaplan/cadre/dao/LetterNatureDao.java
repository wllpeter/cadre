package com.ddb.xaplan.cadre.dao;

import com.ddb.xaplan.cadre.entity.LetterNatureDO;
import com.ddb.xaplan.cadre.entity.ReportedDO;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LetterNatureDao extends BaseDao<LetterNatureDO>{
    @Query(value = "select name, sum(count)from letter_nature_info group by name ",nativeQuery = true)
    List<Object[]> getLetterNatureList();

    @Query(value = "select name, sum(count)from letter_nature_info group by name where area_id = ?1",nativeQuery = true)
    List<Object[]> getLetterNatureList(int areaId);
}
