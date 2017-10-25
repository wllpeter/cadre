package com.ddb.xaplan.cadre.service.impl;

import com.ddb.xaplan.cadre.dao.OfficerLettersInfoDao;
import com.ddb.xaplan.cadre.entity.OfficerLettersInfoDO;
import com.ddb.xaplan.cadre.service.OfficerLettersInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 付鸣 on 2017/10/25.
 * 信访举报
 */

@Service("officerLettersInfoServiceImpl")
public class OfficerLettersInfoServiceImpl extends BaseServiceImpl<OfficerLettersInfoDO> implements OfficerLettersInfoService {

    @Autowired
    private OfficerLettersInfoDao officerLettersInfoDao;
    /**
     * 当年信访数量
     */
    @Override
    public int getLettersCount(int areaId) {

        return this.officerLettersInfoDao.getLettersCount(areaId,0);
    }

    /**
     * 本年与去年信访举报数量比例饿
     */
    @Override
    public String getLettersProportion(int areaId) {
        double year=this.officerLettersInfoDao.getLettersCount(areaId,0);
        double yest=this.officerLettersInfoDao.getLettersCount(areaId,1);
        if(yest==0){
            return "-";
        }else {
            int proportion = (int) ((year - yest) / yest * 100.0);
            return Integer.toString(proportion);
        }
    }

    /**
     * 往年信访统计
     * @param areaId
     * @return
     */
    @Override
    public List<OfficerLettersInfoDO> getLettersStatistics(int areaId) {
        return this.officerLettersInfoDao.getLettersStatistics(areaId);
    }
}
