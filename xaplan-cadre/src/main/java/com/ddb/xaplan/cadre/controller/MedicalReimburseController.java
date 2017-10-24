package com.ddb.xaplan.cadre.controller;

import com.ddb.xaplan.cadre.common.DataInfo;
import com.ddb.xaplan.cadre.service.AreaService;
import com.ddb.xaplan.cadre.service.MedicalReimburseService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 王凯斌 on 2017/10/24.
 */
@RestController
@RequestMapping("/api/medicalReimburse")
public class MedicalReimburseController {

    @Resource(name = "areaServiceImpl")
    private AreaService areaService;

    @Resource(name = "medicalReimburseServiceImpl")
    private MedicalReimburseService medicalReimburseService;

    @ApiOperation(value = "search medical general charts")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "year",paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "areaId",paramType = "query", dataType = "Long")
    })
    @RequestMapping(value = "/statistics",method = RequestMethod.GET)
    public DataInfo<Map> statistics(Long areaId, String year) throws Exception{

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
            @ApiImplicitParam(name = "year",paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "areaId",paramType = "query", dataType = "Long")
    })
    @RequestMapping(value = "/rank",method = RequestMethod.GET)
    public DataInfo<List> rank(Long areaId, String year) throws Exception{

        return DataInfo.success(medicalReimburseService.diseaseRank(year,areaId));
    }
}
