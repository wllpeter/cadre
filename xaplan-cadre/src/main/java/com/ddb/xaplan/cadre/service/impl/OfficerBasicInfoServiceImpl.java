package com.ddb.xaplan.cadre.service.impl;

import com.ddb.xaplan.cadre.dao.OfficerBasicInfoDao;
import com.ddb.xaplan.cadre.entity.AlertInfoDO;
import com.ddb.xaplan.cadre.entity.AreaDO;
import com.ddb.xaplan.cadre.entity.OfficerBasicInfoDO;
import com.ddb.xaplan.cadre.enums.Gender;
import com.ddb.xaplan.cadre.enums.TitleLevel;
import com.ddb.xaplan.cadre.service.OfficerBasicInfoService;
import com.ddb.xaplan.cadre.vo.ComparedBasicVO;
import com.ddb.xaplan.cadre.vo.ComparedItem;
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
import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by 王凯斌 on 2017/10/17.
 */
@Service("officerBasicInfoServiceImpl")
public class OfficerBasicInfoServiceImpl extends BaseServiceImpl<OfficerBasicInfoDO> implements OfficerBasicInfoService{

    @Autowired
    private OfficerBasicInfoDao officerBasicInfoDao;

    @Override
    public ComparedBasicVO findVO(OfficerBasicInfoDO officerBasicInfoDO, List<AlertInfoDO> alertInfoDOList) {

        Map<String,String> dict = new HashMap<>();
        dict.put("name","姓名");
        dict.put("gender","性别");
        dict.put("culture","民族");
        dict.put("birthDate","生日");
        dict.put("nativePlace","籍贯");
        dict.put("address","地址");

        ComparedBasicVO result = new ComparedBasicVO();
        Map<String,AlertInfoDO> map = alertListToMap(alertInfoDOList);

        for(Field attr:ComparedBasicVO.class.getDeclaredFields()){
            try{
                Field valueField =OfficerBasicInfoDO.class.getDeclaredField(attr.getName());
                attr.setAccessible(true);
                valueField.setAccessible(true);

                if(dict.containsKey(attr.getName())){

                    if(map.containsKey(dict.get(attr.getName()))){
                        attr.set(
                                result,
                                ComparedItem.abnormal(valueField.get(officerBasicInfoDO),
                                        map.get(dict.get(attr.getName())).getDescription()
                                        ));
                        continue;
                    }
                }
                attr.set(result,ComparedItem.normal(valueField.get(officerBasicInfoDO)));
            }catch (Exception e){
                e.printStackTrace();
                continue;
            }

        }
        result.setId(officerBasicInfoDO.getId());
        return result;
    }

    private Map alertListToMap(List<AlertInfoDO> alertInfoDOList){

        Set<String> attrs = new HashSet<>();
        attrs.add("姓名");
        attrs.add("性别");
        attrs.add("民族");
        attrs.add("籍贯");
        attrs.add("地址");

        Map<String,AlertInfoDO> result = new HashMap<>();
        for(AlertInfoDO item:alertInfoDOList){
            if(attrs.contains(item.getContent())){
                result.put(item.getContent(),item);
            }
        }
        return result;
    }

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
     * 获取党员数量
     * @param areaId
     * @return
     */
    @Override
    public int getPartyMemberCount(int areaId) {
//        if(areaId==0){
//            //return this.officerBasicInfoDao.getPartyMemberSumCount();
//            return ;
//        }else{
            final int ax = 25682;
            final int rc = 12181;
            final int xx = 15095;
            switch (areaId){
                case 1: return ax;
                case 2: return rc;
                case 3: return xx;
                default: return ax+rc+xx;
            }
            //return this.officerBasicInfoDao.getPartyMemberCount(areaId);
       // }
    }

    /**
     * 获取干部总数量
     */
    @Override
    public int getCadreCount(int areaId) {
        if(areaId==0){
            return this.officerBasicInfoDao.getCadreSumCount();
        }else {
            return this.officerBasicInfoDao.getCadreCount(areaId);
        }
    }

    /**
     * 获取籍贯本地的数量
     */
    @Override
    public int getLocalNativePlace(int areaId) {
        if(areaId==0){
            return this.officerBasicInfoDao.getSumLocalNativePlace();
        }else{
            return this.officerBasicInfoDao.getLocalNativePlace(areaId);
        }

    }

    /**
     * 获取籍贯外地的数量
     */
    @Override
    public int getFieldNativePlace(int areaId) {
        if(areaId==0){
            return this.officerBasicInfoDao.getSumFieldNativePlace();
        }else{
            return this.officerBasicInfoDao.getFieldNativePlace(areaId);
        }
    }

    /**
     * 获取男女性别数量
     */
    @Override
    public int getGenderCount(int areaId,int gender) {
        if(areaId==0){
            return this.officerBasicInfoDao.getSumGenderCount(gender);
        }else{
            return this.officerBasicInfoDao.getGenderCount(areaId,gender);
        }

    }



    /**
     *获取各县硕士及以上学历人数
     */
    @Override
    public int getMasterCount(int areaId) {
        if(areaId==0){
            return this.officerBasicInfoDao.getSumMasterCount();
        }else{
            return this.officerBasicInfoDao.getMasterCount(areaId);
        }

    }

    /**
     *获取各县大学本科学历人数
     *
     */
    @Override
    public int getUndergraduateCount(int areaId) {
        if(areaId==0){
            return this.officerBasicInfoDao.getSumUndergraduateCount();
        }else{
            return this.officerBasicInfoDao.getUndergraduateCount(areaId);
        }
    }

    /**
     *获取各县大学专科学历人数
     *
     */
    @Override
    public int getProfessionColleageCount(int areaId) {
        if(areaId==0){
            return this.officerBasicInfoDao.getSumProfessionColleageCount();
        }else{
            return this.officerBasicInfoDao.getProfessionColleageCount(areaId);
        }

    }

    /**
     *获取各县中专及以下学历人数
     *
     */
    @Override
    public int getSecondaryBelowCount(int areaId) {
        if(areaId==0){
            return this.officerBasicInfoDao.getSumSecondaryBelowCount();
        }else{
            return this.officerBasicInfoDao.getSecondaryBelowCount(areaId);
        }

    }

    /**
     * 获取各县年龄30岁以下人数
     */
    @Override
    public int getThirtyBelowCount(int areaId) {
        if(areaId==0){
            return this.officerBasicInfoDao.getSumThirtyBelowCount();
        }else{
            return this.officerBasicInfoDao.getThirtyBelowCount(areaId);
        }

    }

    /**
     * 获取各县年龄31-40岁人数
     */
    @Override
    public int getThirtyAndFourtyCount(int areaId) {
        if(areaId==0){
            return this.officerBasicInfoDao.getSumThirtyAndFourtyCount();
        }else{
            return this.officerBasicInfoDao.getThirtyAndFourtyCount(areaId);
        }

    }

    /**
     * 获取各县年龄41-50岁人数
     */
    @Override
    public int getFourtyAndFivtyCount(int areaId) {
        if(areaId==0){
            return this.officerBasicInfoDao.getSumFourtyAndFivtyCount();
        }else{
            return this.officerBasicInfoDao.getFourtyAndFivtyCount(areaId);
        }
    }

    /**
     * 获取各县年龄51岁及以上人数
     */
    @Override
    public int getFivtyAboveCount(int areaId) {
        if(areaId==0){
            return this.officerBasicInfoDao.getSumFivtyAboveCount();
        }else{
            return this.officerBasicInfoDao.getFivtyAboveCount(areaId);
        }

    }

    /**
     *获取各县各职级人数
     */
    @Override
    public int getCountyCount(int areaId, int title_level) {
        if(areaId==0){
            return this.officerBasicInfoDao.getSumCountyCount(title_level);
        }else{
            return this.officerBasicInfoDao.getCountyCount(areaId,title_level);
        }

    }




}
