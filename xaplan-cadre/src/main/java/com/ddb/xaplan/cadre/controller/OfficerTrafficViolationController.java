package com.ddb.xaplan.cadre.controller;

import com.ddb.xaplan.cadre.common.DataInfo;
import com.ddb.xaplan.cadre.entity.OfficerPrizeInfoDO;
import com.ddb.xaplan.cadre.entity.OfficerTrafficViolationDO;
import com.ddb.xaplan.cadre.service.OfficerBasicInfoService;
import com.ddb.xaplan.cadre.service.OfficerPrizeInfoService;
import com.ddb.xaplan.cadre.service.OfficerTrafficViolationService;
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
public class OfficerTrafficViolationController {

    @Resource(name="officerTrafficViolationServiceImpl")
    private OfficerTrafficViolationService officerTrafficViolationService;

    @Resource(name="officerBasicInfoServiceImpl")
    private OfficerBasicInfoService officerBasicInfoService;

    @ApiOperation(value = "search traffic violation controller")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "officerId",paramType = "PathVariable", dataType = "String") })
    @RequestMapping(value = "/{officerId}/trafficViolation",method = RequestMethod.GET)
    public DataInfo< List<OfficerTrafficViolationDO>> search(@PathVariable Long officerId){

        List<OfficerTrafficViolationDO> items = officerTrafficViolationService.search(
                "officerBasicInfo",officerBasicInfoService.find(officerId));
        if(items.size()==0){
            return DataInfo.error("未找到关联数据");
        }
        return DataInfo.success(items);
    }

}
