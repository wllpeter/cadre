package com.ddb.xaplan.cadre.controller;

import com.ddb.xaplan.cadre.common.DataInfo;
import com.ddb.xaplan.cadre.entity.OfficerTrafficViolationDO;
import com.ddb.xaplan.cadre.entity.OfficerVehicleInfoDO;
import com.ddb.xaplan.cadre.service.OfficerBasicInfoService;
import com.ddb.xaplan.cadre.service.OfficerPrizeInfoService;
import com.ddb.xaplan.cadre.service.OfficerTrafficViolationService;
import com.ddb.xaplan.cadre.service.OfficerVerhicleInfoService;
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
 * Created by 王凯斌 on 2017/10/17.
 */
@RestController
@RequestMapping("/api/officer")
public class OfficerVerhicleInfoController {

    @Resource(name="officerVerhicleInfoServiceImpl")
    private OfficerVerhicleInfoService officerVerhicleInfoService;

    @Resource(name="officerBasicInfoServiceImpl")
    private OfficerBasicInfoService officerBasicInfoService;

    @ApiOperation(value = "search verhicle info controller")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "officerId",paramType = "PathVariable", dataType = "String") })
    @RequestMapping(value = "/{officerId}/verhicleInfo",method = RequestMethod.GET)
    public DataInfo< List<OfficerVehicleInfoDO>> search(@PathVariable Long officerId){

        List<OfficerVehicleInfoDO> items = officerVerhicleInfoService.search(
                "officerBasicInfo",officerBasicInfoService.find(officerId));
        if(items.size()==0){
            return DataInfo.error("未找到关联数据");
        }
        return DataInfo.success(items);
    }

}
