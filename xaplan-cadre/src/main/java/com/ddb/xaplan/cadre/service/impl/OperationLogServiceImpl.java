package com.ddb.xaplan.cadre.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ddb.xaplan.cadre.common.DataInfo;
import com.ddb.xaplan.cadre.common.tool.HttpUtils;
import com.ddb.xaplan.cadre.dao.OperationLogDao;
import com.ddb.xaplan.cadre.entity.OperationLogDO;
import com.ddb.xaplan.cadre.service.OperationLogService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

/**
 * Created by 王凯斌 on 2017/10/17.
 */
@Service("operationLogServiceImpl")
public class OperationLogServiceImpl extends BaseServiceImpl<OperationLogDO> implements OperationLogService {


    @Autowired
    private OperationLogDao operationLogDao;

    @Value("${chain.save.url}")
    private String chainSaveUrl;

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

    @Override
    public OperationLogDO save(OperationLogDO operationLogDO) {

        try {
            super.save(operationLogDO);
            HttpUtils.get(String.format(
                    chainSaveUrl,
                    URLEncoder.encode(String.valueOf(operationLogDO.getCreateDate().getTime()),"UTF-8"),
                    URLEncoder.encode(JSON.toJSONString(operationLogDO),"UTF-8")));
        }catch (Exception e){
            e.printStackTrace();
        }
        return operationLogDO;
    }

    @Async
    @Override
    public void logger(String userInfo, String content) {

        JSONObject user = null;
        try{
            user = JSON.parseObject(userInfo);
        }catch (Exception e){
            user=null;
        }


        OperationLogDO operationLogDO = new OperationLogDO();
        operationLogDO.setContent(content);
        if(user!=null){
            operationLogDO.setUserName(user.getString("username"));
            operationLogDO.setUuid(user.getString("userid"));
            operationLogDO.setRole(user.getString("position"));
            operationLogDO.setOrganization(user.getString("department"));
            operationLogDO.setArea(user.getString("distinctName"));
        }

        save(operationLogDO);
    }
}
