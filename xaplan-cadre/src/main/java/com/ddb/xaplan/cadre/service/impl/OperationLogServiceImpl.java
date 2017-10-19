package com.ddb.xaplan.cadre.service.impl;

import com.ddb.xaplan.cadre.dao.OperationLogDao;
import com.ddb.xaplan.cadre.entity.OperationLogDO;
import com.ddb.xaplan.cadre.service.OperationLogService;
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
import java.util.List;

/**
 * Created by 王凯斌 on 2017/10/17.
 */
@Service("operationLogServiceImpl")
public class OperationLogServiceImpl extends BaseServiceImpl<OperationLogDO> implements OperationLogService {


    @Autowired
    private OperationLogDao operationLogDao;

    @Override
    public Page<OperationLogDO> search(String keyword, Date startDate, Date endDate, Pageable pageable) {
        return operationLogDao.findAll(new Specification<OperationLogDO>() {

            @Override
            public Predicate toPredicate(Root<OperationLogDO> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = criteriaBuilder.conjunction();
                if (StringUtils.isNotEmpty(keyword)) {
                    predicate.getExpressions().add(
                            criteriaBuilder.or(
                                    criteriaBuilder.like(root.get("idCard"), "%" +keyword+"%"),
                                    criteriaBuilder.like(root.get("userName"), "%" +keyword+"%")
                            )
                    );
                }
                if(endDate!=null){
                    predicate.getExpressions().add(
                            criteriaBuilder.lessThanOrEqualTo(root.get("createDate"), endDate));
                }
                if(startDate!=null){
                    predicate.getExpressions().add(
                            criteriaBuilder.greaterThanOrEqualTo(root.get("createDate"), startDate));
                }
                return predicate;
            }
        },pageable);
    }

    @Override
    public List<OperationLogDO> search(Date startDate, Date endDate) {
        return operationLogDao.findAll(new Specification<OperationLogDO>() {
            @Override
            public Predicate toPredicate(Root<OperationLogDO> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = criteriaBuilder.conjunction();
                if(endDate!=null){
                    predicate.getExpressions().add(
                            criteriaBuilder.lessThanOrEqualTo(root.get("createDate"), endDate));
                }
                if(startDate!=null){
                    predicate.getExpressions().add(
                            criteriaBuilder.greaterThanOrEqualTo(root.get("createDate"), startDate));
                }
                return predicate;
            }
        });
    }
}
