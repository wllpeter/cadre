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
}
