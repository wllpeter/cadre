package com.ddb.xaplan.cadre.controller;

import com.alibaba.fastjson.JSON;
import com.ddb.xaplan.cadre.common.DataInfo;
import com.ddb.xaplan.cadre.entity.*;
import com.ddb.xaplan.cadre.service.*;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by 王凯斌 on 2017/10/20.
 */
@RestController
@RequestMapping("/api/alertInfo")
public class AlertInfoController {

    @Resource(name = "alertInfoServiceImpl")
    private AlertInfoService alertInfoService;

    @Resource(name = "areaServiceImpl")
    private AreaService areaService;

    @Resource(name = "compareBasicInfoServiceImpl")
    private CompareBasicInfoService compareBasicInfoService;

    @Resource(name = "compareEstateInfoServiceImpl")
    private CompareEstateInfoService compareEstateInfoService;

    @Resource(name = "compareVehicleInfoServiceImpl")
    private CompareVehicleInfoService compareVehicleInfoService;

    @Resource(name = "comparePovertyInfoServiceImpl")
    private ComparePovertyInfoService comparePovertyInfoService;

    @Resource(name = "compareSubsidyInfoServiceImpl")
    private CompareSubsidyInfoService compareSubsidyInfoService;

    @Resource(name = "compareScholarshipServiceImpl")
    private CompareScholarshipService compareScholarshipService;

    @Resource(name = "compareHouseSubsidyServiceImpl")
    private CompareHouseSubsidyService compareHouseSubsidyService;

    @Resource(name = "compareSpecialCareServiceImpl")
    private CompareSpecialCareService compareSpecialCareService;

    @Resource(name = "officerBasicInfoServiceImpl")
    private OfficerBasicInfoService officerBasicInfoService;

    @Resource(name = "officerFamilyMemberInfoServiceImpl")
    private OfficerFamilyMemberInfoService officerFamilyMemberInfoService;

    @Resource(name = "officerEstateInfoServiceImpl")
    private OfficerEstateInfoService officerEstateInfoService;

    @Resource(name = "officerVehicleInfoServiceImpl")
    private OfficerVehicleInfoService officerVehicleInfoService;

    @ApiOperation(value = "alert info search controller")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyword", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "alertType", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "areaId", paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "size", paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "page", paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "sort", paramType = "query", dataType = "String")
    })
    @RequestMapping(method = RequestMethod.GET)
    public DataInfo<Page<AlertInfoDO>> search(
            String keyword, Long areaId, AlertInfoDO.AlertType alertType,
            @PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {

        return DataInfo.success(alertInfoService.search(keyword, areaService.find(areaId), alertType, pageable));
    }

    @RequestMapping(value = "/generate", method = RequestMethod.GET)
    public DataInfo<String> generate() {
        basicGenerateHelper();
        estateGenerateHelper();
        vehicleGenerateHelper();
        corruptionGenerateHelper();
        return DataInfo.success("In process");
    }

    @Async
    private void basicGenerateHelper() {
        List<OfficerBasicInfoDO> items = officerBasicInfoService.findAll();
        String[] attrs = {"name", "gender", "culture", "birthDate", "nativePlace", "address"};
        for (OfficerBasicInfoDO item : items) {
            if (StringUtils.isEmpty(item.getIdCard())) {
                continue;
            }
            List<CompareBasicInfoDO> compareBasicInfoDOS = compareBasicInfoService.search("idCard", item.getIdCard());
            if (compareBasicInfoDOS.size() == 0) {
                continue;
            }
            for (String attr : attrs) {
                alertInfoService.compareBasicInfo(item, compareBasicInfoDOS.get(0), attr);
            }

        }
    }

    @Async
    private void estateGenerateHelper() {

        List<OfficerBasicInfoDO> items = officerBasicInfoService.findAll();
        for (OfficerBasicInfoDO item : items) {

            List<CompareEstateInfoDO> comparedValue = new ArrayList<>();
            if (StringUtils.isEmpty(item.getIdCard())) {
                continue;
            }
            Set<String> idCards = getCardIds(item);


            for(String idCard:idCards){
                if (StringUtils.isEmpty(idCard)) {
                    continue;
                }
                comparedValue.addAll(
                        compareEstateInfoService.search("ownerId", idCard));
            }
            if (comparedValue.size() <= 0) {
                continue;
            }

            Map<String,Object> describe = new HashMap<>();
            describe.put("sourceValue",officerEstateInfoService.search("officerBasicInfo",item));
            describe.put("comparedValue",comparedValue);

            AlertInfoDO alertInfoDO = getAlertInfo(item,AlertInfoDO.AlertType.REGISTER);
            alertInfoDO.setContent("房产数量预警");
            alertInfoDO.setAmount(comparedValue.size());
            alertInfoDO.setDescription(JSON.toJSONString(describe));
            alertInfoService.save(alertInfoDO);
        }

    }

    @Async
    private void vehicleGenerateHelper() {

        List<OfficerBasicInfoDO> items = officerBasicInfoService.findAll();
        for (OfficerBasicInfoDO item : items) {

            if (StringUtils.isEmpty(item.getIdCard())) {
                continue;
            }
            Set<String> idCards = getCardIds(item);

            List<CompareVehicleInfoDO> comparedValue = new ArrayList<>();
            Integer amount = 0;

            for (String idCard :idCards) {
                if (StringUtils.isEmpty(idCard)) {
                    continue;
                }
                amount += compareVehicleInfoService.countByIdCard(idCard);
                comparedValue.addAll(
                        compareVehicleInfoService.search("idCard", idCard));
            }

            if (amount <= 0) {
                continue;
            }


            Map<String,Object> describe = new HashMap<>();
            describe.put("sourceValue",officerVehicleInfoService.search("officerBasicInfo",item));
            describe.put("comparedValue",comparedValue);

            AlertInfoDO alertInfoDO =getAlertInfo(item,AlertInfoDO.AlertType.REGISTER);
            alertInfoDO.setContent("车辆数量预警");
            alertInfoDO.setAmount(comparedValue.size());
            alertInfoDO.setDescription(JSON.toJSONString(describe));
            alertInfoService.save(alertInfoDO);
        }
    }

    @Async
    private void corruptionGenerateHelper() {

        List<OfficerBasicInfoDO> items = officerBasicInfoService.findAll();
        for (OfficerBasicInfoDO item : items) {

            if (StringUtils.isEmpty(item.getIdCard())) {
                continue;
            }
            Set<String> idCards = getCardIds(item);
            Set<String> names = getNames(item);

            for (String idCard :idCards) {
                for(ComparePovertyInfoDO comparePovertyInfoDO:
                        comparePovertyInfoService.search("idCard",idCard)){

                    AlertInfoDO alertInfoDO = getAlertInfo(item,AlertInfoDO.AlertType.CORRUPTION);
                    alertInfoDO.setContent("贫困户预警");
                    alertInfoDO.setDescription(JSON.toJSONString(comparePovertyInfoDO));
                    alertInfoService.save(alertInfoDO);
                }
                for(CompareSubsidyInfoDO compareSubsidyInfoDO:
                        compareSubsidyInfoService.search("idCard",idCard)){

                    AlertInfoDO alertInfoDO = getAlertInfo(item,AlertInfoDO.AlertType.CORRUPTION);
                    alertInfoDO.setContent("低保预警");
                    alertInfoDO.setDescription(JSON.toJSONString(compareSubsidyInfoDO));
                    alertInfoService.save(alertInfoDO);
                }
                for(CompareHouseSubsidyDO compareHouseSubsidy:
                        compareHouseSubsidyService.search("idCard",idCard)){

                    AlertInfoDO alertInfoDO = getAlertInfo(item,AlertInfoDO.AlertType.CORRUPTION);
                    alertInfoDO.setContent("危房改造补助预警");
                    alertInfoDO.setDescription(JSON.toJSONString(compareHouseSubsidy));
                    alertInfoService.save(alertInfoDO);
                }
                for(CompareSpecialCareDO compareSpecialCareDO:
                        compareSpecialCareService.search("idCard",idCard)){

                    AlertInfoDO alertInfoDO = getAlertInfo(item,AlertInfoDO.AlertType.CORRUPTION);
                    alertInfoDO.setContent("优抚预警");
                    alertInfoDO.setDescription(JSON.toJSONString(compareSpecialCareDO));
                    alertInfoService.save(alertInfoDO);
                }

            }

            for (String name:names){
                for(CompareScholarshipDO compareScholarship:
                        compareScholarshipService.search("name",name)){

                    AlertInfoDO alertInfoDO = getAlertInfo(item,AlertInfoDO.AlertType.CORRUPTION);
                    alertInfoDO.setContent("助学金");
                    alertInfoDO.setDescription(JSON.toJSONString(compareScholarship));
                    alertInfoService.save(alertInfoDO);
                }
            }


        }
    }

    private Set<String> getCardIds(OfficerBasicInfoDO officerBasicInfoDO){
        Set<String> idCards = new HashSet<>();

        if (StringUtils.isEmpty(officerBasicInfoDO.getIdCard())) {
            return idCards;
        }
        idCards.add(officerBasicInfoDO.getIdCard());
        for (OfficerFamilyMemberInfoDO member :
                officerFamilyMemberInfoService.search("cadre_id_card", officerBasicInfoDO.getIdCard())) {

            if (StringUtils.isEmpty(member.getIdCard())
                    ||officerBasicInfoDO.getIdCard().equals(member.getIdCard())) {
                continue;
            }
            idCards.add(member.getIdCard());
        }
        return idCards;
    }

    private Set<String> getNames(OfficerBasicInfoDO officerBasicInfoDO){
        Set<String> names = new HashSet<>();

        if (StringUtils.isEmpty(officerBasicInfoDO.getIdCard())) {
            return names;
        }
        for (OfficerFamilyMemberInfoDO member :
                officerFamilyMemberInfoService.search("cadre_id_card", officerBasicInfoDO.getIdCard())) {

            if (StringUtils.isEmpty(member.getIdCard())
                    ||officerBasicInfoDO.getIdCard().equals(member.getIdCard())) {
                continue;
            }
            names.add(member.getName());
        }
        return names;
    }

    private AlertInfoDO getAlertInfo(OfficerBasicInfoDO item,AlertInfoDO.AlertType alertType){

        AlertInfoDO alertInfoDO = new AlertInfoDO();
        alertInfoDO.setAlertType(alertType);
        alertInfoDO.setArea(item.getArea());
        alertInfoDO.setIdCard(item.getIdCard());
        alertInfoDO.setName(item.getName());
        alertInfoDO.setOrganization(item.getOrganization());
        alertInfoDO.setTitle(item.getTitle());
        alertInfoDO.setPhoto(item.getPhoto());
        return alertInfoDO;
    }
}
