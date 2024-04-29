package e2e.pages.Lilia;

import java.util.function.Supplier;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import e2e.utils.Action;
import e2e.utils.Locator;

public class ShoppigCartPage {
	WebDriver web;
	Locator get;
	Action Do;
	private Supplier<WebElement> removeButton;
	private Supplier<WebElement> continueShoppingButton;
	private Supplier<WebElement> shoppingCartIcon;
	private Supplier<WebElement> addToCartButton;
	private Supplier<WebElement> productName;
	private Supplier<WebElement> productPrice;
	private Supplier<WebElement> productDescription;

	public ShoppigCartPage(WebDriver driver, Locator locator, Action action) {
		this.web = driver;
		this.get = locator;
		this.Do = action;

		this.removeButton = () -> this.get.Selector("button[data-test^='remove']");
		this.continueShoppingButton = () -> this.get.Selector("button[data-test=continue-shopping]");
		this.shoppingCartIcon = () -> this.get.Selector("a[data-test=shopping-cart-link]");
		this.addToCartButton = () -> this.get.Selector("button[data-test=add-to-cart]");
		this.productName = () -> this.get.Selector("div[data-test=inventory-item-name]");
		this.productPrice = () -> this.get.Selector("div[data-test=inventory-item-price]");
		this.productDescription = () -> this.get.Selector("div[data-test=inventory-item-desc]");
	}

	public void clickAddToCartButton() {
		this.Do.click(this.addToCartButton.get());
	}

	public void clickShoppingCartIcon() {
		this.Do.click(this.shoppingCartIcon.get());
	}

	public String getProductName() {
		return this.productName.get().getText();
	}

	public String getProductPrice() {
		return this.productPrice.get().getText();
	}

	public String getProductDescription() {
		return this.productDescription.get().getText();
	}

	public WebElement getRemoveButton() {
		return this.removeButton.get();
	}

	public WebElement getContinueShopping() {
		return this.continueShoppingButton.get();
	}
}
