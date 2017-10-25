package com.ddb.xaplan.cadre.service;

import com.ddb.xaplan.cadre.entity.OfficerBasicInfoDO;
import com.ddb.xaplan.cadre.entity.OfficerCurriculumVitaeDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by 王凯斌 on 2017/10/17.
 */
public interface OfficerCurriculumVitaeService extends BaseService<OfficerCurriculumVitaeDO>{
    List<OfficerCurriculumVitaeDO> search(OfficerBasicInfoDO officerBasicInfoDO);
}
