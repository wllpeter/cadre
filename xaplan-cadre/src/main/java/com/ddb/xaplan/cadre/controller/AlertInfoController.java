package com.ddb.xaplan.cadre.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Resource(name = "officerBasicInfoServiceImpl")
    private OfficerBasicInfoService officerBasicInfoService;

    @Resource(name = "officerFamilyMemberInfoServiceImpl")
    private OfficerFamilyMemberInfoService officerFamilyMemberInfoService;

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
        generateHelper();
        return DataInfo.success("In process");
    }

    @RequestMapping(value = "/estateGenerate", method = RequestMethod.GET)
    public DataInfo<String> estateGenerate() {
        estateGenerateHelper();
        return DataInfo.success("In process");
    }

    @RequestMapping(value = "/vehicleGenerate", method = RequestMethod.GET)
    public DataInfo<String> vehicleGenerate() {
        vehicleGenerateHelper();
        return DataInfo.success("In process");
    }

    @RequestMapping(value = "/povertyGenerate", method = RequestMethod.GET)
    public DataInfo<String> povertyGenerate() {
        povertyGenerateHelper();
        return DataInfo.success("In process");
    }

    private void generateHelper() {
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

    private void estateGenerateHelper() {

        List<OfficerBasicInfoDO> items = officerBasicInfoService.findAll();
        for (OfficerBasicInfoDO item : items) {
            if (StringUtils.isEmpty(item.getIdCard())) {
                continue;
            }
            Integer amount = 0;
            amount += compareEstateInfoService.countByOwnerId(item.getIdCard());

            for (OfficerFamilyMemberInfoDO member :
                    officerFamilyMemberInfoService.search("cadre_id_card", item.getIdCard())) {
                if (StringUtils.isEmpty(member.getIdCard())||item.getIdCard().equals(member.getIdCard())) {
                    continue;
                }
                amount += compareEstateInfoService.countByOwnerId(member.getIdCard());
            }

            if (amount <= 0) {
                continue;
            }
            AlertInfoDO alertInfoDO = new AlertInfoDO();
            alertInfoDO.setAlertType(AlertInfoDO.AlertType.REGISTER);
            alertInfoDO.setArea(item.getArea());
            alertInfoDO.setIdCard(item.getIdCard());
            alertInfoDO.setName(item.getName());
            alertInfoDO.setOrganization(item.getOrganization());
            alertInfoDO.setPhoto(item.getPhoto());
            alertInfoDO.setTitle(item.getTitle());
            alertInfoDO.setContent(
                    String.format("房产数量：%d,产权登记号：%s", amount));
            alertInfoService.save(alertInfoDO);
        }
    }

    private void vehicleGenerateHelper() {

        List<OfficerBasicInfoDO> items = officerBasicInfoService.findAll();
        for (OfficerBasicInfoDO item : items) {

            if (StringUtils.isEmpty(item.getIdCard())) {
                continue;
            }
            Set<String> idCards = getCardIds(item);

            StringBuffer plates = new StringBuffer();
            Integer amount = 0;

            for (String idCard :idCards) {
                if (StringUtils.isEmpty(idCard)) {
                    continue;
                }
                amount += compareVehicleInfoService.countByIdCard(idCard);
                for (CompareVehicleInfoDO vehicleInfoDO :
                        compareVehicleInfoService.search("idCard", idCard)) {
                    plates.append("/车牌：" + vehicleInfoDO.getPlateNumber());
                    plates.append("/车主姓名：" + vehicleInfoDO.getOwner());
                    plates.append("/车主身份证：" + vehicleInfoDO.getIdCard());
                    plates.append(";");
                }
            }

            if (amount <= 0) {
                continue;
            }
            AlertInfoDO alertInfoDO = new AlertInfoDO();
            alertInfoDO.setAlertType(AlertInfoDO.AlertType.REGISTER);
            alertInfoDO.setArea(item.getArea());
            alertInfoDO.setIdCard(item.getIdCard());
            alertInfoDO.setName(item.getName());
            alertInfoDO.setOrganization(item.getOrganization());
            alertInfoDO.setTitle(item.getTitle());
            alertInfoDO.setPhoto(amount.toString());
            alertInfoDO.setContent(
                    String.format("机动车数量：%d,%s", amount, plates.toString()));
            alertInfoService.save(alertInfoDO);
        }
    }

    private void povertyGenerateHelper() {

        List<OfficerBasicInfoDO> items = officerBasicInfoService.findAll();
        for (OfficerBasicInfoDO item : items) {

            if (StringUtils.isEmpty(item.getIdCard())) {
                continue;
            }
            Set<String> idCards = getCardIds(item);

            for (String idCard :idCards) {
                for(ComparePovertyInfoDO comparePovertyInfoDO:
                        comparePovertyInfoService.search("idCard",idCard)){

                    AlertInfoDO alertInfoDO = new AlertInfoDO();
                    alertInfoDO.setAlertType(AlertInfoDO.AlertType.CORRUPTION);
                    alertInfoDO.setArea(item.getArea());
                    alertInfoDO.setIdCard(item.getIdCard());
                    alertInfoDO.setName(item.getName());
                    alertInfoDO.setOrganization(item.getOrganization());
                    alertInfoDO.setTitle(item.getTitle());
                    alertInfoDO.setPhoto(item.getPhoto());
                    alertInfoDO.setContent(
                            String.format("关联贫困户：%s",
                                    comparePovertyInfoDO.getName()+comparePovertyInfoDO.getIdCard()));
                    alertInfoService.save(alertInfoDO);
                }
                for(CompareSubsidyInfoDO compareSubsidyInfoDO:
                        compareSubsidyInfoService.search("idCard",idCard)){

                    AlertInfoDO alertInfoDO = new AlertInfoDO();
                    alertInfoDO.setAlertType(AlertInfoDO.AlertType.CORRUPTION);
                    alertInfoDO.setArea(item.getArea());
                    alertInfoDO.setIdCard(item.getIdCard());
                    alertInfoDO.setName(item.getName());
                    alertInfoDO.setOrganization(item.getOrganization());
                    alertInfoDO.setTitle(item.getTitle());
                    alertInfoDO.setPhoto(item.getPhoto());
                    alertInfoDO.setContent(
                            String.format("关联低保户：%s",
                                    compareSubsidyInfoDO.getName()+compareSubsidyInfoDO.getIdCard()));
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
}
