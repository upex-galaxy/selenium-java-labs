package e2e.pages;

import org.openqa.selenium.WebDriver;

import e2e.utils.Action;
import e2e.utils.Locator;

public class InventoryPage {
    private WebDriver web;
    private Locator get;
    private Action Do;

    public InventoryPage(WebDriver driver, Locator locator, Action action) {
        this.web = driver;
        this.get = locator;
        this.Do = action;

    }

    public boolean isOnInventoryPage() {
        return this.get.ById("inventory_container").isDisplayed();
    }

    // public void inventoryItemList() {
    // this.get.ByClasses("inventory_item");
    // }

}
