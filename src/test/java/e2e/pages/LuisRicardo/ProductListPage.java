package e2e.pages.LuisRicardo;

import java.util.List;
import java.util.function.Supplier;

import org.openqa.selenium.WebElement;

import e2e.utils.Action;
//import e2e.utils.Assertion;
import e2e.utils.Locator;

//*  Tech Debt: GX3-321 = https://upexgalaxy26.atlassian.net/browse/GX3-321

public class ProductListPage {
    // EL PAGE OBJECT MODEL SE DIVIDE EN 3 SECTORES PRINCIPALES:

    private Locator get;
    private Action Do;
    // private Assertion validate;

    private Supplier<List<WebElement>> addToCartButton;
    private Supplier<WebElement> totalCartItems;
    private Supplier<List<WebElement>> getDescriptionProductText;

    public ProductListPage(Locator locator, Action action) {
        this.get = locator;
        this.Do = action;

        this.addToCartButton = () -> this.get.Selectors("[data-test*=add-to-cart]");
        this.totalCartItems = () -> this.get.ByClass("shopping_cart_badge");
        this.getDescriptionProductText = () -> this.get.ByClasses("inventory_item_description");
    }

    public void addItem(Integer product) {
        this.Do.click(this.addToCartButton.get().get(product));
    }

    public String getTotalCartItems() {
        WebElement totalCartItems = this.totalCartItems.get();
        String priceValue = totalCartItems.getText();
        return priceValue;
    }

    public String getDescriptionProductText(Integer productIndex) {
        WebElement getDescriptionProductText = this.getDescriptionProductText.get().get(productIndex);
        String descriptionProductText = getDescriptionProductText.getText();
        return descriptionProductText;
    }
}