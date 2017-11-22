package com.ddb.xaplan.cadre.dao;

import com.ddb.xaplan.cadre.entity.ReportedDO;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReportedDao extends BaseDao<ReportedDO>{
    /**
     * 被举报人性质统计全县
     * @return
     */
    @Query(value = "select name, sum(count) from reported_info where year = year(SYSDATE())group by name",nativeQuery = true)
    List<Object[]> getBeReportedList();

    /**
     * 按区进行筛选的被举报人统计
     * @param areaId
     * @return
     */
    @Query(value = "select name, sum(count) from reported_info where area_id = ?1 and year(SYSDATE()) group by name ",nativeQuery = true)
    List<Object[]> getBeReportedList(int areaId);

    /**
     * 按年份和总数查询
     * @return
     */
    @Query(value = "select year, sum(count) from reported_info group by year ",nativeQuery = true)
    List<Object[]> getBeReportedByYear();

    @Query(value = "select year, sum(count) from reported_info where area_id = ?1 group by year ",nativeQuery = true)
    List<Object[]> getBeReportedByYear(int areaId);
}
