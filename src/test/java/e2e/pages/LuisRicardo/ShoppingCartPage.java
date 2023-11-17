package e2e.pages.LuisRicardo;

import java.util.List;
import java.util.function.Supplier;
import org.openqa.selenium.WebElement;
import e2e.utils.Action;
//import e2e.utils.Assertion;
import e2e.utils.Locator;

//*  Tech Debt: GX3-321 = https://upexgalaxy26.atlassian.net/browse/GX3-321

public class ShoppingCartPage {
    // EL PAGE OBJECT MODEL SE DIVIDE EN 3 SECTORES PRINCIPALES:

    private Locator get;
    private Action Do;

    private Supplier<WebElement> shoppingCartButton;
    private Supplier<List<WebElement>> getDescriptionProductText;
    private Supplier<WebElement> checkoutButton;

    public ShoppingCartPage(Locator locator, Action action) {

        this.get = locator;
        this.Do = action;
        this.shoppingCartButton = () -> this.get.ByClass("shopping_cart_link");
        this.getDescriptionProductText = () -> this.get.ByClasses("inventory_item_description");
        this.checkoutButton = () -> this.get.ByTestId("checkout");
    }

    public void goToShoppingCartPage() {
        this.Do.click(this.shoppingCartButton.get());
    }

    public String getDescriptionProductText(Integer productIndex) {
        WebElement getDescriptionProductText = this.getDescriptionProductText.get().get(productIndex);
        String descriptionProductText = getDescriptionProductText.getText();
        return descriptionProductText;
    }

    public void checkoutButton() {
        this.Do.click(this.checkoutButton.get());
    }
}
