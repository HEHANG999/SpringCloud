package com.project;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;

public class writeMain {

    public static void main(String[] args) throws Exception {
        //1、创建工作薄
        XSSFWorkbook workbook = new XSSFWorkbook();
        //2、创建工作表
        XSSFSheet sheet = workbook.createSheet("可可");
        //3、创建行
        XSSFRow row = sheet.createRow(0);
            //创建单元表格
            row.createCell(0).setCellValue(4);
            row.createCell(1).setCellValue("哈哈");
            row.createCell(2).setCellValue(777);
            row.createCell(3).setCellValue("男");

        XSSFRow row2 = sheet.createRow(1);
            //创建单元表格
            row2.createCell(0).setCellValue(42);
            row2.createCell(1).setCellValue("哈哈2");
            row2.createCell(2).setCellValue(7772);
            row2.createCell(3).setCellValue("男2");

        //输出流
        FileOutputStream out = new FileOutputStream("C:\\Users\\31392\\Desktop\\user.xlsx");
        workbook.write(out);
        out.flush();

        //释放资源
        out.close();
        workbook.close();
        System.out.println("写入成功");
    }

}
