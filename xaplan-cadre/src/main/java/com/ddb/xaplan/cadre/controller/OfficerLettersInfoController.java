package com.ddb.xaplan.cadre.controller;

import com.ddb.xaplan.cadre.common.DataInfo;
import com.ddb.xaplan.cadre.entity.OfficerLettersInfoDO;
import com.ddb.xaplan.cadre.service.OfficerLettersInfoService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 付鸣 on 2017/10/25.
 * 信访举报
 */
@RestController
@RequestMapping("/api/officerLettersInfo")
public class OfficerLettersInfoController {

    //信访举报
    @Resource(name="officerLettersInfoServiceImpl")
    private OfficerLettersInfoService officerLettersInfoService;

    @ApiOperation(value = "search Letters info controller")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "areaName",paramType = "PathVariable", dataType = "String") })
    @GetMapping(value="/getLettersStatistics")
    public DataInfo<List<OfficerLettersInfoDO>> getLettersStatistics(@RequestParam("areaName") String areaName){
        int areaId=0;
        if(areaName.equals("雄县")){
            areaId=3;
        }else if (areaName.equals("容城县")){
            areaId=2;
        }else if (areaName.equals("安新县")){
            areaId=1;
        }else{
            areaId=0;
        }
        List<OfficerLettersInfoDO> list=this.officerLettersInfoService.getLettersStatistics(areaId);
        if(list.size()==0){
            return DataInfo.error("未找到关联数据");
        }
        return DataInfo.success(list);

    }



}
