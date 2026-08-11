package com.utility;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.ExtentReports;
public class ExtentReportUtility {
    private static ExtentReports extentReport;
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    public static void setupSparkReporter() {
    
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/ExtentReport.html");
        extentSparkReporter.config().setDocumentTitle("Automation Test - Practice Report");
        extentSparkReporter.config().setReportName("Login Test - Report");
        extentReport = new ExtentReports();
        extentReport.attachReporter(extentSparkReporter);
    }

    public static void createExtentTest(String testName)
    {       
        ExtentTest test = extentReport.createTest(testName);
        extentTest.set(test);
    }

    public static ExtentTest getExtentTest() {
        return extentTest.get();
    }

    public static void flushReport() {
        if (extentReport != null) {
            extentReport.flush();
        }
    }
        
}

