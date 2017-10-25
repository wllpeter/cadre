package com.ddb.xaplan.cadre.dao;

import com.ddb.xaplan.cadre.entity.OfficerBasicInfoDO;
import com.ddb.xaplan.cadre.entity.OfficerCurriculumVitaeDO;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


/**
 * Created by 王凯斌 on 2017/10/17.
 */
public interface OfficerCurriculumVitaeDao extends BaseDao<OfficerCurriculumVitaeDO>{

    @Query("select d from OfficerCurriculumVitaeDO  d where d.officerBasicInfo=?1 order by d.startDate desc")
    List<OfficerCurriculumVitaeDO> search(OfficerBasicInfoDO officerBasicInfoDO);

}
