package com.ddb.xaplan.cadre.service;

import com.ddb.xaplan.cadre.entity.AlertInfoDO;
import com.ddb.xaplan.cadre.entity.AreaDO;
import com.ddb.xaplan.cadre.entity.CompareBasicInfoDO;
import com.ddb.xaplan.cadre.entity.OfficerBasicInfoDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by 王凯斌 on 2017/10/17.
 */
public interface AlertInfoService extends BaseService<AlertInfoDO>{

    Page<AlertInfoDO> search(String keyword,AreaDO areaDO,
                             AlertInfoDO.AlertType alertType, Pageable pageable);

    AlertInfoDO compareBasicInfo(OfficerBasicInfoDO source, CompareBasicInfoDO compared, String attr);
}
