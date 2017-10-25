package com.ddb.xaplan.cadre.service.impl;

import com.ddb.xaplan.cadre.dao.OfficerBasicInfoDao;
import com.ddb.xaplan.cadre.entity.AreaDO;
import com.ddb.xaplan.cadre.entity.OfficerBasicInfoDO;
import com.ddb.xaplan.cadre.enums.Gender;
import com.ddb.xaplan.cadre.enums.TitleLevel;
import com.ddb.xaplan.cadre.service.OfficerBasicInfoService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by 王凯斌 on 2017/10/17.
 */
@Service("officerBasicInfoServiceImpl")
public class OfficerBasicInfoServiceImpl extends BaseServiceImpl<OfficerBasicInfoDO> implements OfficerBasicInfoService{

    @Autowired
    private OfficerBasicInfoDao officerBasicInfoDao;

    @Override
    public Page<OfficerBasicInfoDO> search(
            String keyword, AreaDO area, String org, TitleLevel titleLevel,
            Gender gender, Integer minimumAge, Integer maxAge, Pageable pageable) {

        return officerBasicInfoDao.findAll(new Specification<OfficerBasicInfoDO>() {

            @Override
            public Predicate toPredicate(Root<OfficerBasicInfoDO> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                Predicate predicate = criteriaBuilder.conjunction();
                if (StringUtils.isNotEmpty(keyword)) {
                    predicate.getExpressions().add(
                            criteriaBuilder.or(
                                    criteriaBuilder.like(root.get("name"), "%" +keyword+"%"),
                                    criteriaBuilder.like(root.get("idCard"), "%" +keyword+"%")
                                    )
                            );
                }
                if(area!=null){
                    predicate.getExpressions().add(
                            criteriaBuilder.like(root.get("areaIds"), "%" +area.getId()+"%"));
                }
                if(StringUtils.isNotEmpty(org)){
                    predicate.getExpressions().add(
                            criteriaBuilder.like(root.get("organization"), "%" +org+"%"));
                }
                if(titleLevel!=null){
                    predicate.getExpressions().add(
                            criteriaBuilder.equal(root.get("titleLevel"), titleLevel));
                }
                if(gender!=null){
                    predicate.getExpressions().add(
                            criteriaBuilder.equal(root.get("gender"), gender));
                }
                if(minimumAge!=null){
                    predicate.getExpressions().add(
                            criteriaBuilder.lessThanOrEqualTo(
                                    root.get("birthDate"),
                                    DateUtils.addYears(new Date(),-minimumAge)));
                }
                if(maxAge!=null){
                    predicate.getExpressions().add(
                            criteriaBuilder.greaterThanOrEqualTo(
                                    root.get("birthDate"),
                                    DateUtils.addYears(new Date(),-maxAge)));
                }
                return predicate;
            }
        },pageable);
    }


    /**
     * 获取各县党员数量
     * @param areaId
     * @return
     */
    @Override
    public int getPartyMemberCount(int areaId) {
        return this.officerBasicInfoDao.getPartyMemberCount(areaId);
    }

    /**
     * 获取各县干部总数量
     */
    @Override
    public int getCadreCount(int areaId) {
        return this.officerBasicInfoDao.getCadreCount(areaId);
    }

    /**
     * 获取各县籍贯本地的数量
     */
    @Override
    public int getLocalNativePlace(int areaId) {
        return this.officerBasicInfoDao.getLocalNativePlace(areaId);
    }

    /**
     * 获取各县籍贯外地的数量
     */
    @Override
    public int getFieldNativePlace(int areaId) {
        return this.officerBasicInfoDao.getFieldNativePlace(areaId);
    }

    /**
     * 获取各县男女性别数量
     */
    @Override
    public int getGenderCount(int areaId,int gender) {
        return this.officerBasicInfoDao.getGenderCount(areaId,gender);
    }



    /**
     *获取各县硕士及以上学历人数
     */
    @Override
    public int getMasterCount(int areaId) {
        return this.officerBasicInfoDao.getMasterCount(areaId);
    }

    /**
     *获取各县大学本科学历人数
     *
     */
    @Override
    public int getUndergraduateCount(int areaId) {
        return this.officerBasicInfoDao.getUndergraduateCount(areaId);
    }

    /**
     *获取各县大学专科学历人数
     *
     */
    @Override
    public int getProfessionColleageCount(int areaId) {
        return this.officerBasicInfoDao.getProfessionColleageCount(areaId);
    }

    /**
     *获取各县中专及以下学历人数
     *
     */
    @Override
    public int getSecondaryBelowCount(int areaId) {
        return this.officerBasicInfoDao.getSecondaryBelowCount(areaId);
    }

    /**
     * 获取各县年龄30岁以下人数
     */
    @Override
    public int getThirtyBelowCount(int areaId) {
        return this.officerBasicInfoDao.getThirtyBelowCount(areaId);
    }

    /**
     * 获取各县年龄31-40岁人数
     */
    @Override
    public int getThirtyAndFourtyCount(int areaId) {
        return this.officerBasicInfoDao.getThirtyAndFourtyCount(areaId);
    }

    /**
     * 获取各县年龄41-50岁人数
     */
    @Override
    public int getFourtyAndFivtyCount(int areaId) {
        return this.officerBasicInfoDao.getFourtyAndFivtyCount(areaId);
    }

    /**
     * 获取各县年龄51岁及以上人数
     */
    @Override
    public int getFivtyAboveCount(int areaId) {
        return this.officerBasicInfoDao.getFivtyAboveCount(areaId);
    }

    /**
     *获取各县各职级人数
     */
    @Override
    public int getCountyCount(int areaId, int title_level) {
        return this.officerBasicInfoDao.getCountyCount(areaId,title_level);
    }




}
