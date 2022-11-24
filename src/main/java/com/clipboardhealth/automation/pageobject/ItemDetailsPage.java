package com.clipboardhealth.automation.pageobject;

import com.clipboardhealth.automation.client.DriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

import static org.testng.Assert.assertEquals;

public class ItemDetailsPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(DriverFactory.class);

    @FindBy(css = "h1.a-size-base-plus")
    private WebElement itemDescriptionTitle;

    @FindBy(css = "#feature-bullets")
    private WebElement itemDescriptionText;

    public ItemDetailsPage verifyItemDetailsSectionTitle(String title) {
        assertEquals(itemDescriptionTitle.getText(), title);
        return this;
    }

    public ItemDetailsPage publishItemDetailsText() {
        String itemDetailsText = itemDescriptionText.getText();
        Reporter.log(itemDetailsText);
        logger.info(itemDetailsText);
        return this;
    }
}
