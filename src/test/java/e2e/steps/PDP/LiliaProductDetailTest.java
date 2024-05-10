package e2e.steps.PDP;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebElement;

import e2e.fixtures.TestBase;
import e2e.pages.Lilia.LoginPage;
import e2e.pages.Lilia.ProductDetailPage;
import e2e.pages.Lilia.ProductListPage;
import e2e.pages.Lilia.ShoppigCartPage;
import io.qameta.allure.*;
import static io.qameta.allure.SeverityLevel.*;

import java.io.IOException;

// Tech Debt:GX3-3208 = https://upexgalaxy38.atlassian.net/browse/GX3-3208
public class LiliaProductDetailTest extends TestBase {
	@Epic("PDP")
	@Feature("")
	@Story("GX3-3208: SwagLabs | PDP | Visualizar Detalles del Item")
	@BeforeEach
	public void precondition() {

		LoginPage loginPage = new LoginPage(web, get, Do);
		web.get(BASE_URL);
		loginPage.Login();
		then.shouldContain(web.getCurrentUrl(), "inventory.html");
	}

	@Test
	@Severity(BLOCKER)
	@DisplayName("TC1: Validate clicking on the product's Details without adding to the shopping cart")
	@Description("Validate clicking on the product's Details")

	public void productDetailTest(TestInfo testInfo) throws InterruptedException, IOException {
		ProductListPage productListPage = new ProductListPage(web, get, Do);
		ProductDetailPage productDetailPage = new ProductDetailPage(web, get, Do);

		Allure.step("Step 1: Click on random product", (step) -> {
			productListPage.clickOnRandomProductTitle();
		});

		String productName = productListPage.getselectedName();
		String productPrice = productListPage.getSelectedPrice();
		String productDesc = productListPage.getSelectedDescription();

		String productDetailName = productDetailPage.getProductDetailName();
		String productDetailPrice = productDetailPage.getProductDetailPrice();
		WebElement productDetailImg = productDetailPage.getProductDetailImg();
		String productDetailDesc = productDetailPage.getProductDetailDescription();

		Allure.step("Expected result: Random product is selected successfully", (step) -> {
			then.shouldContain(web.getCurrentUrl(), "inventory-item.html?id=");
			then.shouldBeEqual(productDetailName, productName);
			then.shouldBeEqual(productDetailPrice, productPrice);
			then.shouldBeEqual(productDetailDesc, productDesc);
			then.shouldBeVisible(productDetailImg);
			then.shouldBeVisible(productDetailPage.getAddToCartButton());
			then.shouldBeVisible(productDetailPage.getBackToProductsButton());
			Do.screenshot(testInfo);

		});

	}

	@Test
	@Severity(BLOCKER)
	@DisplayName("TC2: Validate  product's details added to the SCP")
	@Description("Validate product's details from the shopping cart page")
	public void productShoppingCartTest(TestInfo testInfo) throws InterruptedException, IOException {
		ProductListPage productListPage = new ProductListPage(web, get, Do);
		ShoppigCartPage shoppigCartPage = new ShoppigCartPage(web, get, Do);

		Allure.step("Step 1: Click on random product", (step) -> {
			productListPage.clickOnRandomProductTitle();
		});

		String productName = productListPage.getselectedName();
		String productPrice = productListPage.getSelectedPrice();
		String productDesc = productListPage.getSelectedDescription();
		Allure.step("Step 2: Add product to shopping cart", (step) -> {
			shoppigCartPage.clickAddToCartButton();
		});

		Allure.step("Step 3: click on shopping cart icon", () -> {
			shoppigCartPage.clickShoppingCartIcon();
		});
		String productShoppingCartName = shoppigCartPage.getProductName();
		String productShoppingCartDescription = shoppigCartPage.getProductDescription();
		String productShoppingCartPrice = shoppigCartPage.getProductPrice();

		Allure.step("Expected result: Verify product's details in the shopping cart", (step) -> {
			then.shouldBeEqual(productShoppingCartName, productName);
			then.shouldBeEqual(productShoppingCartDescription, productDesc);
			then.shouldBeEqual(productShoppingCartPrice, productPrice);
			then.shouldBeVisible(shoppigCartPage.getRemoveButton());
			then.shouldBeVisible(shoppigCartPage.getContinueShopping());
			Do.screenshot(testInfo);

		});

	}

}
