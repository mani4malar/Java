package com.ui.listeners;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.Status;
import com.utility.BrowserUtility;
import com.utility.ExtentReportUtility;
import com.utility.LoggerUtility;
public class TestListener implements ITestListener {
    Logger logger=LoggerUtility.getLogger(this.getClass());
    @Override
    public void onTestStart(ITestResult result) {
        // Code to execute when a test starts
        logger.info("Starting test: {}", result.getMethod().getMethodName());
        logger.info("Test description: {}", result.getMethod().getDescription());
        logger.info("Test groups: {}", String.join(", ", result.getMethod().getGroups()));
        ExtentReportUtility.createExtentTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // Code to execute when a test passes
        logger.info("Test passed: {}", result.getMethod().getMethodName());
        ExtentReportUtility.getExtentTest().log(Status.PASS, result.getMethod().getMethodName()+" is passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // Code to execute when a test fails
        logger.error("Test failed:  {}", result.getMethod().getMethodName());
        logger.error("Failure message: {}", result.getThrowable().getMessage());
        ExtentReportUtility.getExtentTest().log(Status.FAIL, result.getMethod().getMethodName()+" is failed");
        Object testInstance = result.getInstance();
        BrowserUtility browserUtility = ((com.ui.test.TestBase) testInstance).getInstance();
        String screenshotPath = browserUtility.takeScreenshot(result.getMethod().getMethodName());
        ExtentReportUtility.getExtentTest().addScreenCaptureFromPath(screenshotPath);
       
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // Code to execute when a test is skipped
        logger.warn("Test skipped: {}", result.getMethod().getMethodName());
        ExtentReportUtility.getExtentTest().log(Status.SKIP, result.getMethod().getMethodName()+" is skipped");
    }

    @Override
    public void onStart(ITestContext context) {
        // Code to execute before any test starts
        logger.info("Starting test suite: {}", context.getSuite().getName());
        ExtentReportUtility.setupSparkReporter();
    }
    @Override
    public void onFinish(ITestContext context) { 
        logger.info("Finished test suite: {}", context.getSuite().getName());
        ExtentReportUtility.flushReport();
    }

}
