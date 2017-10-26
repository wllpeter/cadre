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
     * 三县党员数量
     */
    @Query(value="select count(1) from officer_basic_info where politics_status='2'",nativeQuery=true)
    public int getPartyMemberSumCount();


    /**
     * 获取各县干部总数量
     */
    @Query(value="select count(1) from officer_basic_info where area_ids like concat(concat('%,',:areaId),',%')",nativeQuery=true)
    public int getCadreCount(@Param("areaId")int areaId);

    /**
     * 获取三县干部总数量
     */
    @Query(value="select count(1) from officer_basic_info",nativeQuery=true)
    public int getCadreSumCount();

    /**
     * 获取各县籍贯本地的数量
     */
    @Query(value="select count(1) from officer_basic_info where area_ids like concat(concat('%,',:areaId),',%') and native_place in (\n" +
            "select name from sys_area where parent_id=:areaId or id =:areaId)",nativeQuery=true)
    public int getLocalNativePlace(@Param("areaId")int areaId);

    /**
     * 获取三县籍贯本地的数量
     */
    @Query(value="select count(1) from officer_basic_info where native_place in(\n" +
            "select name from sys_area where grade=3 or grade =4)",nativeQuery=true)
    public int getSumLocalNativePlace();

    /**
     * 获取各县籍贯外地的数量
     */
    @Query(value="select count(1) from officer_basic_info where area_ids like concat(concat('%,',:areaId),',%') and native_place not in (\n" +
            "select name from sys_area where parent_id=:areaId or id =:areaId)",nativeQuery=true)
    public int getFieldNativePlace(@Param("areaId")int areaId);

    /**
     * 获取三县籍贯外地的数量
     */
    @Query(value="select count(1) from officer_basic_info where native_place not in(\n" +
            "select name from sys_area where grade=3 or grade =4)",nativeQuery=true)
    public int getSumFieldNativePlace();


    /**
     * 获取各县男生数量
     */
    @Query(value="select count(1) from officer_basic_info where area_ids like concat(concat('%,',:areaId),',%') and gender=:gender",nativeQuery=true)
    public int getGenderCount(@Param("areaId")int areaId,@Param("gender")int gender);

    /**
     * 获取三县男生数量
     */
    @Query(value="select count(1) from officer_basic_info where gender=:gender",nativeQuery=true)
    public int getSumGenderCount(@Param("gender")int gender);

    /**
     *获取各县硕士及以上学历人数
     */
    @Query(value="select count(1) from officer_basic_info where\n" +
            " area_ids like concat('%,',:areaId,',%') and education_level>=6",nativeQuery=true)
    public int getMasterCount(@Param("areaId")int areaId);

    /**
     *获取三县硕士及以上学历人数
     */
    @Query(value="select count(1) from officer_basic_info where\n" +
            " education_level>=6",nativeQuery=true)
    public int getSumMasterCount();

    /**
     *获取各县大学本科学历人数
     *
     */
    @Query(value="select count(1) from officer_basic_info where\n" +
            " area_ids like concat('%,',:areaId,',%') and education_level=5",nativeQuery=true)
    public int getUndergraduateCount(@Param("areaId")int areaId);

    /**
     *获取三县大学本科学历人数
     *
     */
    @Query(value="select count(1) from officer_basic_info where\n" +
            " education_level=5",nativeQuery=true)
    public int getSumUndergraduateCount();
    /**
     *获取各县大学专科学历人数
     *
     */
    @Query(value="select count(1) from officer_basic_info where\n" +
            " area_ids like concat('%,',:areaId,',%') and education_level=4",nativeQuery=true)
    public int getProfessionColleageCount(@Param("areaId")int areaId);
    /**
     *获取三县大学专科学历人数
     *
     */
    @Query(value="select count(1) from officer_basic_info where\n" +
            " education_level=4",nativeQuery=true)
    public int getSumProfessionColleageCount();

    /**
     *获取各县中专及以下学历人数
     *
     */
    @Query(value="select count(1) from officer_basic_info where\n" +
            " area_ids like concat('%,',:areaId,',%') and education_level<=3",nativeQuery=true)
    public int getSecondaryBelowCount(@Param("areaId")int areaId);

    /**
     *获取三县中专及以下学历人数
     *
     */
    @Query(value="select count(1) from officer_basic_info where\n" +
            " education_level<=3",nativeQuery=true)
    public int getSumSecondaryBelowCount();

    /**
     * 获取各县年龄30岁以下人数
     */
    @Query(value="select count(1) from officer_basic_info where\n" +
            " area_ids like concat('%,',:areaId,',%') and year(SYSDATE())-year(birth_date)<=30",nativeQuery=true)
    public int getThirtyBelowCount(@Param("areaId")int areaId);

    /**
     * 获取三县年龄30岁以下人数
     */
    @Query(value="select count(1) from officer_basic_info where\n" +
            " year(SYSDATE())-year(birth_date)<=30",nativeQuery=true)
    public int getSumThirtyBelowCount();

    /**
     * 获取各县年龄31-40岁人数
     */
    @Query(value="select count(1) from officer_basic_info where\n" +
            " area_ids like concat('%,',:areaId,',%') and year(SYSDATE())-year(birth_date)>30" +
            " and year(SYSDATE())-year(birth_date)<=40",nativeQuery=true)
    public int getThirtyAndFourtyCount(@Param("areaId")int areaId);

    /**
     * 获取各县年龄31-40岁人数
     */
    @Query(value="select count(1) from officer_basic_info where\n" +
            " year(SYSDATE())-year(birth_date)>30" +
            " and year(SYSDATE())-year(birth_date)<=40",nativeQuery=true)
    public int getSumThirtyAndFourtyCount();

    /**
     * 获取各县年龄41-50岁人数
     */
    @Query(value="select count(1) from officer_basic_info where\n" +
            " area_ids like concat('%,',:areaId,',%') and year(SYSDATE())-year(birth_date)>40" +
            " and year(SYSDATE())-year(birth_date)<=50",nativeQuery=true)
    public int getFourtyAndFivtyCount(@Param("areaId")int areaId);
    /**
     * 获取三县年龄41-50岁人数
     */
    @Query(value="select count(1) from officer_basic_info where\n" +
            " year(SYSDATE())-year(birth_date)>40" +
            " and year(SYSDATE())-year(birth_date)<=50",nativeQuery=true)
    public int getSumFourtyAndFivtyCount();


    /**
     * 获取各县年龄51岁及以上人数
     */
    @Query(value="select count(1) from officer_basic_info where\n" +
            " area_ids like concat('%,',:areaId,',%') and year(SYSDATE())-year(birth_date)>50",nativeQuery=true)
    public int getFivtyAboveCount(@Param("areaId")int areaId);

    /**
     * 获取三县年龄51岁及以上人数
     */
    @Query(value="select count(1) from officer_basic_info where\n" +
            " year(SYSDATE())-year(birth_date)>50",nativeQuery=true)
    public int getSumFivtyAboveCount();

    /**
     * 获取各县县处级正职人数
     */
    @Query(value="select count(1) from officer_basic_info where\n" +
            " area_ids like concat('%,',:areaId,',%') and title_level=:title_level",nativeQuery=true)
    public int getCountyCount(@Param("areaId")int areaId,@Param("title_level")int title_level);

    /**
     * 获取各县县处级正职人数
     */
    @Query(value="select count(1) from officer_basic_info where\n" +
            " title_level=:title_level",nativeQuery=true)
    public int getSumCountyCount(@Param("title_level")int title_level);



















}
