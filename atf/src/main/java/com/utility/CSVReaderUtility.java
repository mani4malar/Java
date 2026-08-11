package com.utility;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.opencsv.CSVReader;
import com.pojo.User;

public class CSVReaderUtility {

    public static Iterator<User> readCSVFile(String fileName) {

        System.out.println("CSV File Path: " + fileName);

        List<User> userList = new ArrayList<>();

        try (CSVReader csvReader = new CSVReader(new FileReader(fileName))) {

            String[] line;

            // Skip header
            csvReader.readNext();

            while ((line = csvReader.readNext()) != null) {

                User user = new User();      // Use default constructor
                user.setEmail(line[0]);
                user.setPassword(line[1]);

                userList.add(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return userList.iterator();
    }

    public static void main(String[] args) {

        String csvFilePath = System.getProperty("user.dir")
                + "/src/test/resources/testdata/loginData.csv";

        Iterator<User> userIterator = readCSVFile(csvFilePath);

        while (userIterator.hasNext()) {
            User user = userIterator.next();

            System.out.println("-------------------");
            System.out.println("Email    : " + user.getEmail());
            System.out.println("Password : " + user.getPassword());
        }
    }
}