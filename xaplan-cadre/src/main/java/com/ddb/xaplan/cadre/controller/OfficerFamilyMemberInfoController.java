package com.ddb.xaplan.cadre.controller;

import com.ddb.xaplan.cadre.common.DataInfo;
import com.ddb.xaplan.cadre.entity.OfficerBasicInfoDO;
import com.ddb.xaplan.cadre.entity.OfficerFamilyMemberInfoDO;
import com.ddb.xaplan.cadre.enums.FamilyRelationship;
import com.ddb.xaplan.cadre.service.OfficerBasicInfoService;
import com.ddb.xaplan.cadre.service.OfficerFamilyMemberInfoService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by 陈亚兰 on 2017/10/17.
 */
@RestController
@RequestMapping("/api/officer")
public class OfficerFamilyMemberInfoController {

    @Autowired
    private OfficerBasicInfoService officerBasicInfoService;
    @Autowired
    private OfficerFamilyMemberInfoService officerFamilyMemberInfoService;


    @ApiOperation(value = "search family info controller")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "officerId",paramType = "PathVariable", dataType = "String") })
    @RequestMapping(value = "/{officerId}/memberinfo",method = RequestMethod.GET)
    public DataInfo<List<OfficerFamilyMemberInfoDO>> search(@PathVariable Long officerId){

        List<OfficerFamilyMemberInfoDO> items=officerFamilyMemberInfoService.search(
                "officerBasicInfoDO", officerBasicInfoService.find(officerId));
        if(items.size()==0){
            return DataInfo.error("未找到关联数据");
        }
        return DataInfo.success(items);
    }

    @RequestMapping(value = "/generate",method = RequestMethod.GET)
    public DataInfo<String> generate(){

        List<OfficerBasicInfoDO> items = officerBasicInfoService.findAll();
        for (OfficerBasicInfoDO item : items) {
            List<OfficerFamilyMemberInfoDO> members=officerFamilyMemberInfoService.search(
                    "officerBasicInfoDO", item);
            if(members.size()==0){
                continue;
            }
            Boolean flag = true;
            Boolean isHost = true;
            for(OfficerFamilyMemberInfoDO member:members){
                if(item.getName().equals(member.getName())){
                    flag = false;
                }
                if(FamilyRelationship.HOUSEHOLDER.equals(member.getFamilyRelationship())){
                    isHost = false;
                }
            }
            if(flag){
                OfficerFamilyMemberInfoDO self = new OfficerFamilyMemberInfoDO();
                self.setName(item.getName());
                self.setBirthday(item.getBirthDate());
                self.setCadre_id_card(item.getIdCard());
                self.setOfficerBasicInfoDO(item);
                self.setCulture(item.getCulture());
                self.setIdCard(item.getIdCard());
                self.setFamilyRelationship(isHost?FamilyRelationship.HOUSEHOLDER:null);
                self.setEducationLevel(item.getEducationLevel());
                self.setTitleLevel(item.getTitleLevel());
                self.setTitle(item.getTitle());
                officerFamilyMemberInfoService.save(self);
            }
        }
        return DataInfo.success("");
    }
}
