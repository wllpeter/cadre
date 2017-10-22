package com.ddb.xaplan.cadre.service;

import com.ddb.xaplan.cadre.entity.CompareVehicleInfoDO;

/**
 * Created by 王凯斌 on 2017/10/17.
 */
public interface CompareVehicleInfoService extends BaseService<CompareVehicleInfoDO>{

    Integer countByIdCard(String idCard);
}
