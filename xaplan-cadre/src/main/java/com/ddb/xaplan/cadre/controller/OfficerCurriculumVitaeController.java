package com.ddb.xaplan.cadre.controller;

import com.ddb.xaplan.cadre.common.DataInfo;
import com.ddb.xaplan.cadre.entity.OfficerBasicInfoDO;
import com.ddb.xaplan.cadre.entity.OfficerCurriculumVitaeDO;
import com.ddb.xaplan.cadre.service.OfficerBasicInfoService;
import com.ddb.xaplan.cadre.service.OfficerCurriculumVitaeService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
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
public class OfficerCurriculumVitaeController {

    @Resource(name="officerCurriculumVitaeServiceImpl")
    private OfficerCurriculumVitaeService officerCurriculumVitaeService;

    @Resource(name="officerBasicInfoServiceImpl")
    private OfficerBasicInfoService officerBasicInfoService;



    @ApiOperation(value = "search prize info controller")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "officerId",paramType = "PathVariable", dataType = "String") })
    @RequestMapping(value = "/{officerId}/curriculumVitae",method = RequestMethod.GET)
    public DataInfo<List<OfficerCurriculumVitaeDO>> search(@PathVariable Long officerId){
        OfficerBasicInfoDO officerBasicInfoDO=officerBasicInfoService.find(officerId);
        List<OfficerCurriculumVitaeDO> items =officerCurriculumVitaeService.search(officerBasicInfoDO);
        if(items.size()==0){
            return DataInfo.error("未找到关联数据");
        }
        return DataInfo.success(items);
    }
}
