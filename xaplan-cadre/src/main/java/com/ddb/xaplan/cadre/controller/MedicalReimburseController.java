package com.ddb.xaplan.cadre.controller;

import com.ddb.xaplan.cadre.common.DataInfo;
import com.ddb.xaplan.cadre.common.tool.ExcelExportEntity;
import com.ddb.xaplan.cadre.common.tool.ExcelUtils;
import com.ddb.xaplan.cadre.entity.AreaDO;
import com.ddb.xaplan.cadre.service.AreaService;
import com.ddb.xaplan.cadre.service.MedicalReimburseService;
import com.ddb.xaplan.cadre.service.OperationLogService;
import com.ddb.xaplan.cadre.vo.DiseaseRankItem;
import com.ddb.xaplan.cadre.vo.ReimburseDetailVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by 王凯斌 on 2017/10/24.
 * Update ReimburseList By Zan Yang on 2017/10/25
 */
@RestController
@RequestMapping("/api/medicalReimburse")
@Api(value = "MedicalReimburseController",description = "医疗报销相关接口")
public class MedicalReimburseController {
    private final int REIMBURSE_RANK_LIMIT_RANGE = 20;//报销排名范围
    @Resource(name = "areaServiceImpl")
    private AreaService areaService;

    @Resource(name = "medicalReimburseServiceImpl")
    private MedicalReimburseService medicalReimburseService;

    @Resource(name="operationLogServiceImpl")
    private OperationLogService operationLogService;

    @ApiOperation(value = "search medical general charts")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "year",required = true,paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "areaId",required = true,paramType = "query", dataType = "Long")
    })
    @RequestMapping(value = "/statistics",method = RequestMethod.GET)
    public DataInfo<Map> statistics(Long areaId, String year) throws Exception{

        operationLogService.logger(
                null,String.format("专题分析，地区：%s",areaService.find(areaId).getName()));
        Map<String,Object> result = new HashMap<>();
        result.put("hospitalCount",
                medicalReimburseService.monthStatistics(year,1,areaId));
        result.put("nonHospitalCount",
                medicalReimburseService.monthStatistics(year,0,areaId));
        result.put("monthAmountStatistics",
                medicalReimburseService.monthAmountStatistics(year,areaId));

        return DataInfo.success(result);
    }

    @ApiOperation(value = "search medical rank")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "year",required = true,paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "areaId",required = true,paramType = "query", dataType = "Long")
    })
    @RequestMapping(value = "/rank",method = RequestMethod.GET)
    public DataInfo<List> rank(Long areaId, String year) throws Exception{

        return DataInfo.success(medicalReimburseService.diseaseRank(year,areaId));
    }

    @ApiOperation(value = "每日住院人数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "year",required = true,paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "month",required = true,paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "areaId",required = true,paramType = "query", dataType = "Long")
    })
    @RequestMapping(value = "/hosCount",method = RequestMethod.GET)
    public DataInfo<int[]> hosCount(Long areaId, String year,String month) throws Exception{

        return DataInfo.success(medicalReimburseService.hosCount(year,areaId,month));
    }

    @ApiOperation(value = "平均每日住院人数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "year",required = true,paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "areaId",required = true,paramType = "query", dataType = "Long")
    })
    @RequestMapping(value = "/average",method = RequestMethod.GET)
    public DataInfo<Map> average(Long areaId, String year) throws Exception{

        return DataInfo.success(medicalReimburseService.average(year,areaId));
    }

    @ApiOperation(value = "住院时间以及病因分析")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "year",required = true,paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "areaId",required = true,paramType = "query", dataType = "Long")
    })
    @RequestMapping(value = "/hosRank",method = RequestMethod.GET)
    public DataInfo<Map> hosRank(Long areaId, String year) throws Exception{

        Map<String,List<DiseaseRankItem>> result = new HashMap<>();
        result.put("0~7",medicalReimburseService.hosRank(year,areaId,8,1));
        result.put("8~15",medicalReimburseService.hosRank(year,areaId,16,8));
        result.put("16~30",medicalReimburseService.hosRank(year,areaId,31,16));
        result.put("30~",medicalReimburseService.hosRank(year,areaId,10000,31));

        return DataInfo.success(result);
    }

    @ApiOperation(value = "城区居民医疗保险报销次数排名接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "areaId",required = true, paramType = "query",dataType = "Long",value = "地区id"),
            @ApiImplicitParam(name = "year", required = true, paramType = "query", dataType = "Integer",value = "年度"),
            @ApiImplicitParam(name = "season", paramType = "query", dataType = "Integer",value = "季度")
    })
    @GetMapping(value = "/get_reimburse_details")
    @ResponseBody
    public DataInfo<List<ReimburseDetailVO>> getRemiburseRank(Long areaId,Integer year, Integer season){
        List<ReimburseDetailVO> result = this.medicalReimburseService.getReimburseRank(areaId, year, season, REIMBURSE_RANK_LIMIT_RANGE);
        if(null == result || result.size() == 0){
            return DataInfo.error("未取得排名数据");
        }
        return DataInfo.success(result);
    }

    @ApiOperation(value="病因分析top20界面Excel导出")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "areaId",required = true, paramType = "query",dataType = "Long",value = "地区id"),
            @ApiImplicitParam(name = "year", required = true, paramType = "query", dataType = "Integer",value = "年度"),
            @ApiImplicitParam(name = "month", paramType = "query", dataType = "Integer",value = "月份")
    })
    @GetMapping("/get_disease_rank_excel")
    public String exportDiseaseTop20Page(String year,String month, Long areaId)throws Exception{
        AreaDO areaDO = this.areaService.find(areaId);
        HSSFWorkbook workbook = new HSSFWorkbook();
        /**
         * 统计图sheet
         */
        HSSFSheet sheet = workbook.createSheet("统计图");
        Row titleRow = sheet.createRow(0);
        String[] titles = new String[]{"一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"};
        for(int i = 0 ;i < 12; i++){
            Cell cell = titleRow.createCell(i+1);
            cell.setCellValue(titles[i]);
            continue;
        }

        double[] monthAmountStatistics = medicalReimburseService.monthAmountStatistics(year,areaId);
        int[] hospitalTime = medicalReimburseService.monthStatistics(year,1,areaId);
        int[] nonHospitalTime = medicalReimburseService.monthStatistics(year,0,areaId);
        List<String> totalTime = new ArrayList<>();
        String[] colNames = new String[]{"住院次数","门诊次数","报销金额","报销人次"};
        for(int i = 0; i < 4; i++){
            Row row = sheet.createRow(i + 1);
            Cell colNameCell = row.createCell(0);
            colNameCell.setCellValue(colNames[i]);
            for(int n = 0; n < 12; n++){
                Cell cell = row.createCell(n+1);
                if(i == 0) {
                    cell.setCellValue(hospitalTime[n]);
                }
                else if(i == 1){
                    cell.setCellValue(nonHospitalTime[n]);
                }
                else if(i == 2){
                    cell.setCellValue(monthAmountStatistics[n]);
                }
                else if(i == 3){
                    cell.setCellValue(hospitalTime[n] + nonHospitalTime[n]);
                }
                continue;
            }
        }
        /**
         * 日均人次
         */
        HSSFSheet monthTimeSheet = workbook.createSheet("日均人次");
        Row monthTimeTitle = monthTimeSheet.createRow(0);
        for(int i = 0; i < 12;i++){
            Cell cell = monthTimeTitle.createCell(i);
            cell.setCellValue(titles[i]);
            continue;
        }
        Map<String,double[]> monthAverage = this.medicalReimburseService.average(year, areaId);
        List<Double> monthAList = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            double[] monthData = monthAverage.get(i+1+"");
            for(double temp : monthData){
                monthAList.add(temp);
            }
            continue;
        }
        Row averageRow=monthTimeSheet.createRow(1);
        for(int i = 0 ;i < 12;i++){
            Cell cell = averageRow.createCell(i);
            cell.setCellValue(monthAList.get(i));
            continue;
        }
        /**
         *日住院人次
         */
        HSSFSheet dayTimeSheet = workbook.createSheet("日住院人次");
        Row dayTimeTitle = dayTimeSheet.createRow(0);
        Row dayTimeData = dayTimeSheet.createRow(1);
        int[] monthHos = this.medicalReimburseService.hosCount(year,areaId,month);

//        for(int i = 0; i < 4; i++){
//            double[] monthData = monthAverage.get(i+1+"");
//            for(double temp : monthData){
//                monthAList.add(temp);
//            }
//            continue;
//        }
        for(int i = 0; i < monthHos.length;i++){
            Cell cell = dayTimeTitle.createCell(i);
            cell.setCellValue(month+"月"+i+1+"日");
            Cell cellData = dayTimeData.createCell(i);
            cellData.setCellValue(monthHos[i]);
            continue;
        }
        /**
         * 住院时间综合分析
         */
        List<DiseaseRankItem> hosRank;
        hosRank = medicalReimburseService.hosRank(year,areaId,8,1);
        hosRank.addAll(medicalReimburseService.hosRank(year,areaId,16,8));
        hosRank.addAll(medicalReimburseService.hosRank(year,areaId,31,16));
        hosRank.addAll(medicalReimburseService.hosRank(year,areaId,10000,31));
        List<String> hosRanktitle = new ArrayList<String>();
        hosRanktitle.add("病因");
        hosRanktitle.add("人次");
        ExcelExportEntity<DiseaseRankItem> diseaseRankItemTempList = new ExcelExportEntity<>("住院病因及人次",hosRanktitle,hosRank,new String[]{"name","hosCount"});
        workbook = ExcelUtils.setSheet2Workbook(workbook,diseaseRankItemTempList);
        /**
         * 病因分析top20
         */
        List<DiseaseRankItem> rank = this.medicalReimburseService.diseaseRank(year, areaId);
        String[] rankTitles = new String[]{"排名","病名","门诊","门诊补偿金额","住院","住院补偿金额"};
        String[] rankAttrs = new String[]{"order","name","nonHosCount","nonHosAmount","hosCount","hosAmount"};
        ExcelExportEntity<DiseaseRankItem> diseaseRankList = new ExcelExportEntity<>("病因排名",Arrays.asList(rankTitles),rank,rankAttrs);
        workbook = ExcelUtils.setSheet2Workbook(workbook,diseaseRankList);
        String ret = ExcelUtils.createExcelByWorkBook(areaDO.getName()+year+"年统计数据",workbook);
        return ret;
    }
    @ApiOperation(value = "报销排名导出")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "areaId",required = true, paramType = "query",dataType = "Long",value = "地区id"),
            @ApiImplicitParam(name = "year", required = true, paramType = "query", dataType = "Integer",value = "年度"),
            @ApiImplicitParam(name = "season", paramType = "query", dataType = "Integer",value = "季度")
    })
    @GetMapping(value = "/get_reimburse_rank_excel")
    @ResponseBody
    public String exportReimbursementRank(Long areaId,Integer year, Integer season){
        AreaDO area = this.areaService.find(areaId);
        List<String> titles = Arrays.asList("排名","姓名","身份证号","报销次数","报销金额","住院次数","住院天数","门诊次数","病因");
        List<ReimburseDetailVO> result = this.medicalReimburseService.getReimburseRank(areaId, year, season, REIMBURSE_RANK_LIMIT_RANGE);
        String[] attrs = new String[]{"rank","name","idCard","reimburseTime","reimburseAmount","hospitalizedTime","hospitalizedDuration","clinicTime","diseaseName"};
        int index = 1;
        for(ReimburseDetailVO temp : result){
            temp.setRank(index);
            index++;
        }
        String ret = null;
        try {
            ret = ExcelUtils.createExcelWithEntity(area.getName()+year+"年第"+season+"季度报销排名",titles,result,attrs);
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
            return "生成失败";
        }
    }
}
