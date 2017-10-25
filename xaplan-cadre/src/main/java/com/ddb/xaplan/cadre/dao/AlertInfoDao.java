package com.ddb.xaplan.cadre.dao;

import com.ddb.xaplan.cadre.entity.AlertInfoDO;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by 王凯斌 on 2017/10/17.
 */
public interface AlertInfoDao extends BaseDao<AlertInfoDO>{
    @Query(value = "select content as content,count(*) as count from alert_info " +
            "group by content",nativeQuery = true)
    List<Object[]> getAlertCountByContent();

    @Query(value = "select area_ids from alert_info where area_ids is not null and area_ids !=''",nativeQuery = true)
    List<String> getAlertCountByArea();
}
