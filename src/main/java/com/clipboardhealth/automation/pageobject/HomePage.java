package com.clipboardhealth.automation.pageobject;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(css = "#nav-hamburger-menu")
    private WebElement navMenuBtn;

    @FindBy(css = "#hmenu-content")
    private WebElement navMenu;

    @FindBy(css = ".hmenu-item")
    private List<WebElement> navMenuItems;

    public HomePage openNavMenu() {
        click(navMenuBtn);
        return this;
    }

    public HomePage selectMenuRowByName(String rowName) {
        waitForVisibility(navMenu);
        WebElement item = navMenuItems.stream().filter(r -> r.getText().equals(rowName)).findFirst().get();
        scrollToElement(item);
        click(item);
        return this;
    }
}
