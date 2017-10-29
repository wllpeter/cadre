package com.ddb.xaplan.cadre.service.impl;

import com.ddb.xaplan.cadre.dao.MedicalReimburseDao;
import com.ddb.xaplan.cadre.entity.AreaDO;
import com.ddb.xaplan.cadre.entity.MedicalReimburseDO;
import com.ddb.xaplan.cadre.service.MedicalReimburseService;
import com.ddb.xaplan.cadre.vo.DiseaseRankItem;
import com.ddb.xaplan.cadre.vo.ReimburseDetailVO;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public int[] monthCountStatistics(String year, Long areaId) {

        int[] result = new int[12];
        for(Object[] item:medicalReimburseDao.monthCountStatistics(year,areaId)){
            int index = Integer.parseInt(item[0].toString())-1;
            result[index]=Integer.parseInt(item[1].toString());
        }
        return result;
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

    @Override
    public Map average(String year, Long areaId) {

        Map<String,double[]> result = new HashMap<>();

        double[] array = new double[12];
        for(Object[] item:medicalReimburseDao.average(year,areaId)){
            int index = Integer.parseInt(item[0].toString())-1;
            array[index]=Double.valueOf(item[1].toString());
        }

        for(int i=1;i<5;i++){
            double[] item = new double[3];
            for(int j=0;j<3;j++){
                item[j] = array[j+((i-1)*3)];
            }
            result.put(String.valueOf(i),item);
        }

        return result;
    }

    @Override
    public List<DiseaseRankItem> hosRank(String year, Long areaId, int maximum, int minimum) {

        List<DiseaseRankItem> result = new ArrayList<>();
        int i = 1;
        for(Object[] item:medicalReimburseDao.hosRank(year,areaId,maximum,minimum)){
            result.add(
                    new DiseaseRankItem(
                            i,String.valueOf(item[0]),null,Integer.parseInt(item[1].toString()),
                            null,null));
            i++;
        }
        return result;
    }

    @Override
    public List<ReimburseDetailVO> getReimburseRank(Long areaId, Integer year, Integer season, int range) {
        List<Object[]> getData = null;
        if(null == season || (season != 1 && season != 2 && season != 3 && season != 4)){
            getData = medicalReimburseDao.getReimburseRank(areaId, year, range);
        }
        else {
            getData = medicalReimburseDao.getReimburseRank(areaId, year, season, range);
        }
        if(null != getData && getData.size() !=0){
            List<ReimburseDetailVO> result = new ArrayList<>();
            for(Object[] item : getData){
                result.add(
                        new ReimburseDetailVO(String.valueOf(item[0]),//名称
                                String.valueOf(item[1]),//身份证号
                                Integer.valueOf(item[2].toString()),//报销次数
                                new BigDecimal(item[3].toString()),//报销金额
                                Integer.valueOf(item[4].toString()),//住院天数
                                Integer.valueOf(item[5].toString()),//门诊次数
                                String.valueOf(item[6]),//病名
                                Integer.valueOf(item[7].toString())//住院次数
                        ));
                continue;
            }
            return result;
        }
        return null;
    }
}
