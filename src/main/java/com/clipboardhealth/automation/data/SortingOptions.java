package com.clipboardhealth.automation.data;

public enum SortingOptions {

    FEATURED("Featured"),
    PRICE_LOW_TO_HIGH("Price: Low to High"),
    PRICE_HIGH_TO_LOW("Price: High to Low"),
    CUSTOMER_REVIEW("Avg. Customer Review"),
    NEWEST_ARRIVALS("Newest Arrivals");

    private String value;

    SortingOptions(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
