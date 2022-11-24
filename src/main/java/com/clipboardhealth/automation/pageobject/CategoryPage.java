package com.clipboardhealth.automation.pageobject;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CategoryPage extends BasePage {

    @FindBy(css = ".apb-browse-refinements-checkbox + span")
    private List<WebElement> filterOptions;

    @FindBy(css = "[data-action=a-dropdown-button]")
    private WebElement sortByBtn;

    @FindBy(css = ".a-dropdown-link")
    private List<WebElement> sortByOptions;

    @FindBy(css = ".s-card-container")
    private List<WebElement> searchResultItems;

    public CategoryPage selectFilter(String filterName) {
        waitForPageLoad();
        click(filterOptions.stream().filter(f -> f.getText().equals(filterName)).findFirst().get());
        return this;
    }

    public CategoryPage openSortByDropDown() {
        click(sortByBtn);
        return this;
    }

    public CategoryPage selectSortOption(String sortOption) {
        click(sortByOptions.stream().filter(o -> o.getText().equals(sortOption)).findFirst().get());
        return this;
    }

    public ItemDetailsPage selectSearchResultItemByPosition(int position) {
        click(searchResultItems.get(position - 1));
        return new ItemDetailsPage();
    }
}
