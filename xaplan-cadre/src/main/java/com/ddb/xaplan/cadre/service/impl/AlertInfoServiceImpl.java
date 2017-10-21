package com.ddb.xaplan.cadre.service.impl;

import com.ddb.xaplan.cadre.dao.AlertInfoDao;
import com.ddb.xaplan.cadre.entity.AlertInfoDO;
import com.ddb.xaplan.cadre.entity.AreaDO;
import com.ddb.xaplan.cadre.service.AlertInfoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created by 王凯斌 on 2017/10/17.
 */
@Service("alertInfoServiceImpl")
public class AlertInfoServiceImpl extends BaseServiceImpl<AlertInfoDO> implements AlertInfoService {

    @Autowired
    private AlertInfoDao alertInfoDao;

    @Override
    public Page<AlertInfoDO> search(String keyword, AreaDO areaDO,
                                    AlertInfoDO.AlertType alertType, Pageable pageable) {

        return alertInfoDao.findAll(new Specification<AlertInfoDO>() {

            @Override
            public Predicate toPredicate(Root<AlertInfoDO> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = criteriaBuilder.conjunction();
                if (StringUtils.isNotEmpty(keyword)) {
                    predicate.getExpressions().add(
                            criteriaBuilder.like(root.get("organization"), "%" +keyword+"%")
                    );
                }
                if(areaDO!=null){
                    predicate.getExpressions().add(
                            criteriaBuilder.equal(root.get("area"), areaDO));
                }
                if(alertType!=null){
                    predicate.getExpressions().add(
                            criteriaBuilder.equal(root.get("alertType"), alertType));
                }
                return predicate;
            }
        },pageable);
    }
}
