package com.clipboardhealth.automation;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.clipboardhealth.automation.pageobject.CategoryPage;
import com.clipboardhealth.automation.pageobject.HomePage;
import com.clipboardhealth.automation.pageobject.ItemDetailsPage;

import static com.clipboardhealth.automation.data.SortingOptions.PRICE_HIGH_TO_LOW;

public class AmazonTest extends BaseTest {

    private HomePage homePage;
    private CategoryPage categoryPage;
    private ItemDetailsPage itemDetailsPage;

    @BeforeClass(alwaysRun = true)
    public void beforeClassInit() {
        homePage = new HomePage();
        categoryPage = new CategoryPage();
        itemDetailsPage = new ItemDetailsPage();
    }

    @Test(description = "Verify item description section title and print content")
    public void testItemDescriptionText() {
        homePage
                .openNavMenu()
                .selectMenuRowByName("TV, Appliances, Electronics")
                .selectMenuRowByName("Televisions");
        categoryPage
                .selectFilter("Samsung")
                .openSortByDropDown()
                .selectSortOption(PRICE_HIGH_TO_LOW.getValue())
                .selectSearchResultItemByPosition(2)
                .switchToTheNewTab();
        itemDetailsPage
                .verifyItemDetailsSectionTitle("About this item")
                .publishItemDetailsText();
    }
}
