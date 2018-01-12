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
        if (areaId == 0) {
            if (this.officerLettersInfoDao.getLettersSumCount(1) == null) {
                return 0;
            } else {
                return Integer.parseInt(this.officerLettersInfoDao.getLettersSumCount(1));
            }
        } else {
            if (this.officerLettersInfoDao.getLettersCount(areaId, 1) == null) {
                return 0;
            } else {
                return Integer.parseInt(this.officerLettersInfoDao.getLettersCount(areaId, 1));
            }
        }
    }

    /**
     * 本年与去年信访举报数量比例饿
     */
    @Override
    public String getLettersProportion(int areaId) {
        double year;
        String syear;
        if (areaId == 0) {
            syear = this.officerLettersInfoDao.getLettersSumCount(1);
        } else {
            syear = this.officerLettersInfoDao.getLettersCount(areaId, 1);
        }
        if (syear == null || syear == "") {
            year = 0;
        } else {
            year = Double.parseDouble(syear);
        }
        double yest;
        String syest;
        if (areaId == 0) {
            syest = this.officerLettersInfoDao.getLettersSumCount(2);
        } else {
            syest = this.officerLettersInfoDao.getLettersCount(areaId, 2);
        }
        if (syest == null || syest == "") {
            yest = 0;
        } else {
            yest = Double.parseDouble(syest);
        }
        if (yest == 0) {
            return "-";
        } else {
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
        if(areaId==0){
            return this.officerLettersInfoDao.getLettersSumStatistics();
        }else{
            return this.officerLettersInfoDao.getLettersStatistics(areaId);
        }

    }
}
