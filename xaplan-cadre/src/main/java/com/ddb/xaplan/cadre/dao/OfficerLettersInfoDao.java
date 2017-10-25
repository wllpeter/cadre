package com.ddb.xaplan.cadre.dao;

import com.ddb.xaplan.cadre.entity.OfficerLettersInfoDO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by 付鸣 on 2017/10/25.
 */
public interface OfficerLettersInfoDao extends BaseDao<OfficerLettersInfoDO>{
    /**
     * 获取信访举报数量
     */
    @Query(value="select count from officer_letters_info where year=year(SYSDATE())-:y and area_id=:areaId",nativeQuery=true)
    public int getLettersCount(@Param("areaId")int areaId,@Param("y")int y);

    /**
     * 往年信访举报统计
     */
    @Query(value="select * from officer_letters_info where year >= year(SYSDATE())-4 and area_id=:areaId",nativeQuery=true)
    public List<OfficerLettersInfoDO> getLettersStatistics(@Param("areaId")int areaId);



}
