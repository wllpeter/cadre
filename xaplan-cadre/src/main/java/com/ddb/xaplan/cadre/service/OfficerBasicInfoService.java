package com.ddb.xaplan.cadre.service;

import com.ddb.xaplan.cadre.entity.AlertInfoDO;
import com.ddb.xaplan.cadre.entity.AreaDO;
import com.ddb.xaplan.cadre.entity.OfficerBasicInfoDO;
import com.ddb.xaplan.cadre.enums.Gender;
import com.ddb.xaplan.cadre.enums.TitleLevel;
import com.ddb.xaplan.cadre.vo.ComparedBasicVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

/**
 * Created by 王凯斌 on 2017/10/17.
 */
public interface OfficerBasicInfoService extends BaseService<OfficerBasicInfoDO>{

    ComparedBasicVO findVO(OfficerBasicInfoDO officerBasicInfoDO, List<AlertInfoDO> alertInfoDOList);
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
     * 获取各县男女生数量
     */
    public int getGenderCount(int areaId,int gender);



    /**
     *获取各县硕士及以上学历人数
     */
    public int getMasterCount(int areaId);

    /**
     *获取各县大学本科学历人数
     *
     */
    public int getUndergraduateCount(int areaId);

    /**
     *获取各县大学专科学历人数
     *
     */
    public int getProfessionColleageCount(int areaId);

    /**
     *获取各县中专及以下学历人数
     *
     */
    public int getSecondaryBelowCount(int areaId);

    /**
     * 获取各县年龄30岁以下人数
     */
    public int getThirtyBelowCount(int areaId);

    /**
     * 获取各县年龄31-40岁人数
     */
    public int getThirtyAndFourtyCount(int areaId);

    /**
     * 获取各县年龄41-50岁人数
     */
    public int getFourtyAndFivtyCount(int areaId);

    /**
     * 获取各县年龄51岁及以上人数
     */
    public int getFivtyAboveCount(int areaId);

    /**
     *获取各县各职级人数
     */
    public int getCountyCount(int areaId,int title_level);

    /**
     * 获取各县村干部数量
     */
    Integer getVillageCadresCount(int areaId);

    /**
     *获取各县科员数量
     */
    Integer getClerkCount(int areaId);





    Page<OfficerBasicInfoDO> search(
            String keyword, AreaDO area, String org, TitleLevel titleLevel,
            Gender gender, Integer minimumAge, Integer maxAge, Pageable pageable,
            String userAreaCode,String culture,Date date);





}
