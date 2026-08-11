package com.utility;
import java.io.File;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import com.constants.BrowserType;
import org.openqa.selenium.TakesScreenshot;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
public abstract class BrowserUtility {
Logger logger=LoggerUtility.getLogger(this.getClass());
private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

 public WebDriver getDriver() {
        return driver.get();
    }

protected BrowserUtility(WebDriver driver) {
    this.driver.set(driver);
}

    protected BrowserUtility(BrowserType BrowserName,boolean isHeadless) {
    logger.info("Selected Browser is {}{}", BrowserName, isHeadless ? " in headless mode" : "in normal mode");
   if(BrowserName==BrowserType.CHROME) {
        ChromeOptions options = new ChromeOptions();
        if (isHeadless) {
            options.addArguments("--headless=old");
            options.addArguments("--window-size=1920,1080");
        }
        driver.set(new ChromeDriver(options));
    }  else if (BrowserName==BrowserType.EDGE) {
            EdgeOptions options = new EdgeOptions();
        if (isHeadless) {
            options.addArguments("--headless=old");
            options.addArguments("disable-gpu");
        }
        driver.set(new EdgeDriver(options));
    } else {
        logger.error("Unsupported browser: {}", BrowserName);
        throw new IllegalArgumentException("Unsupported browser: " + BrowserName);
    }

}
public void goToWebSite(String url) {
    logger.info("Navigating to the website: {}", url);
    if (url == null) {
        throw new RuntimeException("URL is null");
    }

    getDriver().get(url);
    maximizeWindow();
    setImplicitWait(10);
}
public void maximizeWindow() {
    logger.info("Maximizing the browser window");
    getDriver().manage().window().maximize();
}

public void setImplicitWait(int seconds) {
    getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
}

public void clickOnByElement(By by) {
    logger.info("Clicking on element: {}", by);
    getDriver().findElement(by).click();
}   

public void sendKeysByElement(By by, String text) {
    logger.info("Sending keys to element: {}, Value: {}", by, text);
    getDriver().findElement(by).sendKeys(text);
}

public boolean isElementDisplayed(By by) {
    logger.info("Checking if element is displayed: {}", by);
    return getDriver().findElement(by).isDisplayed();
}

public void closeBrowser() {
    logger.info("Closing the browser");
    if (getDriver() != null) {
        getDriver().quit();
    }
}

public String takeScreenshot(String name) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
    String screenshotPath = System.getProperty("user.dir") + "/screenshots/" + name + "_" + formatter.format(LocalDateTime.now()) + ".png";
    TakesScreenshot screenshot =(TakesScreenshot) getDriver();
    File screenshotFile = screenshot.getScreenshotAs(OutputType.FILE);
    try {
        FileUtils.copyFile(screenshotFile, new File(screenshotPath));
        logger.info("Screenshot saved to: {}", screenshotPath);
    } catch (IOException e) {
        logger.error("Failed to save screenshot: {}", e.getMessage());   
    }
    return screenshotPath;
}
}
