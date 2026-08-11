package com.ui.dataProviders;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.testng.annotations.DataProvider;
import com.google.gson.Gson;
import com.pojo.UserList;
import com.utility.CSVReaderUtility;
import com.utility.ExcelReaderUtility;
import com.pojo.User;

public class LoginDataProvider {
    @DataProvider(name = "loginTestJsonDataProvider")
    public Iterator<Object[]> loginTestJsonDataProvider() {
        // Implement your data provider logic here
        Gson gson = new Gson();
        File  jsonFile = new File(System.getProperty("user.dir")+File.separator+"src\\test\\resources\\testdata\\loginData.json");
        System.out.println("JSON File Path: " + jsonFile.getAbsolutePath());

        try(FileReader reader = new FileReader(jsonFile))
        {
            UserList users=gson.fromJson(reader,UserList.class);
            List<Object[]> userList = new ArrayList<Object[]  >();
            
            for(User user:users.getUsers())
            {
                if("yes".equals(user.getRun())) {
                    userList.add(new Object[]{user});
                    
                } 
               
            }
            return userList.iterator();
        }
        catch (java.io.IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to read loginData.json", e);
        }
    }

     @DataProvider(name = "loginTestCSVDataProvider")
    public Iterator<User> loginTestCSVDataProvider() {
        String csvFilePath = System.getProperty("user.dir")
                + "/src/test/resources/testdata/loginData.csv";
        return CSVReaderUtility.readCSVFile(csvFilePath);
    } 

     @DataProvider(name = "loginTestExlDataProvider", parallel = false)
    public Iterator<User> loginTestExlDataProvider() {
        String csvFilePath = System.getProperty("user.dir")
                + "/src/test/resources/testdata/loginData.xlsx";
        return ExcelReaderUtility.readExcelFile(csvFilePath, "testdata");
    } 
}
