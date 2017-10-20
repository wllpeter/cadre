package com.ddb.xaplan.cadre.service.impl;

import com.ddb.xaplan.cadre.dao.FeedbackDao;
import com.ddb.xaplan.cadre.entity.FeedbackDO;
import com.ddb.xaplan.cadre.service.FeedbackService;
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
import java.util.Date;

/**
 * Created by 王凯斌 on 2017/10/17.
 */
@Service("feedbackServiceImpl")
public class FeedbackServiceImpl extends BaseServiceImpl<FeedbackDO> implements FeedbackService {

    @Autowired
    private FeedbackDao feedbackDao;

    @Override
    public Page<FeedbackDO> search(String keyword, Date startDate, Date endDate, Pageable pageable) {

        return feedbackDao.findAll(new Specification<FeedbackDO>() {

            @Override
            public Predicate toPredicate(Root<FeedbackDO> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = criteriaBuilder.conjunction();
                if (StringUtils.isNotEmpty(keyword)) {
                    predicate.getExpressions().add(
                            criteriaBuilder.or(
                                    criteriaBuilder.like(root.get("idCard"), "%" +keyword+"%"),
                                    criteriaBuilder.like(root.get("userName"), "%" +keyword+"%")
                            )
                    );
                }
                if(startDate!=null){
                    predicate.getExpressions().add(
                            criteriaBuilder.greaterThanOrEqualTo(root.get("createDate"), startDate));
                }
                if(endDate!=null){
                    predicate.getExpressions().add(
                            criteriaBuilder.lessThanOrEqualTo(root.get("createDate"), endDate));
                }
                return predicate;
            }
        },pageable);
    }
}
