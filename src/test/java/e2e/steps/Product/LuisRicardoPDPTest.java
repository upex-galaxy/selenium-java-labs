package e2e.steps.Product;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebElement;

import e2e.fixtures.TestBase;
import e2e.pages.LoginPage;
import e2e.pages.LuisRicardo.ProductListPage;
import e2e.pages.LuisRicardo.ShoppingCartPage;
import e2e.pages.LuisRicardo.InformationToCheckoutPage;
import e2e.pages.LuisRicardo.CheckoutOverviewPage;
import e2e.pages.LuisRicardo.CheckoutCompletePage;

//* Tech Debt: GX3-321 = https://upexgalaxy26.atlassian.net/browse/GX3-321
public class LuisRicardoPDPTest extends TestBase {

    // private LoginPage loginPage;

    @BeforeEach
    public void precondition() {
        web.get("https://www.saucedemo.com");
        LoginPage loginPage = new LoginPage(web, get, Do);
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.submitLogin();
        then.shouldContain(web.getCurrentUrl(), "inventory.html");
    }

    @Test
    @DisplayName("GX3-391 | TC1: Validar que agrega un producto al carrito")
    public void addToCart() throws InterruptedException {

        ProductListPage productDetailsPage = new ProductListPage(get, Do);
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(get, Do);
        InformationToCheckoutPage informationToCheckoutPage = new InformationToCheckoutPage(get, Do);
        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(get, Do);
        CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage(get, Do);

        int[] productIndices = { 0, 1, 2 };

        for (int productIndex : productIndices) {
            productDetailsPage.addItem(productIndex);
            String selector = "#remove-sauce-labs-backpack";
            WebElement output = get.Selector(selector);
            String value = output.getText();
            then.shouldBeEqual(value, "Remove");
            then.shouldBeEqual(productDetailsPage.getTotalCartItems(), String.valueOf(productIndex + 1));
            Thread.sleep(1000);
            then.shouldBeEqual(productDetailsPage.getDescriptionProductText(productIndex),
                    String.valueOf(shoppingCartPage.getDescriptionProductText(productIndex)));
        }
        shoppingCartPage.goToShoppingCartPage();
        then.shouldContain(web.getCurrentUrl(), "cart.html");
        Thread.sleep(1000);

        shoppingCartPage.checkoutButton();
        then.shouldContain(web.getCurrentUrl(), "checkout-step-one.html");
        Thread.sleep(1000);

        informationToCheckoutPage.enterFirstName("Luis Ricardo");
        informationToCheckoutPage.enterLastName("Marcelo Gómez");
        informationToCheckoutPage.enterZipCode("02711");
        informationToCheckoutPage.continueButton();
        then.shouldContain(web.getCurrentUrl(), "checkout-step-two.html");
        Thread.sleep(1000);

        System.out.println("La RESPUESTA ES: " + checkoutOverviewPage.getTotalPrices());
        then.shouldBeEqual(String.valueOf(checkoutOverviewPage.getTotalPrices()),
                checkoutOverviewPage.totalPriceLabel());
        checkoutOverviewPage.finishButton();
        Thread.sleep(1000);

        then.shouldBeEqual(checkoutCompletePage.validateCheckoutMessage(), "Thank you for your order!");
        checkoutCompletePage.backHomeButton();
        then.shouldContain(web.getCurrentUrl(), "https://www.saucedemo.com/");
        Thread.sleep(1000);
    }
}
