package com.ui.listeners;
import org.testng.ITestResult;

import com.constants.Env;
import com.utility.JsonUtility;
import com.utility.PropertyUtil;

import org.testng.IRetryAnalyzer;

public class MyRetryAnalyzer implements IRetryAnalyzer {

    private int retryCount = 0;
    //private static final int maxRetryCount = Integer.parseInt(PropertyUtil.getProperty("MAX_RETRY_COUNT")); // Default to 3 retries if not set
    private static  final int maxRetryCount = JsonUtility.readJson(Env.QA).getMaxRetryCount(); // Default to 3 retries if not set
    
    @Override
    public boolean retry(ITestResult result) {
        System.out.println("Retry invoked for: " + result.getName());
        System.out.println("Current Retry Count: " + retryCount);

        if (retryCount < maxRetryCount) {
            retryCount++;
            System.out.println("Retrying... Attempt: " + retryCount);
            return true;
        }
        System.out.println("Maximum retries reached.");
        return false;
    }
}
