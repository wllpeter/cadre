package com.ddb.xaplan.cadre.controller;

import com.ddb.xaplan.cadre.common.DataInfo;
import com.ddb.xaplan.cadre.entity.AreaDO;
import com.ddb.xaplan.cadre.service.AreaService;
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
 * Created by 王凯斌 on 2017/10/20.
 */
@RestController
@RequestMapping("/api/area")
public class AreaController {

    @Resource(name="areaServiceImpl")
    private AreaService areaService;

    @ApiOperation(value = "area root controller")
    @RequestMapping(value = "/root",method = RequestMethod.GET)
    public DataInfo<List<AreaDO>> root() {

        return DataInfo.success(areaService.findByGrade(3));
    }

    @ApiOperation(value = "area children controller")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "areaId",paramType = "PathVariable", dataType = "String"),
    })
    @RequestMapping(value = "/{areaId}/children",method = RequestMethod.GET)
    public DataInfo<List<AreaDO>> children(@PathVariable Long areaId) {

        return DataInfo.success(areaService.findByParent(areaService.find(areaId)));
    }
}
