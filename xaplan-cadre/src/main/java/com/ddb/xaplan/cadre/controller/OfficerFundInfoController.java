package com.ddb.xaplan.cadre.controller;


import com.ddb.xaplan.cadre.common.DataInfo;
import com.ddb.xaplan.cadre.entity.OfficerFundInfoDO;
import com.ddb.xaplan.cadre.entity.OfficerStockInfoDO;
import com.ddb.xaplan.cadre.service.OfficerBasicInfoService;
import com.ddb.xaplan.cadre.service.OfficerFundInfoService;
import com.ddb.xaplan.cadre.service.OfficerStockInfoService;
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
 * Created by 付鸣 on 2017/10/18.
 * 培训信息
 */
@RestController
@RequestMapping("/api/officerFundInfo")
public class OfficerFundInfoController {

    @Resource(name="officerFundInfoServiceImpl")
    private OfficerFundInfoService officerFundInfoService;

    //干部信息
    @Resource(name="officerBasicInfoServiceImpl")
    private OfficerBasicInfoService officerBasicInfoService;

    @ApiOperation(value = "search stock info controller")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "officerId",paramType = "PathVariable", dataType = "String") })
    @GetMapping(value="/{officerId}/searchInfo")
    public DataInfo<List<OfficerFundInfoDO>> search(@PathVariable Long officerId){
        List<OfficerFundInfoDO> items=this.officerFundInfoService.search(
            "officerBasicInfo",this.officerBasicInfoService.find(officerId)
        );
        if(items.size()==0){
            return DataInfo.error("未找到关联数据");
        }
        return DataInfo.success(items);
    }

}
