package com.ddb.xaplan.cadre.controller;

import com.ddb.xaplan.cadre.common.DataInfo;
import com.ddb.xaplan.cadre.entity.OfficerBasicInfoDO;
import com.ddb.xaplan.cadre.enums.Gender;
import com.ddb.xaplan.cadre.enums.TitleLevel;
import com.ddb.xaplan.cadre.service.AreaService;
import com.ddb.xaplan.cadre.service.OfficerBasicInfoService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by 王凯斌 on 2017/10/16.
 */
@RestController("/api/officerBasicInfo")
public class OfficerBasicInfoController {

    @Resource(name="officerBasicInfoServiceImpl")
    private OfficerBasicInfoService officerBasicInfoService;

    @Resource(name="areaServiceImpl")
    private AreaService areaService;

    @ApiOperation(value = "search basic info controller")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyword",paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "areaId",paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "org",paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "titleLevel",paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "gender",paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "minimumAge",paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "maxAge",paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "size",paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "page",paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "sort",paramType = "query", dataType = "String")
    })
    @RequestMapping(method = RequestMethod.GET)
    public DataInfo<Page<OfficerBasicInfoDO>> search(
            String keyword, Long areaId, String org, TitleLevel titleLevel,
            Gender gender, Integer minimumAge, Integer maxAge,
            @PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {

        return DataInfo.success(
                officerBasicInfoService.search(
                        keyword,areaService.find(areaId),org,titleLevel,
                        gender,minimumAge,maxAge,pageable));
    }

    @ApiOperation(value = "get basic info controller")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "officerId",paramType = "query", dataType = "String"),
    })
    @RequestMapping(value = "/{officerId}",method = RequestMethod.GET)
    public DataInfo<OfficerBasicInfoDO> search(@PathVariable Long officerId) {

        return DataInfo.success(
                officerBasicInfoService.find(officerId));
    }
}
