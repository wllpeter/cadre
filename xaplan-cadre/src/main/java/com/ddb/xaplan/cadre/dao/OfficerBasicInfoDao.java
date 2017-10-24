package com.ddb.xaplan.cadre.dao;

import com.ddb.xaplan.cadre.entity.OfficerBasicInfoDO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by 王凯斌 on 2017/10/16.
 */
public interface OfficerBasicInfoDao extends BaseDao<OfficerBasicInfoDO>{
    /**
     * 获取各县党员数量
     */
    @Query(value="select count(1) from officer_basic_info where politics_status='2' and area_ids like concat(concat('%,',:areaId),',%')",nativeQuery=true)
    public int getPartyMemberCount(@Param("areaId")int areaId);

    /**
     * 获取各县干部总数量
     */
    @Query(value="select count(1) from officer_basic_info where area_ids like concat(concat('%,',:areaId),',%')",nativeQuery=true)
    public int getCadreCount(@Param("areaId")int areaId);

    /**
     * 获取各县籍贯本地的数量
     */
    @Query(value="select count(1) from officer_basic_info where area_ids like concat(concat('%,',:areaId),',%') and native_place in (\n" +
            "select name from sys_area where parent_id=:areaId or id =:areaId)",nativeQuery=true)
    public int getLocalNativePlace(@Param("areaId")int areaId);

    /**
     * 获取各县籍贯外地的数量
     */
    @Query(value="select count(1) from officer_basic_info where area_ids like concat(concat('%,',:areaId),',%') and native_place not in (\n" +
            "select name from sys_area where parent_id=:areaId or id =:areaId)",nativeQuery=true)
    public int getFieldNativePlace(@Param("areaId")int areaId);


    /**
     * 获取各县男生数量
     */
    @Query(value="select count(1) from officer_basic_info where area_ids like concat(concat('%,',:areaId),',%') and gender=0",nativeQuery=true)
    public int getManCount(@Param("areaId")int areaId);

    /**
     * 获取各县女生数量
     */
    @Query(value="select count(1) from officer_basic_info where area_ids like concat(concat('%,',:areaId),',%') and gender=1",nativeQuery=true)
    public int getWomanCount(@Param("areaId")int areaId);









}
