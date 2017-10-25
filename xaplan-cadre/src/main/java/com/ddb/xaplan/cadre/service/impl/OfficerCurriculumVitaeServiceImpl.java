package com.ddb.xaplan.cadre.service.impl;

import com.ddb.xaplan.cadre.dao.OfficerCurriculumVitaeDao;
import com.ddb.xaplan.cadre.entity.OfficerBasicInfoDO;
import com.ddb.xaplan.cadre.entity.OfficerCurriculumVitaeDO;
import com.ddb.xaplan.cadre.service.OfficerCurriculumVitaeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 王凯斌 on 2017/10/17.
 */
@Service("officerCurriculumVitaeServiceImpl")
public class OfficerCurriculumVitaeServiceImpl extends BaseServiceImpl<OfficerCurriculumVitaeDO> implements OfficerCurriculumVitaeService {
    @Autowired
    private OfficerCurriculumVitaeDao officerCurriculumVitaeDao;

    @Override
    public List<OfficerCurriculumVitaeDO> search(OfficerBasicInfoDO officerBasicInfoDO) {

        return officerCurriculumVitaeDao.search(officerBasicInfoDO);
    }
}
