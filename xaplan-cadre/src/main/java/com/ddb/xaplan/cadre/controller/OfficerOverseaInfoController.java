package com.ddb.xaplan.cadre.controller;

import com.ddb.xaplan.cadre.common.DataInfo;
import com.ddb.xaplan.cadre.entity.OfficerOverseaInfoDO;
import com.ddb.xaplan.cadre.service.OfficerBasicInfoService;
import com.ddb.xaplan.cadre.service.OfficerOverseaInfoService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by 陈亚兰 on 2017/10/18.
 * 出入境
 */
@RestController
@RequestMapping("/api/officer")
public class OfficerOverseaInfoController {
    @Autowired
    private OfficerBasicInfoService officerBasicInfoService;
    @Autowired
    private OfficerOverseaInfoService officerOverseaInfoService;

    @ApiOperation(value = "search audit info controller")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "officerId",paramType = "PathVariable", dataType = "String") })
    @RequestMapping(value = "/{officerId}/oversea",method = RequestMethod.GET)
    public DataInfo<List<OfficerOverseaInfoDO>> search(@PathVariable Long officerId){
        List<OfficerOverseaInfoDO> items=officerOverseaInfoService.search("officerBasicInfoDO",
                officerBasicInfoService.find(officerId));
        if(items.size()==0){
            return DataInfo.error("没有找到该条数据");
        }
       return DataInfo.success(items);
    }
}
