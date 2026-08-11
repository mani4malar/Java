package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static com.utility.PropertyUtil.getProperty;
import com.constants.BrowserType;
import com.constants.Env;
import com.utility.BrowserUtility;
import com.utility.JsonUtility;

public class Homepage extends BrowserUtility {
   
    private static final String SIGN_IN_BUTTON = "//a[contains(text(),'Login')]";

    public Homepage(WebDriver driver) {
        super(driver);
        goToWebSite(getProperty(Env.QA, "URL"));
    }

    public Homepage(BrowserType browserName) {
        super(browserName, false);
        goToWebSite(JsonUtility.readJson(Env.QA).getUrl().trim());
    }
    public Homepage(BrowserType browserName, boolean isHeadless) {
        super(browserName, isHeadless);
        goToWebSite(JsonUtility.readJson(Env.QA).getUrl().trim());
    }

    public LoginPage gotoLoginPage() {
        clickOnByElement(By.xpath(SIGN_IN_BUTTON));
        return new LoginPage(getDriver());
    }

   

}
