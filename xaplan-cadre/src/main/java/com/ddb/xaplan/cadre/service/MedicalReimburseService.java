package com.ddb.xaplan.cadre.service;

import com.ddb.xaplan.cadre.entity.AreaDO;
import com.ddb.xaplan.cadre.entity.MedicalReimburseDO;
import com.ddb.xaplan.cadre.vo.DiseaseRankItem;

import java.util.List;
import java.util.Map;

/**
 * Created by 王凯斌 on 2017/10/23.
 */
public interface MedicalReimburseService extends BaseService<MedicalReimburseDO>{

    List<MedicalReimburseDO> findByAreaAndAddressLike(AreaDO area,String address);

    int[] monthStatistics(String year, int hospitalized,Long areaId);

    double[] monthAmountStatistics(String year, Long areaId);

    List<DiseaseRankItem> diseaseRank(String year, Long areaId);

    int[] hosCount(String year, Long areaId,String month);

    Map average(String year, Long areaId);

    List<DiseaseRankItem> hosRank(String year, Long areaId, int maximum, int minimum);
}
