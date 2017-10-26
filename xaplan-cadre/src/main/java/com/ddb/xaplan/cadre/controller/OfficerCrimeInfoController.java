package com.ddb.xaplan.cadre.controller;

import com.ddb.xaplan.cadre.common.DataInfo;
import com.ddb.xaplan.cadre.entity.OfficerCrimeInfoDO;
import com.ddb.xaplan.cadre.entity.StatisticsBean;
import com.ddb.xaplan.cadre.service.OfficerBasicInfoService;
import com.ddb.xaplan.cadre.service.OfficerCrimeInfoService;
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
 * 立案查处
 */

@RestController
@RequestMapping("/api/officerCrimeInfo")
public class OfficerCrimeInfoController {
    //违纪涉法
    @Resource(name="officerCrimeInfoServiceImpl")
    private OfficerCrimeInfoService officerCrimeInfoService;

    //干部信息
    @Resource(name="officerBasicInfoServiceImpl")
    private OfficerBasicInfoService officerBasicInfoService;


    @ApiOperation(value = "search Crime info controller")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "officerId",paramType = "PathVariable", dataType = "String") })
    @GetMapping(value="/{officerId}/searchInfo")
    public DataInfo<List<OfficerCrimeInfoDO>> search(@PathVariable Long officerId){
        List<OfficerCrimeInfoDO> items = officerCrimeInfoService.search(
                "officerBasicInfo",officerBasicInfoService.find(officerId));
        if(items.size()==0){
            return DataInfo.error("未找到关联数据");
        }
        return DataInfo.success(items);
    }



    /**
     * 立案统计接口
     */
    @ApiOperation(value = "search CrimeStatistics info controller")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "areaName",paramType = "PathVariable", dataType = "String") })
    @GetMapping(value="/getCrimeStatistics")
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
        List<StatisticsBean> yearList=this.officerCrimeInfoService.getGroupYearCrimeCount(areaId);
        List<StatisticsBean> crimeList=this.officerCrimeInfoService.getGroupCrimeCount(areaId);
        map.put("yearList",yearList);
        map.put("crimeList",crimeList);
        Set<String> keys=map.keySet();
        for (String str:keys ) {
            if(map.get(str).size()==0){
                return DataInfo.error("未找到关联数据");
            }
        }
        return DataInfo.success(map);
    }





}
