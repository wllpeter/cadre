package com.ddb.xaplan.cadre.controller;

import com.ddb.xaplan.cadre.common.DataInfo;
import com.ddb.xaplan.cadre.entity.OfficerLettersInfoDO;
import com.ddb.xaplan.cadre.entity.StatisticsBean;
import com.ddb.xaplan.cadre.service.LetterNatureService;
import com.ddb.xaplan.cadre.service.OfficerLettersInfoService;
import com.ddb.xaplan.cadre.service.ReportedService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Resource(name = "reportedServiceImpl")
    private ReportedService reportedService;
    @Resource(name = "letterNatureServiceImpl")
    private LetterNatureService letterNatureService;

    @ApiOperation(value = "信访举报弹窗接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "areaName",paramType = "query", dataType = "String") })
    @GetMapping(value="/getLettersStatistics")
    public DataInfo<Map<String, Object>> getLettersStatistics(String areaName){
        Map<String,Object> result = new HashMap<>();
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
        List<StatisticsBean> reportedList = this.reportedService.getBeReportedList(areaId);
        List<StatisticsBean> natureList = this.letterNatureService.getNatureList(areaId);
        result.put("letter_resource",list);
        result.put("reported",reportedList);
        result.put("nature",natureList);
        return DataInfo.success(result);

    }

}
