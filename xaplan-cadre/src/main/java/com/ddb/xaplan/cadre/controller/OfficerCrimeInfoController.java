package com.ddb.xaplan.cadre.controller;

import com.ddb.xaplan.cadre.common.DataInfo;
import com.ddb.xaplan.cadre.entity.OfficerCrimeInfoDO;
import com.ddb.xaplan.cadre.service.OfficerBasicInfoService;
import com.ddb.xaplan.cadre.service.OfficerCrimeInfoService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 付鸣 on 2017/10/17.
 * 立案查处
 */

@RestController
@RequestMapping("/api/officerCrimeInfo")
public class OfficerCrimeInfoController {
    //违纪涉法
    @Resource(name="officerCrimeInfoServiceImpl")
    private OfficerCrimeInfoService officerCrimeInfoService;

    //干部信息
    @Resource(name="officerBasicInfoServiceImpl")
    private OfficerBasicInfoService officerBasicInfoService;


    @ApiOperation(value = "search Crime info controller")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "officerId",paramType = "PathVariable", dataType = "String") })
    @GetMapping(value="/{officerId}/searchInfo")
    public DataInfo<List<OfficerCrimeInfoDO>> search(@PathVariable Long officerId){
        List<OfficerCrimeInfoDO> items = officerCrimeInfoService.search(
                "officerBasicInfo",officerBasicInfoService.find(officerId));
        if(items.size()==0){
            return DataInfo.error("未找到关联数据");
        }
        return DataInfo.success(items);
    }





}
