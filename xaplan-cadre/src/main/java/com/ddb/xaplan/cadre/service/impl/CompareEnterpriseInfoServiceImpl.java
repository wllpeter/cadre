package com.ddb.xaplan.cadre.service.impl;

import com.ddb.xaplan.cadre.dao.CompareEnterpriseInfoDao;
import com.ddb.xaplan.cadre.dao.CompareEstateInfoDao;
import com.ddb.xaplan.cadre.entity.CompareEnterpriseInfoDO;
import com.ddb.xaplan.cadre.service.CompareEnterpriseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 王凯斌 on 2017/10/23.
 */
@Service("compareEnterpriseInfoServiceImpl")
public class CompareEnterpriseInfoServiceImpl extends BaseServiceImpl<CompareEnterpriseInfoDO> implements CompareEnterpriseInfoService {

    @Autowired
    CompareEnterpriseInfoDao compareEnterpriseInfoDao;

    @Override
    public Integer countByOwnerId(String ownerId) {
        return compareEnterpriseInfoDao.countByOwnerId(ownerId);
    }
}
