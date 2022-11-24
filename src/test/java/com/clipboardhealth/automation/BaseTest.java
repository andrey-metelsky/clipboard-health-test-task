package com.clipboardhealth.automation;

import com.clipboardhealth.automation.client.DriverFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static com.clipboardhealth.automation.environment.ConfigurationManager.configuration;

public class BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void beforeMethodInit() {
        DriverFactory.getInstance().getDriver().get(configuration().url());
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethodStop() {
        DriverFactory.getInstance().removeDriver();
    }
}