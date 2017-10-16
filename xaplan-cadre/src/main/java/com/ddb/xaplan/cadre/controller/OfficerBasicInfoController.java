package com.ddb.xaplan.cadre.controller;

import com.ddb.xaplan.cadre.common.DataInfo;
import com.ddb.xaplan.cadre.dao.OfficerBasicInfoDao;
import com.ddb.xaplan.cadre.entity.OfficerBasicInfoDO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 王凯斌 on 2017/10/16.
 */
@RestController("/api")
public class OfficerBasicInfoController {

    @Autowired
    private OfficerBasicInfoDao officerBasicInfoDao;


    @ApiOperation(value = "add basic info controller")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "demo", required = true, paramType = "query", dataType = "String") })
    @GetMapping("/officerBasicInfo")
    public DataInfo<OfficerBasicInfoDO> add(HttpServletRequest request, @RequestParam(name = "demo") String demo) {

        OfficerBasicInfoDO officerBasicInfoDO = new OfficerBasicInfoDO();
        officerBasicInfoDO.setIdCard(demo);
        officerBasicInfoDao.save(officerBasicInfoDO);
        return new DataInfo<OfficerBasicInfoDO>(officerBasicInfoDO);
    }
}
