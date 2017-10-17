package com.ddb.xaplan.cadre.controller;

import com.ddb.xaplan.cadre.common.DataInfo;
import com.ddb.xaplan.cadre.entity.OfficerBasicInfoDO;
import com.ddb.xaplan.cadre.service.OfficerBasicInfoService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 王凯斌 on 2017/10/16.
 */
@RestController("/api")
public class OfficerBasicInfoController {

    @Resource(name="officerBasicInfoServiceImpl")
    private OfficerBasicInfoService officerBasicInfoService;

    @ApiOperation(value = "add basic info controller")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "demo",paramType = "query", dataType = "String") })
    @GetMapping("/officerBasicInfo")
    public DataInfo<List<OfficerBasicInfoDO>> search(@RequestParam(name = "demo") String demo) {

        return DataInfo.success(officerBasicInfoService.search("idCard","123"));
    }
}
