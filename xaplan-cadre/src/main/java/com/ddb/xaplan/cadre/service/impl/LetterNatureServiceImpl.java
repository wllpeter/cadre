package com.ddb.xaplan.cadre.service.impl;

import com.ddb.xaplan.cadre.dao.LetterNatureDao;
import com.ddb.xaplan.cadre.entity.LetterNatureDO;
import com.ddb.xaplan.cadre.entity.OfficerLettersInfoDO;
import com.ddb.xaplan.cadre.entity.StatisticsBean;
import com.ddb.xaplan.cadre.service.LetterNatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("letterNatureServiceImpl")
public class LetterNatureServiceImpl extends BaseServiceImpl<LetterNatureDO> implements LetterNatureService{
    @Autowired
    private LetterNatureDao letterNatureDao;
    @Override
    public List<StatisticsBean> getNatureList(int areaId) {
        OfficerLettersInfoDO officerLettersInfoDO;
        if (areaId == 0) {
            officerLettersInfoDO = this.letterNatureDao.getLetterNatureList();
        } else {
            officerLettersInfoDO = this.letterNatureDao.getLetterNatureList(areaId);
        }
        if(null != officerLettersInfoDO ){
            List<StatisticsBean> result = new ArrayList<>();
            result.add(new StatisticsBean("网络与其它",officerLettersInfoDO.getNetworkOtherCount()));
            result.add(new StatisticsBean("电话",officerLettersInfoDO.getPhoneCount()));
            result.add(new StatisticsBean("上级转来",officerLettersInfoDO.getHightLevelTransferCount()));
            result.add(new StatisticsBean("来信",officerLettersInfoDO.getInLetterCount()));
            result.add(new StatisticsBean("来访",officerLettersInfoDO.getVisitCount()));
            return result;
        }
        return null;
    }

    @Override
    public List<StatisticsBean> getNaturesByYear(int areaId) {
        List<Object[]> ret;
        if (areaId == 0) {
            ret = this.letterNatureDao.getLetterNatureByYear();
        } else {
            ret = this.letterNatureDao.getLetterNatureByYear(areaId);
        }
        if(null != ret && ret.size() > 0 ){
            List<StatisticsBean> result = new ArrayList<>();
            for(Object[] item : ret){
                result.add(new StatisticsBean(item[0].toString(),Integer.valueOf(item[1].toString())));
                continue;
            }
            return result;
        }
        return null;
    }
}
