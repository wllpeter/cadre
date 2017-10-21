package com.ddb.xaplan.cadre.service.impl;

import com.ddb.xaplan.cadre.dao.CompareBasicInfoDao;
import com.ddb.xaplan.cadre.dao.OfficerBasicInfoDao;
import com.ddb.xaplan.cadre.entity.AreaDO;
import com.ddb.xaplan.cadre.entity.CompareBasicInfoDO;
import com.ddb.xaplan.cadre.entity.OfficerBasicInfoDO;
import com.ddb.xaplan.cadre.enums.Gender;
import com.ddb.xaplan.cadre.enums.TitleLevel;
import com.ddb.xaplan.cadre.service.CompareBasicInfoService;
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
@Service("compareBasicInfoServiceImpl")
public class CompareBasicInfoServiceImpl extends BaseServiceImpl<CompareBasicInfoDO> implements CompareBasicInfoService {

    @Autowired
    private CompareBasicInfoDao compareBasicInfoDao;

}
