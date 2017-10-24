package com.ddb.xaplan.cadre.service.impl;

import com.ddb.xaplan.cadre.dao.MedicalReimburseDao;
import com.ddb.xaplan.cadre.entity.AreaDO;
import com.ddb.xaplan.cadre.entity.MedicalReimburseDO;
import com.ddb.xaplan.cadre.service.MedicalReimburseService;
import com.ddb.xaplan.cadre.vo.DiseaseRankItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 王凯斌 on 2017/10/23.
 */
@Service("medicalReimburseServiceImpl")
public class MedicalReimburseServiceImpl extends BaseServiceImpl<MedicalReimburseDO> implements MedicalReimburseService {

    @Autowired
    private MedicalReimburseDao medicalReimburseDao;

    @Override
    public List<MedicalReimburseDO> findByAreaAndAddressLike(AreaDO area, String address) {
        return medicalReimburseDao.findByAreaAndAddressLike(area,address);
    }

    @Override
    public int[] monthStatistics(String year, int hospitalized, Long areaId) {

        int[] result = new int[12];
        for(Object[] item:medicalReimburseDao.monthStatistics(year,hospitalized,areaId)){
            int index = Integer.parseInt(item[0].toString())-1;
            result[index]=Integer.parseInt(item[1].toString());
        }
        return result;
    }

    @Override
    public double[] monthAmountStatistics(String year, Long areaId) {
        double[] result = new double[12];
        for(Object[] item:medicalReimburseDao.monthAmountStatistics(year,areaId)){
            int index = Integer.parseInt(item[0].toString())-1;
            result[index]=Double.valueOf(item[1].toString());
        }
        return result;
    }

    @Override
    public List<DiseaseRankItem> diseaseRank(String year, Long areaId) {

        List<DiseaseRankItem> result = new ArrayList<>();
        int i = 1;
        for(Object[] item:medicalReimburseDao.diseaseRack(year,areaId)){
            result.add(
                    new DiseaseRankItem(
                            i,String.valueOf(item[0]),Integer.parseInt(item[2].toString()),Integer.parseInt(item[1].toString()),
                            Double.valueOf(item[4].toString()),Double.valueOf(item[3].toString())));
            i++;
        }
        return result;
    }

    @Override
    public int[] hosCount(String year, Long areaId, String month) {

        int[] result = new int[31];
        for(Object[] item:medicalReimburseDao.hosCount(year,areaId,month)){
            int index = Integer.parseInt(item[0].toString())-1;
            result[index]=Integer.parseInt(item[1].toString());
        }
        return result;
    }
}
