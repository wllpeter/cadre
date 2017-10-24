package com.ddb.xaplan.cadre.dao;

import com.ddb.xaplan.cadre.entity.CompareEnterpriseInfoDO;

/**
 * Created by 王凯斌 on 2017/10/23.
 */
public interface CompareEnterpriseInfoDao extends BaseDao<CompareEnterpriseInfoDO>{

    Integer countByOwnerId(String ownerId);
}
