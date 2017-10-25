package com.ddb.xaplan.cadre.service.impl;

import com.ddb.xaplan.cadre.dao.OfficerCrimeInfoDao;
import com.ddb.xaplan.cadre.entity.OfficerCrimeInfoDO;
import com.ddb.xaplan.cadre.service.OfficerCrimeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 付鸣 on 2017/10/17.
 * 立案查处
 */

@Service("officerCrimeInfoServiceImpl")
public class OfficerCrimeInfoServiceImpl extends BaseServiceImpl<OfficerCrimeInfoDO> implements OfficerCrimeInfoService {
    @Autowired
    private OfficerCrimeInfoDao officerCrimeInfoDao;
    /**
     * 本年立案查处记录数量
     */
    @Override
    public int getCrimeCount(int areaId) {
        return this.officerCrimeInfoDao.getCrimeCount(areaId,0);
    }

    /**
     * 计算本年与去年相比立案查处数量增长比例
     */
    @Override
    public String getCrimeProportion(int areaId) {
        double year=this.officerCrimeInfoDao.getCrimeCount(areaId,0);
        double yest=this.officerCrimeInfoDao.getCrimeCount(areaId,1);
        if(yest==0){
            return "-";
        }else {
            int proportion = (int)Math.ceil(((year - yest) / yest * 100.0));
            return Integer.toString(proportion);
        }
    }

}
