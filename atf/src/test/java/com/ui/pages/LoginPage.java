package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.utility.BrowserUtility;

public class LoginPage extends BrowserUtility {
    
    private static final String EMAIL_FIELD = "//input[@name='email']";
    private static final String PASSWORD_FIELD = "//input[@name='password']";
    private static final String SUBMIT_BUTTON = "//button[contains(text(),'Login')]";
    private static final String LOGIN_ERROR_MESSAGE = "//p[contains(text(),'Your email or password is incorrect!')]";
    

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterEmailAndPassWord(String email, String password) {
        sendKeysByElement(By.xpath(EMAIL_FIELD), email);
        sendKeysByElement(By.xpath(PASSWORD_FIELD), password);
    }

      public boolean isLoginErrorMessageDisplayed() {
        return isElementDisplayed(By.xpath(LOGIN_ERROR_MESSAGE));
    }

    public MyAccountPage doLoginWithValidEmailAndPassword(String email, String password) {
        enterEmailAndPassWord( email,  password);
        clickOnByElement(By.xpath(SUBMIT_BUTTON));
        return new MyAccountPage(getDriver());
    }

    public LoginPage doLoginWithInvalidEmailAndPassword(String email, String password) {
        enterEmailAndPassWord( email,  password);
        clickOnByElement(By.xpath(SUBMIT_BUTTON));
        return new LoginPage(getDriver());
    }

}

