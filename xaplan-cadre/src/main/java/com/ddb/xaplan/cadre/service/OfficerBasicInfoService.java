package com.ddb.xaplan.cadre.service;

import com.ddb.xaplan.cadre.entity.AreaDO;
import com.ddb.xaplan.cadre.entity.OfficerBasicInfoDO;
import com.ddb.xaplan.cadre.enums.Gender;
import com.ddb.xaplan.cadre.enums.TitleLevel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by 王凯斌 on 2017/10/17.
 */
public interface OfficerBasicInfoService extends BaseService<OfficerBasicInfoDO>{
    /**
     * 获取各县党员数量
     */
    public int getPartyMemberCount(int areaId);

    /**
     * 获取各县干部总数量
     */
    public int getCadreCount(int areaId);

    /**
     * 获取各县籍贯本地的数量
     */
    public int getLocalNativePlace(int areaId);

    /**
     * 获取各县籍贯外地的数量
     */
    public int getFieldNativePlace(int areaId);

    /**
     * 获取各县男生数量
     */
    public int getManCount(int areaId);

    /**
     * 获取各县女生数量
     */
    public int getWomanCount(int areaId);


    Page<OfficerBasicInfoDO> search(
            String keyword, AreaDO area, String org, TitleLevel titleLevel,
            Gender gender, Integer minimumAge, Integer maxAge, Pageable pageable);





}
