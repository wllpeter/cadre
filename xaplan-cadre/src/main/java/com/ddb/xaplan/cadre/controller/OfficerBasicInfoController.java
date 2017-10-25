package com.ddb.xaplan.cadre.controller;

import com.ddb.xaplan.cadre.common.DataInfo;
import com.ddb.xaplan.cadre.entity.OfficerBasicInfoDO;
import com.ddb.xaplan.cadre.enums.Gender;
import com.ddb.xaplan.cadre.enums.TitleLevel;
import com.ddb.xaplan.cadre.service.*;
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

    @Resource(name="officerLettersInfoServiceImpl")
    private OfficerLettersInfoService officerLettersInfoService;

    @Resource(name="officerIllegalInfoServiceImpl")
    private OfficerIllegalInfoService officerIllegalInfoService;

    @Resource(name="officerCrimeInfoServiceImpl")
    private OfficerCrimeInfoService officerCrimeInfoService;

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
    public DataInfo<Map<String,String>> getCount(@PathVariable Integer areaId){
        Map<String,String> map=new HashMap<String,String>();
        //党员数量
        String partyMemberCount=Integer.toString(this.officerBasicInfoService.getPartyMemberCount(areaId));
        //干部数量
        String cadreCount=Integer.toString(this.officerBasicInfoService.getCadreCount(areaId));
        //本地籍贯数量
        String localNativePlaceCount=Integer.toString(this.officerBasicInfoService.getLocalNativePlace(areaId));
        //外地籍贯数量
        String fieldNativePlaceCount=Integer.toString(this.officerBasicInfoService.getFieldNativePlace(areaId));
        //男生数量
        String manCount=Integer.toString(this.officerBasicInfoService.getGenderCount(areaId,0));
        //女生数量
        String womanCount=Integer.toString(this.officerBasicInfoService.getGenderCount(areaId,1));
        //硕士数量
        String masterCount=Integer.toString(this.officerBasicInfoService.getMasterCount(areaId));
        //本科数量
        String undergraduateCount=Integer.toString(this.officerBasicInfoService.getUndergraduateCount(areaId));
        //专科数量
        String professionColleageCount=Integer.toString(this.officerBasicInfoService.getProfessionColleageCount(areaId));
        //中专及以下数量
        String secondaryBelowCount=Integer.toString(this.officerBasicInfoService.getSecondaryBelowCount(areaId));
        //30岁及以下数量
        String thirtyBelowCount=Integer.toString(this.officerBasicInfoService.getThirtyBelowCount(areaId));
        //31-40数量
        String thirtyAndFourtyCount=Integer.toString(this.officerBasicInfoService.getThirtyAndFourtyCount(areaId));
        //41-50数量
        String fourtyAndFivtyCount=Integer.toString(this.officerBasicInfoService.getFourtyAndFivtyCount(areaId));
        //51及以上数量
        String fivtyAboveCount=Integer.toString(this.officerBasicInfoService.getFivtyAboveCount(areaId));
        //县处级正职
        String countyCount=Integer.toString(this.officerBasicInfoService.getCountyCount(areaId,3));
        //县处级副职
        String viceCountyCount=Integer.toString(this.officerBasicInfoService.getCountyCount(areaId,4));
        //乡科级正职
        String townShipCount=Integer.toString(this.officerBasicInfoService.getCountyCount(areaId,1));
        //乡科级副职
        String viceTownShipCount=Integer.toString(this.officerBasicInfoService.getCountyCount(areaId,2));
        //本年各县违纪记录数量
        String illegalCount=Integer.toString(this.officerIllegalInfoService.getIllegalCount(areaId));
        //本年与去年相比违纪数量增长比例
        String illegalProportion=this.officerIllegalInfoService.getIllegalProportion(areaId);
        //本年立案查处记录数量
        String crimeCount=Integer.toString(this.officerCrimeInfoService.getCrimeCount(areaId));
        //本年与去年相比立案查处数量增长比例
        String crimeProportion=this.officerCrimeInfoService.getCrimeProportion(areaId);
        //本年信访数量
        String lettersCount=Integer.toString(this.officerLettersInfoService.getLettersCount(areaId));
        //本年与去年信访数量比例
        String lettersProportion=this.officerLettersInfoService.getLettersProportion(areaId);


        map.put("partyMemberCount",partyMemberCount);
        map.put("cadreCount",cadreCount);
        map.put("localNativePlaceCount",localNativePlaceCount);
        map.put("fieldNativePlaceCount",fieldNativePlaceCount);
        map.put("manCount",manCount);
        map.put("womanCount",womanCount);
        map.put("masterCount",masterCount);
        map.put("undergraduateCount",undergraduateCount);
        map.put("professionColleageCount",professionColleageCount);
        map.put("secondaryBelowCount",secondaryBelowCount);
        map.put("thirtyBelowCount",thirtyBelowCount);
        map.put("thirtyAndFourtyCount",thirtyAndFourtyCount);
        map.put("fourtyAndFivtyCount",fourtyAndFivtyCount);
        map.put("fivtyAboveCount",fivtyAboveCount);
        map.put("countyCount",countyCount);
        map.put("viceCountyCount",viceCountyCount);
        map.put("townShipCount",townShipCount);
        map.put("viceTownShipCount",viceTownShipCount);
        map.put("illegalCount",illegalCount);
        map.put("illegalProportion",illegalProportion);
        map.put("crimeCount",crimeCount);
        map.put("crimeProportion",crimeProportion);
        return DataInfo.success(map);
    }



}
