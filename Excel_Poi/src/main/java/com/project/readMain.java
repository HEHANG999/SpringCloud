package com.project;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.IOException;

public class readMain {

    public static void main(String[] args) throws IOException {
        //1、获取工作薄
        XSSFWorkbook workbook = new XSSFWorkbook("C:\\Users\\31392\\Desktop\\user.xlsx");
        //2、获取工作表
        XSSFSheet sheet = workbook.getSheetAt(0);//也可以根据名称获取
        //获得工作表总行数
        int i = sheet.getPhysicalNumberOfRows();

        //获取第2行
        XSSFRow row2 = sheet.getRow(1);
        //获取第2行第2列
        XSSFCell cell1 = row2.getCell(1);
        System.out.println("第2行第2列："+cell1);

        //3、获取第一行之后所有
        for (int index = 1; index < i; index++) {
            //设置类型,防止读不出数字

            //获取行
            XSSFRow row = sheet.getRow(index);
            //获取列
            //获取单元格内容
            int lineName0 = (int) row.getCell(0).getNumericCellValue();//转化为int类型
            System.out.print(lineName0);
            String lineName1 = row.getCell(1).getStringCellValue();
            System.out.print(lineName1);
            int lineName2 = (int) row.getCell(2).getNumericCellValue();
            System.out.print(lineName2);
            String lineName3 = row.getCell(3).getStringCellValue();
            System.out.println(lineName3);

    }
        /*for (Row rows : sheet) {
            //4、获取单元格
            for (Cell cell : rows) {
                //设置类型,防止读不出数字
                cell.setCellType(CellType.STRING);
                //获取单元格中的内容
                String value = cell.getStringCellValue();
                System.out.println(value);

            }
        }*/


        //释放资源
        workbook.close();
    }




}
