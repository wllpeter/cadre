package com.ddb.xaplan.cadre.controller;

import com.ddb.xaplan.cadre.common.DataInfo;
import com.ddb.xaplan.cadre.entity.FeedbackDO;
import com.ddb.xaplan.cadre.service.FeedbackService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.Date;
import java.util.UUID;

/**
 * Created by 王凯斌 on 2017/10/18.
 */
@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    @Resource(name="feedbackServiceImpl")
    private FeedbackService feedbackService;

    @Value("${upload.output.path}")
    private String uploadOutputPath;


    @ApiOperation(value = "search feedback controller")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyword",paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "startDate",paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "endDate",paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "size",paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "page",paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "sort",paramType = "query", dataType = "String")
    })
    @RequestMapping(method = RequestMethod.GET)
    public DataInfo<Page<FeedbackDO>> search(String keyword,
                                             @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                             @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
                                             @PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {

        return DataInfo.success(feedbackService.search(keyword,startDate,endDate,pageable));
    }

    @ApiOperation(value = "新增反馈接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "content",required = true,paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "images",paramType = "query", dataType = "String")
    })
    @RequestMapping(method = RequestMethod.POST)
    public DataInfo<FeedbackDO> add(String content,String images){
        FeedbackDO feedbackDO =new FeedbackDO();
        feedbackDO.setContent(content);
        return DataInfo.success(feedbackService.save(feedbackDO));
    }

    @ApiOperation(value = "上传接口")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public DataInfo<String> write(@RequestParam("file") MultipartFile file) {

        String fileName = UUID.randomUUID() + "-" + file.getOriginalFilename();

        try {
            file.transferTo(new File(uploadOutputPath + fileName));

        } catch (Exception e) {
            e.printStackTrace();
            return DataInfo.error(e.getMessage());
        }
        return DataInfo.success("/upload/" + fileName);
    }
}
