package com.ddb.xaplan.cadre.controller;

import com.ddb.xaplan.cadre.common.DataInfo;
import com.ddb.xaplan.cadre.common.tool.ExcelUtils;
import com.ddb.xaplan.cadre.entity.OperationLogDO;
import com.ddb.xaplan.cadre.service.OperationLogService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by 王凯斌 on 2017/10/18.
 */
@RestController
@RequestMapping("/api/operationLog")
public class OperationLogController {

    @Value("${excel.output.path}")
    private String excelOutputPath;

    @Resource(name="operationLogServiceImpl")
    private OperationLogService operationLogService;

    @ApiOperation(value = "search operationLog controller")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyword",paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "startDate",paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "endDate",paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "size",paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "page",paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "sort",paramType = "query", dataType = "String")
    })
    @RequestMapping(method = RequestMethod.GET)
    public DataInfo<Page<OperationLogDO>> search(String keyword,
                                                 @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                                 @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
                                                 @PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {

        return DataInfo.success(operationLogService.search(keyword,startDate,endDate,pageable));
    }

    @ApiOperation(value = "search operationLog controller")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "attrs",required = true,paramType = "array", dataType = "String"),
            @ApiImplicitParam(name = "startDate",paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "endDate",paramType = "query", dataType = "String")
    })
    @RequestMapping(value = "/excel",method = RequestMethod.GET)
    public DataInfo<String> search(
            String[] attrs,
            @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {

        if(attrs==null||attrs.length==0){
            return DataInfo.error("至少要选择一项数据列");
        }
        String fileName = String.valueOf(new Date().getTime());
        List<String> titles =Arrays.asList(attrs);
        String path = "";
        try {
            path = ExcelUtils.createExcelWithEntity(
                    fileName, titles,operationLogService.search(startDate,endDate),attrs);
        }catch (Exception e){
            e.printStackTrace();
            return DataInfo.error("文件生成错误");
        }

        return DataInfo.success("/xaplan-cadre/excel/"+path);
    }
}
