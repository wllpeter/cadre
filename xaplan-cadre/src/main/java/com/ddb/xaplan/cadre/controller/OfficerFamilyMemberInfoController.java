package com.ddb.xaplan.cadre.controller;

import com.ddb.xaplan.cadre.common.DataInfo;
import com.ddb.xaplan.cadre.service.OfficerBasicInfoService;
import com.ddb.xaplan.cadre.service.OfficerFamilyMemberInfoService;
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
public class OfficerFamilyMemberInfoController {

    @Autowired
    private OfficerBasicInfoService officerBasicInfoService;
    @Autowired
    private OfficerFamilyMemberInfoService officerFamilyMemberInfoService;


    @ApiOperation(value = "search family info controller")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "officerId",paramType = "PathVariable", dataType = "String") })
    @RequestMapping(value = "/{officerId}/memberinfo",method = RequestMethod.GET)
    public DataInfo<List<OfficerFamilyMemberInfoDO>> search(@PathVariable Long officerId){

        List<OfficerFamilyMemberInfoDO> items = officerFamilyMemberInfoService.search(
                "officerBasicInfoDO",officerBasicInfoService.find(officerId));
        if(items.size()==0){
            return DataInfo.error("未找到关联数据");
        }
        return DataInfo.success(items);
    }
}
