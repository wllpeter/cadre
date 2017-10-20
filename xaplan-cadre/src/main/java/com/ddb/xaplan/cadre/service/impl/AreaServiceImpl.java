package com.ddb.xaplan.cadre.service.impl;

import com.ddb.xaplan.cadre.dao.AreaDao;
import com.ddb.xaplan.cadre.entity.AreaDO;
import com.ddb.xaplan.cadre.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 王凯斌 on 2017/10/17.
 */
@Service("areaServiceImpl")
public class AreaServiceImpl extends BaseServiceImpl<AreaDO> implements AreaService {

    @Autowired
    private AreaDao areaDao;

    @Override
    public List<AreaDO> findByGrade(Integer grade) {
        return areaDao.findByGrade(grade);
    }

    @Override
    public List<AreaDO> findByParent(AreaDO parent) {
        return areaDao.findByParent(parent);
    }
}
