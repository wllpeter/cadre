package com.ddb.xaplan.cadre.controller;

import com.ddb.xaplan.cadre.common.DataInfo;
import com.ddb.xaplan.cadre.entity.OfficerEducationInfoDO;
import com.ddb.xaplan.cadre.service.OfficerBasicInfoService;
import com.ddb.xaplan.cadre.service.OfficerEducationInfoService;
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
 * Created by 陈亚兰 on 2017/10/17.
 */
@RestController
@RequestMapping("/api/officer")
public class OfficerEducationInfoController {
    @Autowired
    private OfficerBasicInfoService officerBasicInfoService;

    @Autowired
    private OfficerEducationInfoService officerEducationInfoService;


    @ApiOperation(value = "search education info controller")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "officerId",paramType = "PathVariable", dataType = "String") })
    @RequestMapping(value = "/{officerId}/education",method = RequestMethod.GET)
    public DataInfo<List<OfficerEducationInfoDO>> search(@PathVariable Long officerId){

        List<OfficerEducationInfoDO> items = officerEducationInfoService.search(
                "officerBasicInfoDO",officerBasicInfoService.find(officerId));
        if(items.size()==0){
            return DataInfo.error("未找到关联数据");
        }
        return DataInfo.success(items);
    }
}
