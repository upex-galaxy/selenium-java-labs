package e2e.pages.LuisRicardo;

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
    // private Assertion validate;

    public ShoppingCartPage(Locator locator, Action action) {

        this.get = locator;
        this.Do = action;
        this.shoppingCartButton = () -> this.get.ByClass("shopping_cart_link");
    }

    public void goToShoppingCartPage() {
        this.Do.click(this.shoppingCartButton.get());
    }
}
