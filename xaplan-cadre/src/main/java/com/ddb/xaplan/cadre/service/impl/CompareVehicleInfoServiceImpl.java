package com.ddb.xaplan.cadre.service.impl;

import com.ddb.xaplan.cadre.dao.CompareVehicleInfoDao;
import com.ddb.xaplan.cadre.entity.CompareVehicleInfoDO;
import com.ddb.xaplan.cadre.service.CompareVehicleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 王凯斌 on 2017/10/17.
 */
@Service("compareVehicleInfoServiceImpl")
public class CompareVehicleInfoServiceImpl extends BaseServiceImpl<CompareVehicleInfoDO> implements CompareVehicleInfoService {

    @Autowired
    private CompareVehicleInfoDao compareVehicleInfoDao;

    @Override
    public Integer countByIdCard(String idCard) {
        return compareVehicleInfoDao.countByIdCard(idCard);
    }
}
