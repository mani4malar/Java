package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.utility.BrowserUtility;

public class MyAccountPage extends BrowserUtility {
    private static final String MY_ACCOUNT_HEADER = "//b[contains(text(),'Manivannan Ezhumalai')]";

    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    public boolean isMyAccountHeaderDisplayed() {
       return isElementDisplayed(By.xpath(MY_ACCOUNT_HEADER));
    }


}
