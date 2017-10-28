package com.ddb.xaplan.cadre.common.tool;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.assertj.core.data.MapEntry;
import org.omg.CORBA.ShortHolder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by 王凯斌 on 2017/6/17.
 */
@Component
public class  ExcelUtils {

    private final static int page = 10000;

    @Value("${excel.output.path}")
    private String excelOutputPath;

    private static String excelPath;

    @PostConstruct
    public void init() {

        excelPath = excelOutputPath;
    }

    /**
     * 创建excel
     *
     * @param fileName
     * @param title
     * @param data
     * @throws Exception
     */
    public static String createExcel(String fileName, List<String> title, List<List<String>> data) throws Exception {

        Workbook wb = new HSSFWorkbook();

        int sheetNum = (data.size() % page == 0) ? (data.size() / page) : (data.size() / page) + 1;
        int eachSheetNum = 0;
        for (int sheetIndex = 0; sheetIndex < sheetNum; sheetIndex++) {
            Sheet sheet = wb.createSheet(fileName + sheetIndex);
            Row titleRow = sheet.createRow((short) 0);
            eachSheetNum = ((sheetIndex + 1) == sheetNum) ? ((data.size() % page == 0) ? page : (data.size() % page)) : page;
            fillRowWithCells(titleRow, title);
            for (int n = 0; n < eachSheetNum; n++) {
                Row dataRow = sheet.createRow(n + 1);
                fillRowWithCells(dataRow, data.get(n));
            }
        }
        String fileResult = createExcelByWorkBook(fileName,wb);
        return fileResult;
    }

    /**
     * 创建带cell的row
     *
     * @param row
     * @param cells
     * @return
     */
    private static Row fillRowWithCells(Row row, List<String> cells) {
        for (int n = 0; n < cells.size(); n++) {
            Cell cell = row.createCell(n);
            cell.setCellValue(cells.get(n));
        }
        return row;
    }

    /**
     * 用Map替代List表达每行的数据
     *
     * @param fileName
     * @param title
     * @param data
     * @throws Exception
     */
    public static String createExcelWithMap(String fileName, List<String> title, List<Map> data) throws Exception {

        List<List<String>> newData = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            newData.add(new ArrayList<>(data.get(i).values()));
        }
        return createExcel(fileName, title, newData);
    }

    public static <T>  String createExcelWithEntity(String fileName, List<String> title, List<T> data,String[] attrs) throws Exception {

        List<List<String>> newData = new ArrayList<>();
        for (T item : data) {
            List<String> row = createRow(item, attrs);
            newData.add(row);
        }
        return createExcel(fileName, title, newData);
    }

    private static <T> List<String> createRow(T item, String[] attrs) throws Exception {
        List<Field> fields = new ArrayList<>();
        fields.addAll(Arrays.asList(item.getClass().getSuperclass().getDeclaredFields()));
        fields.addAll(Arrays.asList(item.getClass().getDeclaredFields()));

        List<String> row = new ArrayList<>();
        for(String attr:attrs){
            for(Field field:fields){
                if (field.getName().equals(attr)){
                    field.setAccessible(true);
                    row.add(String.valueOf(field.get(item)));
                }
            }
        }
        return  row;
    }

    /**
     * author：ZAN YANG
     * 组装不同sheet的表格
     */
    public static <T> HSSFWorkbook setSheet2Workbook(HSSFWorkbook workbook, ExcelExportEntity<T> excelExportEntity )throws Exception{
        Sheet sheet = workbook.createSheet(excelExportEntity.getSheetName());
        Row titleRow = sheet.createRow(0);
        fillRowWithCells(titleRow, excelExportEntity.getTitles());
        List<List<String>> newData = new LinkedList<>();
        List<T> datas = excelExportEntity.getDatas();
        for(T data : datas) {
            List<String> rows = createRow(data,excelExportEntity.getArgs());
            newData.add(rows);
        }
        int endIndex = newData.size();
        for(int startIndex = 0; startIndex < endIndex; startIndex++){
            Row dataRow = sheet.createRow(startIndex+1);
            fillRowWithCells(dataRow, newData.get(startIndex));
            continue;
        }
        return workbook;
    }

//    public static <T> HSSFWorkbook setSheet2Workbook(HSSFWorkbook workbook, String sheetName, List<String> titles,List<Map> datas)throws Exception{
//        Sheet sheet = workbook.createSheet(sheetName);
//        Row titleRow = sheet.createRow(0);
//        fillRowWithCells(titleRow, titles);
//        List<List<String>> newData = new LinkedList<>();
//        List<T> datas = excelExportEntity.getDatas();
//        for(T data : datas) {
//            List<String> rows = createRow(data,excelExportEntity.getArgs());
//            newData.add(rows);
//        }
//        int endIndex = newData.size();
//        for(int startIndex = 0; startIndex < endIndex; startIndex++){
//            Row dataRow = sheet.createRow(startIndex+1);
//            fillRowWithCells(dataRow, newData.get(startIndex));
//            continue;
//        }
//        return workbook;
//    }

    public static String createExcelByWorkBook(String fileName,Workbook workbook) throws Exception{
        File parentFile = new File(excelPath);
        File excelFile = new File(parentFile, fileName + ".xls");
        FileOutputStream fileOut = new FileOutputStream(excelFile);
        workbook.write(fileOut);
        fileOut.close();
        return fileName+".xls";
    }

    public static byte[] createExcelByWorkBookByBytes(String fileName, Workbook workbook) throws Exception{
        //response.reset();
        ByteOutputStream os = new ByteOutputStream();
        workbook.write(os);
        byte[] bytes = os.getBytes();
//        response.reset();
//        response.setContentType("application/vnd.ms-excel;charset=utf-8");
//        response.setHeader("Content-Disposition",
//                "attachment;filename=" + new String((fileName + ".xls").getBytes("GBK"), "iso-8859-1"));
//        response.getOutputStream().write(bytes);
//        response.getOutputStream().flush();
//        response.getOutputStream().close();
        return bytes;
    }
}
