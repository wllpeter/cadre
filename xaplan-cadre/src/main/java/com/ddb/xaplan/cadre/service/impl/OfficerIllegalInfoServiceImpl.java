package com.ddb.xaplan.cadre.service.impl;

import com.ddb.xaplan.cadre.dao.OfficerIllegalInfoDao;
import com.ddb.xaplan.cadre.entity.OfficerIllegalInfoDO;
import com.ddb.xaplan.cadre.service.OfficerIllegalInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 付鸣 on 2017/10/17.
 * 违纪涉法
 */

@Service("officerIllegalInfoServiceImpl")
public class OfficerIllegalInfoServiceImpl extends BaseServiceImpl<OfficerIllegalInfoDO> implements OfficerIllegalInfoService{

    @Autowired
    private OfficerIllegalInfoDao officerIllegalInfoDao;

    /**
     * 本年各县违纪记录数量
     */
    @Override
    public int getIllegalCount(int areaId) {
        return this.officerIllegalInfoDao.getIllegalCount(areaId,0);
    }

    /**
     * 计算本年与去年相比违纪数量增长比例
     */
    @Override
    public String getIllegalProportion(int areaId) {

        double year=this.officerIllegalInfoDao.getIllegalCount(areaId,0);
        double yest=this.officerIllegalInfoDao.getIllegalCount(areaId,1);
        if(yest==0){
            return "-";
        }else {
            int proportion = (int) Math.ceil(((year - yest) / yest * 100.0));
            return Integer.toString(proportion);
        }
    }

}
