package com.clipboardhealth.automation.pageobject;

import com.clipboardhealth.automation.client.DriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;

public class BasePage {

    private RemoteWebDriver webDriver = DriverFactory.getInstance().getDriver();
    private WebDriverWait webDriverWait = new WebDriverWait(this.webDriver, Duration.ofSeconds(DriverFactory.TIME_WAIT_SECONDS));

    private static final Logger logger = LogManager.getLogger(DriverFactory.class);

    public WebDriver getWebDriver() {
        return this.webDriver;
    }

    public BasePage() {
        PageFactory.initElements(webDriver, this);
    }

    public void sendKeys(final WebElement webElement, String text) {
        waitForClickable(webElement);
        webElement.clear();
        webElement.sendKeys(text);
    }

    public WebElement waitForVisibility(WebElement webElement) {
        try {
            webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
        } catch (NoSuchElementException nse) {
            logger.error("", nse);
            return null;
        }
        return webElement;
    }

    public void waitForInvisibility(By locator) {
        try {
            webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (NoSuchElementException e) {
            logger.error("", e);
        }
    }

    public WebElement waitForClickable(WebElement webElement) {
        waitForVisibility(webElement);
        try {
            webDriverWait.until(ExpectedConditions.elementToBeClickable(webElement));
        } catch (NoSuchElementException nse) {
            logger.error("Try to wait little more (wait for clickable)", nse);
        }
        return webElement;
    }

    public void waitForVisibilityBy(By locator) {
        try {
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (NoSuchElementException nse) {
            logger.error("", nse);
        }
    }

    public void pressEnter(final WebElement webElement) {
        waitForClickable(webElement);
        webElement.sendKeys(Keys.ENTER);
    }

    public void click(WebElement webElement) {
        safeClick(webElement);
    }

    private void safeClick(WebElement webElement) {
        waitForClickable(webElement);
        pureSafeClick(webElement);
    }

    private void pureSafeClick(WebElement webElement) {
        for (int i = 0; i < 10; i++) {
            try {
                webElement.click();
                break;
            } catch (Exception e) {
                logger.error("Click failed", e);
                pause();
            }
        }
    }

    public boolean isElementPresent(final WebElement we) {
        webDriver.getPageSource();
        try {
            return we.isDisplayed();
        } catch (Exception e) {
            logger.error("Element is not displayed", e);
            return false;
        }
    }

    public void pause() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            logger.error("", e);
        }
    }

    protected Object executeJS(final String script, final Object... params) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;
        return javascriptExecutor.executeScript(script, params);
    }

    public void waitForPageLoad() {
        String pageLoadStatus;
        do {
            pageLoadStatus = (String) executeJS("return document.readyState");
            logger.info("Page is loading");
        } while (!pageLoadStatus.equals("complete"));
        waitForVisibilityBy(By.cssSelector("#nav-logo"));
        logger.info("Page is loaded");
    }

    public void moveMouseCursorToWebElement(WebElement webElement) {
        waitForClickable(webElement);
        Actions action = new Actions(getWebDriver());
        action.moveToElement(webElement).perform();
    }

    public void scrollToElement(WebElement webElement) {
        executeJS("arguments[0].scrollIntoView(true);", webElement);
    }

    public void switchToTheNewTab() {
        String originalWindow = webDriver.getWindowHandle();
        ArrayList<String> allWindows = new ArrayList<String>(webDriver.getWindowHandles());
        for (String windowHandle : webDriver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                webDriver.switchTo().window(windowHandle);
                break;
            }
        }
    }
}