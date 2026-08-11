package com.utility;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.pojo.User;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class ExcelReaderUtility {

    public static Iterator<User> readExcelFile(String filePath,String sheetName) {
        // Implement your Excel reading logic here
        List<User> userList = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(filePath)) {
            try (XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
                XSSFSheet sheet = workbook.getSheet(sheetName); // Assuming you want to read the first sheet

       for(Row row : sheet)
       {
        if (row.getRowNum() == 0) {
                    continue;
                }
                    User user = new User();  
                    String email = row.getCell(0).getStringCellValue();
                    String password = row.getCell(1).getStringCellValue();
                    user.setEmail(email);
                    user.setPassword(password);
                    userList.add(user);
                    System.out.println(user);
       }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList.iterator();
    }

    public static void main(String[] args) {
        String excelFilePath = System.getProperty("user.dir") + "\\atf\\src\\test\\resources\\testdata\\loginData.xlsx"; // Update with your Excel file path
        String sheetName = "testdata"; // Update with your sheet name
        System.out.println("Excel file path: " + excelFilePath);
        readExcelFile(excelFilePath, sheetName);
    }

}
