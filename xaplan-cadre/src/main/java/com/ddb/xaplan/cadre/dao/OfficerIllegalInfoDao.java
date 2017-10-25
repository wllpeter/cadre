package com.ddb.xaplan.cadre.dao;


import com.ddb.xaplan.cadre.entity.OfficerIllegalInfoDO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
}
