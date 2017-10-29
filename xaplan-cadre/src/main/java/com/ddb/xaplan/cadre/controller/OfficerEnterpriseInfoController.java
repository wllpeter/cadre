package com.ddb.xaplan.cadre.controller;

import com.ddb.xaplan.cadre.common.DataInfo;
import com.ddb.xaplan.cadre.entity.CompareEnterpriseInfoDO;
import com.ddb.xaplan.cadre.service.CompareEnterpriseInfoService;
import com.ddb.xaplan.cadre.service.OfficerBasicInfoService;
import com.ddb.xaplan.cadre.service.OfficerEnterpriseInfoService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ZanYang on 2017/10/17.
 */
@RestController
@RequestMapping("/api/officer")
public class OfficerEnterpriseInfoController {

    @Resource(name="officerEnterpriseInfoServiceImpl")
    private OfficerEnterpriseInfoService officerEnterpriseInfoService;

    @Resource(name="officerBasicInfoServiceImpl")
    private OfficerBasicInfoService officerBasicInfoService;

    @Resource(name = "compareEnterpriseInfoServiceImpl")
    private CompareEnterpriseInfoService compareEnterpriseInfoService;

    @ApiOperation(value = "search enterprise info controller")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "officerId",paramType = "PathVariable", dataType = "String") })
    @RequestMapping(value = "/{officerId}/enterpriseInfo",method = RequestMethod.GET)
    public DataInfo< List<CompareEnterpriseInfoDO>> search(@PathVariable Long officerId){

        List<CompareEnterpriseInfoDO> items = compareEnterpriseInfoService.search(
                "ownerId",officerBasicInfoService.find(officerId).getIdCard());
        if(items.size()==0){
            return DataInfo.error("未找到关联数据");
        }
        return DataInfo.success(items);
    }

}
