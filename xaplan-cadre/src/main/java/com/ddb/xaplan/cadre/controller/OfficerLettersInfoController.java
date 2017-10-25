package com.ddb.xaplan.cadre.controller;

import com.ddb.xaplan.cadre.common.DataInfo;
import com.ddb.xaplan.cadre.entity.OfficerLettersInfoDO;
import com.ddb.xaplan.cadre.service.OfficerLettersInfoService;
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
 * Created by 付鸣 on 2017/10/25.
 * 信访举报
 */
@RestController
@RequestMapping("/api/officerLettersInfo")
public class OfficerLettersInfoController {

    //违纪涉法
    @Resource(name="officerLettersInfoServiceImpl")
    private OfficerLettersInfoService officerLettersInfoService;

    @ApiOperation(value = "search Letters info controller")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "areaId",paramType = "PathVariable", dataType = "Integer") })
    @GetMapping(value="/{areaId}/getLettersStatistics")
    public DataInfo<List<OfficerLettersInfoDO>> getLettersStatistics(@PathVariable Integer areaId){
        List<OfficerLettersInfoDO> list=this.officerLettersInfoService.getLettersStatistics(areaId);
        if(list.size()==0){
            return DataInfo.error("未找到关联数据");
        }
        return DataInfo.success(list);

    }



}
