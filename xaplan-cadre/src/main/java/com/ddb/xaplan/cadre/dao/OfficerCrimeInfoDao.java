package com.ddb.xaplan.cadre.dao;


import com.ddb.xaplan.cadre.entity.OfficerCrimeInfoDO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by 付鸣 on 2017/10/17.
 * 立案查处
 */
public interface OfficerCrimeInfoDao extends BaseDao<OfficerCrimeInfoDO>{


    /**
     * 本年立案查处记录数量
     */
    @Query(value="select count(1) from officer_crime_info where  \n" +
            "year(occur_date)=year(SYSDATE())-:y and result like concat('%',(select LEFT(name,2) from sys_area where id=:areaId),'%')",nativeQuery=true)
    public int getCrimeCount(@Param("areaId")int areaId, @Param("y")int y);


}
