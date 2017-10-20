package com.ddb.xaplan.cadre.dao;

import com.ddb.xaplan.cadre.entity.AreaDO;

import java.util.List;

/**
 * Created by 王凯斌 on 2017/10/17.
 */
public interface AreaDao extends BaseDao<AreaDO>{

    List<AreaDO> findByGrade(Integer grade);

    List<AreaDO> findByParent(AreaDO parent);
}
