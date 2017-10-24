package com.ddb.xaplan.cadre.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ddb.xaplan.cadre.dao.AlertInfoDao;
import com.ddb.xaplan.cadre.entity.AlertInfoDO;
import com.ddb.xaplan.cadre.entity.AreaDO;
import com.ddb.xaplan.cadre.entity.CompareBasicInfoDO;
import com.ddb.xaplan.cadre.entity.OfficerBasicInfoDO;
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
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

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

    @Override
    public AlertInfoDO compareBasicInfo(OfficerBasicInfoDO source, CompareBasicInfoDO compared, String attr) {

        Object sourceValue = getValue(source,attr);
        Object comparedValue = getValue(compared,attr);
        if(sourceValue==null&&comparedValue==null){
            return null;
        }
        if ((sourceValue==null||comparedValue==null)
                ||!sourceValue.equals(comparedValue)){
            Map<String,Object> describe = new HashMap<>();
            describe.put("sourceValue","组织部:"+sourceValue);
            describe.put("comparedValue","公安局:"+comparedValue);

            AlertInfoDO alertInfoDO = new AlertInfoDO();
            alertInfoDO.setAlertType(AlertInfoDO.AlertType.BASIC);
            alertInfoDO.setArea(source.getArea());
            alertInfoDO.setIdCard(source.getIdCard());
            alertInfoDO.setName(source.getName());
            alertInfoDO.setOrganization(source.getOrganization());
            alertInfoDO.setPhoto(source.getPhoto());
            alertInfoDO.setTitle(source.getTitle());
            alertInfoDO.setContent(attr);
            alertInfoDO.setDescription(JSON.toJSONString(describe, SerializerFeature.WriteNullStringAsEmpty));

            return alertInfoDao.save(alertInfoDO);
        }
        return null;
    }

    private Object getValue(Object obj, String attr){

        try {
            Field field = obj.getClass().getDeclaredField(attr);
            field.setAccessible(true);
            return field.get(obj);
        }catch (NoSuchFieldException e){
            e.printStackTrace();
            return null;
        }catch (IllegalAccessException e){
            e.printStackTrace();
            return null;
        }

    }
}
