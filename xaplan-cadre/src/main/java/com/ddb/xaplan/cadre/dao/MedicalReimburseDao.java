package com.ddb.xaplan.cadre.dao;

import com.ddb.xaplan.cadre.entity.AreaDO;
import com.ddb.xaplan.cadre.entity.MedicalReimburseDO;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by 王凯斌 on 2017/10/23.
 */
public interface MedicalReimburseDao extends BaseDao<MedicalReimburseDO>{

    List<MedicalReimburseDO> findByAreaAndAddressLike(AreaDO area,String address);

    @Query(value = "SELECT MONTH(occur_date) monthValue,count(*) countValue from medical_reimburse \n" +
            "where occur_date is not null and YEAR(occur_date) = ?1 and hospitalized = ?2 and area_ids like CONCAT('%,',?3,',%')" +
            "GROUP BY MONTH(occur_date)\n",nativeQuery = true)
    List<Object[]> monthStatistics(String year,int hospitalized, Long areaId);

    @Query(value = "SELECT MONTH(occur_date) monthValue,sum(reimbursement_amount) sumValue from medical_reimburse \n" +
            "where occur_date is not null and YEAR(occur_date) = ?1 and area_ids like CONCAT('%,',?2,',%')" +
            "GROUP BY MONTH(occur_date)\n",nativeQuery = true)
    List<Object[]> monthAmountStatistics(String year, Long areaId);

    @Query(value = "select disease_name,SUM(IF(hospitalized=1,1,0)),SUM(IF(hospitalized=0,1,0)),SUM(IF(hospitalized=1,reimbursement_amount,0)),SUM(IF(hospitalized=0,reimbursement_amount,0))\n" +
            "from medical_reimburse \n" +
            "WHERE disease_name is not null and disease_name !=\"\" and area_ids like concat(\"%,\",?2,\",%\") and YEAR(occur_date) = ?1\n" +
            "GROUP BY disease_name ORDER BY count(*) desc\n" +
            "LIMIT 0,20",nativeQuery = true)
    List<Object[]> diseaseRack(String year, Long areaId);

    @Query(value = "select DAY(start_date),count(*)\n" +
            "from medical_reimburse \n" +
            "WHERE YEAR(start_date) = ?1 and area_ids like concat(\"%,\",?2,\",%\") and MONTH(start_date)=?3 and hospitalized=1\n" +
            "GROUP BY DAY(start_date)",nativeQuery = true)
    List<Object[]> hosCount(String year, Long areaId,String month);

    @Query(value = "select MONTH(start_date),count(*)/30 from medical_reimburse\n" +
            "where area_ids like CONCAT(\"%,\",?2,\",%\") and YEAR(start_date) = ?1 and hospitalized =1\n" +
            "GROUP BY MONTH(start_date)",nativeQuery = true)
    List<Object[]> average(String year, Long areaId);

    @Query(value = "SELECT disease_name,count(*)\n" +
            "from medical_reimburse \n" +
            "where YEAR(start_date) = ?1 \n" +
            "\tand hospitalized_duration >=?4 \n" +
            "\tand hospitalized_duration <?3 \n" +
            "\tand disease_name is not null \n" +
            "\tand disease_name !=\"\"\n" +
            "\tand area_ids like CONCAT(\"%,\",?2,\",%\")\n" +
            "GROUP BY disease_name\n" +
            "ORDER BY count(*) DESC\n" +
            "limit 0,3",nativeQuery = true)
    List<Object[]> hosRank(String year, Long areaId, int maximum, int minimum);

}