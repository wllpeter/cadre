package com.ddb.xaplan.cadre.dao;


import com.ddb.xaplan.cadre.entity.OfficerIllegalInfoDO;
import com.ddb.xaplan.cadre.entity.StatisticsBean;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by 付鸣 on 2017/10/17.
 * 违纪涉法
 */
public interface OfficerIllegalInfoDao extends BaseDao<OfficerIllegalInfoDO> {


    /**
     * 本年各县违纪记录数量
     */
    @Query(value="select count(1) from officer_illegal_info where year(occur_date)=year(SYSDATE())-:y and officer_basic_info_id in (\n" +
            " select id from officer_basic_info where\n" +
            " area_ids like concat('%,',:areaId,',%'))",nativeQuery=true)
    public int getIllegalCount(@Param("areaId")int areaId, @Param("y")int y);


    /**
     * 本年三县违纪记录数量
     */
    @Query(value="select count(1) from officer_illegal_info,officer_basic_info\n" +
            "where officer_illegal_info.officer_basic_info_id=officer_basic_info.id \n" +
            "and year(occur_date)=year(SYSDATE())-:y",nativeQuery=true)
    public int getSumIllegalCount( @Param("y")int y);

    /**
     * 各县每年违纪记录数量
     */
    @Query(value="select year(occur_date) name,count(1) count from officer_basic_info,officer_illegal_info\n" +
            "where officer_illegal_info.officer_basic_info_id=officer_basic_info.id \n" +
            "and address like concat('%',(select LEFT(name,2) from sys_area where id=:areaId),'%')\n" +
            "group by year(occur_date)\n",nativeQuery=true)
    public List<Object[]> getGroupYearIllegalCount(@Param("areaId")int areaId);

    /**
     *三县每年违纪记录数量
     */
    @Query(value="select year(occur_date) name,count(1) count from officer_basic_info,officer_illegal_info\n" +
            " where officer_illegal_info.officer_basic_info_id=officer_basic_info.id \n" +
            " group by year(occur_date)",nativeQuery=true)
    public List<Object[]> getGroupYearSumIllegalCount();

    /**
     * 各县党纪处分分类
     */
    @Query(value="select result name,count(1) count from officer_illegal_info,officer_basic_info\n" +
            " where officer_illegal_info.officer_basic_info_id=officer_basic_info.id\n" +
            " and address like concat('%',(select LEFT(name,2) from sys_area where id=:areaId),'%')\n" +
            " group by result",nativeQuery=true)
    public List<Object[]> getGroupPunishmentIllegalCount( @Param("areaId")int areaId);

    /**
     * 三县党纪处分分类
     */
    @Query(value="select result name,count(1) count from officer_illegal_info,officer_basic_info\n" +
            " where officer_illegal_info.officer_basic_info_id=officer_basic_info.id\n" +
            " group by result",nativeQuery=true)
    public List<Object[]> getGroupPunishmentSumIllegalCount();

    /**
     * 各县性质分类
     */
    @Query(value="select brief name,count(1) count from officer_illegal_info,officer_basic_info\n" +
            " where officer_illegal_info.officer_basic_info_id=officer_basic_info.id\n" +
            " and address like concat('%',(select LEFT(name,2) from sys_area where id=:areaId),'%')\n" +
            " group by brief",nativeQuery=true)
    public List<Object[]> getGroupBriefIllegalCount(@Param("areaId")int areaId);

    /**
     * 三县性质分类
     */
    @Query(value="select brief name,count(1) count from officer_illegal_info,officer_basic_info\n" +
            " where officer_illegal_info.officer_basic_info_id=officer_basic_info.id\n" +
            " group by brief",nativeQuery=true)
    public List<Object[]> getGroupBriefSumIllegalCount();















}
