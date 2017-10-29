package com.ddb.xaplan.cadre.controller;


import com.ddb.xaplan.cadre.common.DataInfo;
import com.ddb.xaplan.cadre.entity.*;
import com.ddb.xaplan.cadre.service.*;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 付鸣 on 2017/10/18.
 * 财产全部信息
 */
@RestController
@RequestMapping("/api/PropertyInformation")
public class PropertyInformationController {

    @Resource(name = "alertInfoServiceImpl")
    private AlertInfoService alertInfoService;

    //干部信息
    @Resource(name="officerBasicInfoServiceImpl")
    private OfficerBasicInfoService officerBasicInfoService;

    //车辆信息
    @Resource(name="officerVehicleInfoServiceImpl")
    private OfficerVehicleInfoService officerVehicleInfoService;

    //房产信息
    @Resource(name="officerEstateInfoServiceImpl")
    private OfficerEstateInfoService officerEstateInfoService;

    //收入信息
    @Resource(name="officerIncomeInfoServiceImpl")
    private OfficerIncomeInfoService officerIncomeInfoService;

    //保险信息
    @Autowired
    private OfficerInsuranceInfoService officerInsuranceInfoService;


    //国外存款信息
    @Autowired
    private OfficerAbroadDepositInfoService officerAbroadDepositInfoService;

    //国外投资信息
    @Resource(name="officerAbroadInvestmentInfoServiceImpl")
    private OfficerAbroadInvestmentInfoService officerAbroadInvestmentInfoService;

    //股票信息
    @Resource(name="officerStockInfoServiceImpl")
    private OfficerStockInfoService officerStockInfoService;

    //基金信息
    @Resource(name="officerFundInfoServiceImpl")
    private OfficerFundInfoService officerFundInfoService;


    @ApiOperation(value = "search propertyInformation controller")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "officerId",paramType = "PathVariable", dataType = "String")})
    @GetMapping("/{officerId}/searchInfo")
    public DataInfo<Map<String,List>> search(@PathVariable Long officerId){

        OfficerBasicInfoDO officerBasicInfoDO = officerBasicInfoService.find(officerId);
        Map<String,List> map=new HashMap<String,List>();
        //车辆信息
        List<OfficerVehicleInfoDO> vehicleItems = officerVehicleInfoService.search(
                "officerBasicInfo",officerBasicInfoDO);
        map.put("vehicleItems",vehicleItems);
        //房产信息
        List<OfficerEstateInfoDO> estateItems=this.officerEstateInfoService.search(
                "officerBasicInfo",officerBasicInfoDO);
        map.put("estateItems",estateItems);
        //收入信息
        List<OfficerIncomeInfoDO> IncomeItems=this.officerIncomeInfoService.search(
                "officerBasicInfo",officerBasicInfoDO);
        map.put("incomeItems",IncomeItems);
        //保险信息
        List<OfficerInsuranceInfoDO> insuranceItems=officerInsuranceInfoService.search("officerBasicInfoDO",
                officerBasicInfoService.find(officerId));
        map.put("insuranceItems",insuranceItems);
        //国外存款信息
        List<OfficerAbroadDepositInfoDO> abroadDepositItems=officerAbroadDepositInfoService.search("officerBasicInfoDO",
                officerBasicInfoService.find(officerId));
        map.put("abroadDepositItems",abroadDepositItems);
        //国外投资信息
        List<OfficerAbroadInvestmentInfoDO> abroadInvestmentItems=this.officerAbroadInvestmentInfoService.search(
                "officerBasicInfo",officerBasicInfoDO);
        map.put("abroadInvestmentItems",abroadInvestmentItems);
        //股票信息
        List<OfficerStockInfoDO> stockItems=this.officerStockInfoService.search(
                "officerBasicInfo",officerBasicInfoDO
        );
        map.put("stockItems",stockItems);
        //基金信息
        List<OfficerFundInfoDO> fundItems=this.officerFundInfoService.search(
                "officerBasicInfo",officerBasicInfoDO
        );
        map.put("fundItems",fundItems);

        List<AlertInfoDO> vehicleAlerts = new ArrayList<>();
        List<AlertInfoDO> estateAlerts = new ArrayList<>();

        for(AlertInfoDO alertInfoDO:
                alertInfoService.search("officerBasicInfo",officerBasicInfoDO)){
            if("房产数量预警".equals(alertInfoDO.getContent())){
                estateAlerts.add(alertInfoDO);
                continue;
            }
            if("车辆数量预警".equals(alertInfoDO.getContent())){
                vehicleAlerts.add(alertInfoDO);
            }
        }
        map.put("vehicleAlerts",vehicleAlerts);
        map.put("estateAlerts",estateAlerts);

        return DataInfo.success(map);
    }
}








