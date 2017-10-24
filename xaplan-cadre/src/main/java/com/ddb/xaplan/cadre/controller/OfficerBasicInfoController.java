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
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 王凯斌 on 2017/10/16.
 */
@RestController
@RequestMapping("/api/officer")
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

    /**
     * 返回各县党员、干部、籍贯本地外地、男女数量
     */
    @ApiOperation(value = "get basic info controller")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "areaId",paramType = "query", dataType = "Integer"),
    })
    @GetMapping(value = "/{areaId}/getCount")
    public DataInfo<Map<String,Integer>> getCount(@PathVariable Integer areaId){
        Map<String,Integer> map=new HashMap<String,Integer>();
        //党员数量
        int partyMemberCount=this.officerBasicInfoService.getPartyMemberCount(areaId);
        //干部数量
        int cadreCount=this.officerBasicInfoService.getCadreCount(areaId);
        //本地籍贯数量
        int localNativePlaceCount=this.officerBasicInfoService.getLocalNativePlace(areaId);
        //外地籍贯数量
        int fieldNativePlaceCount=this.officerBasicInfoService.getFieldNativePlace(areaId);
        //男生数量
        int manCount=this.officerBasicInfoService.getManCount(areaId);
        //女生数量
        int womanCount=this.officerBasicInfoService.getWomanCount(areaId);
        map.put("partyMemberCount",partyMemberCount);
        map.put("cadreCount",cadreCount);
        map.put("localNativePlaceCount",localNativePlaceCount);
        map.put("fieldNativePlaceCount",fieldNativePlaceCount);
        map.put("manCount",manCount);
        map.put("womanCount",womanCount);
        return DataInfo.success(map);
    }



}
