package com.ddb.xaplan.cadre.dao;


import com.ddb.xaplan.cadre.entity.OfficerCrimeInfoDO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by 付鸣 on 2017/10/17.
 * 立案查处
 */
public interface OfficerCrimeInfoDao extends BaseDao<OfficerCrimeInfoDO>{


    /**
     * 本年各县立案查处记录数量
     */
    @Query(value="select count(1) from officer_crime_info where  \n" +
            "year(occur_date)=year(SYSDATE())-:y and result like concat('%',(select LEFT(name,2) from sys_area where id=:areaId),'%')",nativeQuery=true)
    public int getCrimeCount(@Param("areaId")int areaId, @Param("y")int y);

    /**
     * 本年三县立案查处记录
     */
    @Query(value="select count(1) from officer_crime_info where  \n" +
            "year(occur_date)=year(SYSDATE())-:y",nativeQuery=true)
    public int getSumCrimeCount(@Param("y")int y);

    /**
     * 各县每年立案数量
     */
    @Query(value="select year(occur_date) name,count(1) count from officer_crime_info where occur_date is not null\n" +
            " and result like concat('%',(select LEFT(name,2) from sys_area where id=:areaId),'%')\n" +
            " group by year(occur_date)",nativeQuery=true)
    public List<Object[]> getGroupYearCrimeCount(@Param("areaId")int areaId);

    /**
     * 三县每年立案数量
     */
    @Query(value="select year(occur_date) name,count(1) count from officer_crime_info where occur_date is not null\n" +
            " group by year(occur_date)",nativeQuery=true)
    public List<Object[]> getGroupYearSumCrimeCount();

    /**
     * 各县犯罪分类
     */
    @Query(value="select type name,count(1) count from officer_crime_info where  \n" +
            " result like concat('%',(select LEFT(name,2) from sys_area where id=:areaId),'%')\n" +
            " group by type order by type desc",nativeQuery=true)
    public List<Object[]> getGroupCrimeCount(@Param("areaId")int areaId);

    /**
     * 三县犯罪分类
     */
    @Query(value="select type name,count(1) count from officer_crime_info\n" +
            " group by type order by type desc",nativeQuery=true)
    public List<Object[]> getGroupSumCrimeCount();





}
