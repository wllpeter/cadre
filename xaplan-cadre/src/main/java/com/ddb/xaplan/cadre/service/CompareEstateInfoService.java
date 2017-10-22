package com.ddb.xaplan.cadre.service;

import com.ddb.xaplan.cadre.entity.CompareEstateInfoDO;

/**
 * Created by 王凯斌 on 2017/10/17.
 */
public interface CompareEstateInfoService extends BaseService<CompareEstateInfoDO>{

    Integer countByOwnerId(String ownerId);
}
