package com.ddb.xaplan.cadre.service;

import com.ddb.xaplan.cadre.entity.CompareEnterpriseInfoDO;

/**
 * Created by 王凯斌 on 2017/10/23.
 */
public interface CompareEnterpriseInfoService extends BaseService<CompareEnterpriseInfoDO>{

    Integer countByOwnerId(String ownerId);
}
