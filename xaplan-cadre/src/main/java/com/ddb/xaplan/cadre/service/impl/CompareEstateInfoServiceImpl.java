package com.ddb.xaplan.cadre.service.impl;

import com.ddb.xaplan.cadre.dao.CompareEstateInfoDao;
import com.ddb.xaplan.cadre.entity.CompareEstateInfoDO;
import com.ddb.xaplan.cadre.service.CompareEstateInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 王凯斌 on 2017/10/17.
 */
@Service("compareEstateInfoServiceImpl")
public class CompareEstateInfoServiceImpl extends BaseServiceImpl<CompareEstateInfoDO> implements CompareEstateInfoService {

    @Autowired
    private CompareEstateInfoDao compareEstateInfoDao;

    @Override
    public Integer countByOwnerId(String ownerId) {
        return compareEstateInfoDao.countByOwnerId(ownerId);
    }
}
