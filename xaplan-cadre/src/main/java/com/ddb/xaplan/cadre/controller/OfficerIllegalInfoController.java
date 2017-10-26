package com.ddb.xaplan.cadre.controller;

import com.ddb.xaplan.cadre.common.DataInfo;
import com.ddb.xaplan.cadre.entity.OfficerIllegalInfoDO;
import com.ddb.xaplan.cadre.entity.StatisticsBean;
import com.ddb.xaplan.cadre.service.OfficerBasicInfoService;
import com.ddb.xaplan.cadre.service.OfficerIllegalInfoService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by 付鸣 on 2017/10/17.
 * 违纪涉法
 */

@RestController
@RequestMapping("/api/officerIllegalInfo")
public class OfficerIllegalInfoController {
    //违纪涉法
    @Resource(name="officerIllegalInfoServiceImpl")
    private OfficerIllegalInfoService officerIllegalInfoService;

    //干部信息
    @Resource(name="officerBasicInfoServiceImpl")
    private OfficerBasicInfoService officerBasicInfoService;


    @ApiOperation(value = "search Illegal info controller")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "officerId",paramType = "PathVariable", dataType = "String") })
    @GetMapping(value="/{officerId}/searchInfo")
    public DataInfo<List<OfficerIllegalInfoDO>> search(@PathVariable Long officerId){
        List<OfficerIllegalInfoDO> items = officerIllegalInfoService.search(
                "officerBasicInfo",officerBasicInfoService.find(officerId));
        if(items.size()==0){
            return DataInfo.error("未找到关联数据");
        }
        return DataInfo.success(items);
    }


    /**
     * 违纪统计接口
     */
    @ApiOperation(value = "search IllegalStatistics info controller")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "areaName",paramType = "PathVariable", dataType = "String") })
    @GetMapping(value="/getIllegalStatistics")
    public DataInfo<Map<String,List>> getCrimeStatistics(@RequestParam("areaName") String areaName){
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
            Map<String,List> map=new HashMap<String,List>();
        List<StatisticsBean> yearList=this.officerIllegalInfoService.getGroupYearIllegalCount(areaId);
        List<StatisticsBean> punishmentList=this.officerIllegalInfoService.getGroupPunishmentIllegalCount(areaId);
        List<StatisticsBean> briefList=this.officerIllegalInfoService.getGroupBriefIllegalCount(areaId);
        map.put("yearList",yearList);
        map.put("punishmentList",punishmentList);
        map.put("briefList",briefList);
        Set<String> keys=map.keySet();
        for (String str:keys ) {
            if(map.get(str).size()==0){
                return DataInfo.error("未找到关联数据");
            }
        }
        return DataInfo.success(map);

    }









}
