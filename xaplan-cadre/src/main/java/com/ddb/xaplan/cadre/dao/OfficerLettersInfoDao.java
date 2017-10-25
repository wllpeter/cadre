package com.ddb.xaplan.cadre.dao;

import com.ddb.xaplan.cadre.entity.OfficerLettersInfoDO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by 付鸣 on 2017/10/25.
 */
public interface OfficerLettersInfoDao extends BaseDao<OfficerLettersInfoDO>{
    /**
     * 获取信访举报数量
     */
    @Query(value="select count from officer_letters_info where year=year(SYSDATE())-:y and areado_id=:areaId",nativeQuery=true)
    public int getLettersCount(@Param("areaId")int areaId,@Param("y")int y);



}
