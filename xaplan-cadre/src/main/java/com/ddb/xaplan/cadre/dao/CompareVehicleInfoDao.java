package com.ddb.xaplan.cadre.dao;

import com.ddb.xaplan.cadre.entity.CompareVehicleInfoDO;

/**
 * Created by 王凯斌 on 2017/10/17.
 */
public interface CompareVehicleInfoDao extends BaseDao<CompareVehicleInfoDO>{

    Integer countByIdCard(String idCard);
}
