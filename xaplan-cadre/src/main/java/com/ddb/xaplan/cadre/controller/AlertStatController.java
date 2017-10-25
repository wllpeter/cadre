package com.ddb.xaplan.cadre.controller;

import com.ddb.xaplan.cadre.common.DataInfo;
import com.ddb.xaplan.cadre.service.AlertInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Api(value = "AlertStatController",description = "警告统计相关接口")
@Controller
@RequestMapping("/api/alertStat")
public class AlertStatController {
    @Resource(name = "alertInfoServiceImpl")
    private AlertInfoService alertInfoService;

    @ApiOperation(value = "根据三县id和警告类型查看警告类型")
    @GetMapping("get_alert_classify")
    @ResponseBody
    public DataInfo<HashMap<String,Object>> getAlertClassify(){
        HashMap<String,Object> alertsByContent = this.alertInfoService.getAlertCountByContent();
        HashMap<String,Object> alertsByArea = this.alertInfoService.getAlertCountByArea();
        if(null == alertsByArea && null == alertsByContent){
            return DataInfo.error("未获得对应数据");
        }
        HashMap<String,Object> ret = new HashMap<>();
        ret.put("alerts_by_content" , alertsByContent);
        ret.put("alerts_by_area" , alertsByArea);
        return  DataInfo.success(ret);
    }
}
