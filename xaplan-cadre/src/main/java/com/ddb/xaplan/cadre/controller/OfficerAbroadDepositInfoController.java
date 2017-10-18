package com.ddb.xaplan.cadre.controller;

import com.ddb.xaplan.cadre.common.DataInfo;
import com.ddb.xaplan.cadre.entity.OfficerAbroadDepositInfoDO;
import com.ddb.xaplan.cadre.service.OfficerAbroadDepositInfoService;
import com.ddb.xaplan.cadre.service.OfficerBasicInfoService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 陈亚兰 on 2017/10/18.
 */
@RestController
@RequestMapping("/api/officer")
public class OfficerAbroadDepositInfoController {

    @Autowired
    private OfficerBasicInfoService officerBasicInfoService;

    @Autowired
    private OfficerAbroadDepositInfoService officerAbroadDepositInfoService;

    @ApiOperation(value = "search abroadDeposit info controller")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "officerId",paramType = "PathVariable", dataType = "String") })
    @RequestMapping(value="/{officerId}/abroadDeposit",method = RequestMethod.GET)
    public DataInfo<List<OfficerAbroadDepositInfoDO>> search(@PathVariable Long officerId){
        List<OfficerAbroadDepositInfoDO> items=officerAbroadDepositInfoService.search("officerBasicInfoDO",
                officerBasicInfoService.find(officerId));
        if(items.size()==0){
            return DataInfo.error("没有找到");
        }
        return DataInfo.success(items);
    }
}
