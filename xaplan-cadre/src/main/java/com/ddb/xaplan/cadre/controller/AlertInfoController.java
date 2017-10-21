package com.ddb.xaplan.cadre.controller;

import com.ddb.xaplan.cadre.common.DataInfo;
import com.ddb.xaplan.cadre.entity.AlertInfoDO;
import com.ddb.xaplan.cadre.service.AlertInfoService;
import com.ddb.xaplan.cadre.service.AreaService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by 王凯斌 on 2017/10/20.
 */
@RestController
@RequestMapping("/api/alertInfo")
public class AlertInfoController {

    @Resource(name="alertInfoServiceImpl")
    private AlertInfoService alertInfoService;

    @Resource(name="areaServiceImpl")
    private AreaService areaService;


    @ApiOperation(value = "alert info search controller")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyword",paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "alertType",paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "areaId",paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "size",paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "page",paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "sort",paramType = "query", dataType = "String")
    })
    @RequestMapping(method = RequestMethod.GET)
    public DataInfo<Page<AlertInfoDO>> search(
            String keyword, Long areaId, AlertInfoDO.AlertType alertType,
            @PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {

        return DataInfo.success(alertInfoService.search(keyword,areaService.find(areaId),alertType,pageable));
    }
}
