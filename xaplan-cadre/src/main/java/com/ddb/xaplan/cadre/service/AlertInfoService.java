package com.ddb.xaplan.cadre.service;

import com.ddb.xaplan.cadre.entity.AlertInfoDO;
import com.ddb.xaplan.cadre.entity.AreaDO;
import com.ddb.xaplan.cadre.entity.CompareBasicInfoDO;
import com.ddb.xaplan.cadre.entity.OfficerBasicInfoDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;

/**
 * Created by 王凯斌 on 2017/10/17.
 */
public interface AlertInfoService extends BaseService<AlertInfoDO>{

    Page<AlertInfoDO> search(Integer minimum,String keyword,AreaDO areaDO,
                             AlertInfoDO.AlertType alertType, Pageable pageable);

    AlertInfoDO compareBasicInfo(OfficerBasicInfoDO source, CompareBasicInfoDO compared, String attr);

    HashMap<String,Object> getAlertCountByContent();

    HashMap<String,Object> getAlertCountByArea();

    HashMap<String,Object> getAlertCountByContent(Integer alertType);

    HashMap<String,Object> getAlertCountByArea(Integer alertType);
}
