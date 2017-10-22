package com.ddb.xaplan.cadre.dao;

import com.ddb.xaplan.cadre.entity.CompareEstateInfoDO;

/**
 * Created by 王凯斌 on 2017/10/17.
 */
public interface CompareEstateInfoDao extends BaseDao<CompareEstateInfoDO>{

    Integer countByOwnerId(String ownerId);
}
