package com.ddb.xaplan.cadre.controller;

import com.ddb.xaplan.cadre.common.DataInfo;
import com.ddb.xaplan.cadre.entity.OfficerAssessmentInfoDO;
import com.ddb.xaplan.cadre.service.OfficerAssessmentInfoService;
import com.ddb.xaplan.cadre.service.OfficerBasicInfoService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ZanYang on 2017/10/17.
 */
@RestController
@RequestMapping("/api/officer")
public class OfficerAssessmentInfoController {

    @Resource(name="officerAssessmentInfoServiceImpl")
    private OfficerAssessmentInfoService officerAssessmentInfoService;

    @Resource(name="officerBasicInfoServiceImpl")
    private OfficerBasicInfoService officerBasicInfoService;

    @ApiOperation(value = "search assessment info controller")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "officerId",paramType = "PathVariable", dataType = "String") })
    @RequestMapping(value = "/{officerId}/assessmentInfo",method = RequestMethod.GET)
    public DataInfo< List<OfficerAssessmentInfoDO>> search(@PathVariable Long officerId){

        List<OfficerAssessmentInfoDO> items = officerAssessmentInfoService.search(
                "officerBasicInfo",officerBasicInfoService.find(officerId));
        if(items.size()==0){
            return DataInfo.error("未找到关联数据");
        }
        return DataInfo.success(items);
    }

}
