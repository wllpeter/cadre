package com.ddb.xaplan.cadre.controller;

import com.ddb.xaplan.cadre.common.DataInfo;
import com.ddb.xaplan.cadre.service.AreaService;
import com.ddb.xaplan.cadre.service.MedicalReimburseService;
import com.ddb.xaplan.cadre.service.OperationLogService;
import com.ddb.xaplan.cadre.vo.DiseaseRankItem;
import com.ddb.xaplan.cadre.vo.ReimburseDetailVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 王凯斌 on 2017/10/24.
 * Update ReimburseList By Zan Yang on 2017/10/25
 */
@RestController
@RequestMapping("/api/medicalReimburse")
@Api(value = "MedicalReimburseController",description = "医疗报销相关接口")
public class MedicalReimburseController {
    private final int REIMBURSE_RANK_LIMIT_RANGE = 20;//报销排名范围
    @Resource(name = "areaServiceImpl")
    private AreaService areaService;

    @Resource(name = "medicalReimburseServiceImpl")
    private MedicalReimburseService medicalReimburseService;

    @Resource(name="operationLogServiceImpl")
    private OperationLogService operationLogService;

    @ApiOperation(value = "search medical general charts")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "year",required = true,paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "areaId",required = true,paramType = "query", dataType = "Long")
    })
    @RequestMapping(value = "/statistics",method = RequestMethod.GET)
    public DataInfo<Map> statistics(Long areaId, String year) throws Exception{

        operationLogService.logger(
                null,String.format("专题分析，地区：%s",areaService.find(areaId).getName()));
        Map<String,Object> result = new HashMap<>();
        result.put("hospitalCount",
                medicalReimburseService.monthStatistics(year,1,areaId));
        result.put("nonHospitalCount",
                medicalReimburseService.monthStatistics(year,0,areaId));
        result.put("monthAmountStatistics",
                medicalReimburseService.monthAmountStatistics(year,areaId));

        return DataInfo.success(result);
    }

    @ApiOperation(value = "search medical rank")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "year",required = true,paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "areaId",required = true,paramType = "query", dataType = "Long")
    })
    @RequestMapping(value = "/rank",method = RequestMethod.GET)
    public DataInfo<List> rank(Long areaId, String year) throws Exception{

        return DataInfo.success(medicalReimburseService.diseaseRank(year,areaId));
    }

    @ApiOperation(value = "每日住院人数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "year",required = true,paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "month",required = true,paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "areaId",required = true,paramType = "query", dataType = "Long")
    })
    @RequestMapping(value = "/hosCount",method = RequestMethod.GET)
    public DataInfo<int[]> hosCount(Long areaId, String year,String month) throws Exception{

        return DataInfo.success(medicalReimburseService.hosCount(year,areaId,month));
    }

    @ApiOperation(value = "平均每日住院人数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "year",required = true,paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "areaId",required = true,paramType = "query", dataType = "Long")
    })
    @RequestMapping(value = "/average",method = RequestMethod.GET)
    public DataInfo<Map> average(Long areaId, String year) throws Exception{

        return DataInfo.success(medicalReimburseService.average(year,areaId));
    }

    @ApiOperation(value = "住院时间以及病因分析")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "year",required = true,paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "areaId",required = true,paramType = "query", dataType = "Long")
    })
    @RequestMapping(value = "/hosRank",method = RequestMethod.GET)
    public DataInfo<Map> hosRank(Long areaId, String year) throws Exception{

        Map<String,List<DiseaseRankItem>> result = new HashMap<>();
        result.put("0~7",medicalReimburseService.hosRank(year,areaId,8,1));
        result.put("8~15",medicalReimburseService.hosRank(year,areaId,16,8));
        result.put("16~30",medicalReimburseService.hosRank(year,areaId,31,16));
        result.put("30~",medicalReimburseService.hosRank(year,areaId,10000,31));

        return DataInfo.success(result);
    }

    @ApiOperation(value = "城区居民医疗保险报销次数排名接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "areaId",required = true, paramType = "query",dataType = "Long",value = "地区id"),
            @ApiImplicitParam(name = "year", required = true, paramType = "query", dataType = "Integer",value = "年度"),
            @ApiImplicitParam(name = "season", paramType = "query", dataType = "Integer",value = "季度")
    })
    @GetMapping(value = "/get_reimburse_details")
    @ResponseBody
    public DataInfo<List<ReimburseDetailVO>> getRemiburseRank(Long areaId,Integer year, Integer season){
        List<ReimburseDetailVO> result = this.medicalReimburseService.getReimburseRank(areaId, year, season, REIMBURSE_RANK_LIMIT_RANGE);
        if(null == result || result.size() == 0){
            return DataInfo.error("未取得排名数据");
        }
        return DataInfo.success(result);
    }
}
