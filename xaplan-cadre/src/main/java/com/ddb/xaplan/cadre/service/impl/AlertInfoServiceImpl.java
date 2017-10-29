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

import javax.annotation.PostConstruct;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 王凯斌 on 2017/10/17.
 */
@Service("alertInfoServiceImpl")
public class AlertInfoServiceImpl extends BaseServiceImpl<AlertInfoDO> implements AlertInfoService {
    private final String ANXIN = ",1,";
    private final String RONGCHENG = ",2,";
    private final String XIONGXIAN = ",3,";
    @Autowired
    private AlertInfoDao alertInfoDao;

    private Map<String,String> dict = new HashMap<>();

    @PostConstruct
    private void init(){
        dict.put("name","姓名");
        dict.put("gender","性别");
        dict.put("culture","民族");
        dict.put("birthDate","生日");
        dict.put("nativePlace","籍贯");
        dict.put("address","地址");
    }

    @Override
    public Page<AlertInfoDO> search(Integer minimum,String keyword, AreaDO areaDO,
                                    AlertInfoDO.AlertType alertType, Pageable pageable) {

        return alertInfoDao.findAll(new Specification<AlertInfoDO>() {

            @Override
            public Predicate toPredicate(Root<AlertInfoDO> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = criteriaBuilder.conjunction();
                if (StringUtils.isNotEmpty(keyword)) {
                    predicate.getExpressions().add(
                            criteriaBuilder.or(
                                    criteriaBuilder.like(root.get("organization"), "%" +keyword+"%"),
                                    criteriaBuilder.like(root.get("content"), "%" +keyword+"%")
                            )
                    );
                }
                if(minimum!=null){
                    predicate.getExpressions().add(
                            criteriaBuilder.greaterThanOrEqualTo(root.get("amount"),minimum));
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

            if(sourceValue==null||"".equals(sourceValue)){
                describe.put("sourceValue","组织部: - ");
            }else{
                describe.put("sourceValue","组织部:"+sourceValue);
            }

            if(comparedValue==null||"".equals(comparedValue)){
                describe.put("comparedValue","公安局: - ");
            }else{
                describe.put("comparedValue","公安局:"+comparedValue);
            }

            AlertInfoDO alertInfoDO = new AlertInfoDO();
            alertInfoDO.setOfficerBasicInfo(source);
            alertInfoDO.setAlertType(AlertInfoDO.AlertType.BASIC);
            alertInfoDO.setArea(source.getArea());
            alertInfoDO.setIdCard(source.getIdCard());
            alertInfoDO.setName(source.getName());
            alertInfoDO.setOrganization(source.getOrganization());
            alertInfoDO.setPhoto(source.getPhoto());
            alertInfoDO.setTitle(source.getTitle());
            alertInfoDO.setAreaIds(source.getAreaIds());
            alertInfoDO.setContent(dict.get(attr));
            alertInfoDO.setDescription(JSON.toJSONString(describe, SerializerFeature.WriteNullStringAsEmpty));

            return alertInfoDao.save(alertInfoDO);
        }
        return null;
    }

    @Override
    public HashMap<String, Object> getAlertCountByContent() {
        List<Object[]> alertsRet = this.alertInfoDao.getAlertCountByContent();
        if(null == alertsRet || alertsRet.size() == 0){
            return null;
        }
        HashMap<String, Object> ret = new HashMap<>();
        for (Object[] object: alertsRet) {
            ret.put(String.valueOf(object[0]),Integer.valueOf(object[1].toString()));
            continue;
        }
        return ret;
    }

    @Override
    public HashMap<String, Object> getAlertCountByArea() {
        List<String> areaList = this.alertInfoDao.getAlertCountByArea();
        int anxinCount = 0;
        int xiongxianCount = 0;
        int rongchengCount = 0;
        for(String str : areaList){
            if(null == str){
                continue;
            }
            if(str.contains(ANXIN)){
                anxinCount++;
                continue;
            }
            else if(str.contains(RONGCHENG)){
                rongchengCount++;
                continue;
            }
            else if(str.contains(XIONGXIAN)){
                xiongxianCount++;
                continue;
            }
            else {
                continue;
            }
        }
        HashMap<String,Object> ret = new HashMap<>();
        ret.put("1" , anxinCount);
        ret.put("2" , rongchengCount);
        ret.put("3" , xiongxianCount);
        return ret;
    }

    @Override
    public HashMap<String, Object> getAlertCountByContent(Integer alertType) {
        List<Object[]> alertsRet = this.alertInfoDao.getAlertCountByContent(alertType);
        if(null == alertsRet || alertsRet.size() == 0){
            return null;
        }
        HashMap<String, Object> ret = new HashMap<>();
        for (Object[] object: alertsRet) {
            ret.put(String.valueOf(object[0]),Integer.valueOf(object[1].toString()));
            continue;
        }
        return ret;
    }

    @Override
    public HashMap<String, Object> getAlertCountByArea(Integer alertType) {
        List<String> areaList = this.alertInfoDao.getAlertCountByArea(alertType);
        int anxinCount = 0;
        int xiongxianCount = 0;
        int rongchengCount = 0;
        for(String str : areaList){
            if(null == str){
                continue;
            }
            if(str.contains(ANXIN)){
                anxinCount++;
                continue;
            }
            else if(str.contains(RONGCHENG)){
                rongchengCount++;
                continue;
            }
            else if(str.contains(XIONGXIAN)){
                xiongxianCount++;
                continue;
            }
            else {
                continue;
            }
        }
        HashMap<String,Object> ret = new HashMap<>();
        ret.put("1" , anxinCount);
        ret.put("2" , rongchengCount);
        ret.put("3" , xiongxianCount);
        return ret;
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
