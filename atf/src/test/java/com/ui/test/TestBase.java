package com.ui.test;

import org.testng.annotations.BeforeMethod;
import static com.constants.BrowserType.CHROME;
import com.ui.pages.Homepage;
import com.utility.BrowserUtility;
import com.utility.LoggerUtility;
import org.apache.logging.log4j.Logger;

public class TestBase {

    protected Homepage homepage;
    Logger logger = LoggerUtility.getLogger(this.getClass());

    @BeforeMethod(description = "Load the Home page")
    public void setup() {
      
        homepage = new Homepage(CHROME, true);
        
    }

    public BrowserUtility getInstance() {
        return homepage;
    }
}
