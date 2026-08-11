package com.ui.test;
import static org.testng.Assert.*;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.Listeners;
import com.pojo.User;
import com.utility.LoggerUtility;

@Listeners(com.ui.listeners.TestListener.class)
public class LoginTest extends TestBase {
    Logger logger = LoggerUtility.getLogger(this.getClass());

    @Test(description = "Test to verify login functionality", groups = {"login","e2e","smoke"},dataProvider="loginTestJsonDataProvider", dataProviderClass=com.ui.dataProviders.LoginDataProvider.class)
    public void testLogin(User user) {
    assertTrue(homepage.gotoLoginPage().doLoginWithValidEmailAndPassword(user.getEmail(), user.getPassword()).isMyAccountHeaderDisplayed(), "My Account header is not displayed");
    }

    @Test(description = "Test to verify login functionality with invalid credentials", groups = {"login","smoke"},dataProvider="loginTestCSVDataProvider",dataProviderClass=com.ui.dataProviders.LoginDataProvider.class)
    public void testLoginWithCSVDataCredentials(User user) {
        
    assertTrue(homepage.gotoLoginPage().doLoginWithValidEmailAndPassword(user.getEmail(),user.getPassword()).isMyAccountHeaderDisplayed(), "My Account header is not displayed");
        
    }

    @Test(description = "Test to verify login functionality with invalid credentials", groups = {"login","smoke"},dataProvider="loginTestExlDataProvider",dataProviderClass=com.ui.dataProviders.LoginDataProvider.class,retryAnalyzer=com.ui.listeners.MyRetryAnalyzer.class)
    public void testLoginWithInvalidCredentials(User user) {
        
    assertTrue(homepage.gotoLoginPage().doLoginWithInvalidEmailAndPassword(user.getEmail(),user.getPassword()).isLoginErrorMessageDisplayed(), "Login error message is not displayed");
        
    }

    @AfterMethod(description = "Close the browser")
    public void tearDown()
    {
        homepage.closeBrowser();
    }
}
