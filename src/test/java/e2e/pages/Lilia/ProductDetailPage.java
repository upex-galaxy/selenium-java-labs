package e2e.pages.Lilia;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.google.common.base.Supplier;

import e2e.utils.Action;
import e2e.utils.Locator;

public class ProductDetailPage {
	WebDriver web;
	Locator get;
	Action Do;
	private Supplier<WebElement> productDetailName;
	private Supplier<WebElement> productDetailPrice;
	private Supplier<WebElement> productDetailDescription;
	private Supplier<WebElement> productDetailImg;
	private Supplier<WebElement> backToProductsButton;
	private Supplier<WebElement> addToCartButton;

	public ProductDetailPage(WebDriver driver, Locator locator, Action action) {
		this.web = driver;
		this.get = locator;
		this.Do = action;

		this.productDetailName = () -> this.get.Selector("div[data-test=inventory-item-name]");
		this.productDetailImg = () -> this.get.ByClass("inventory_details_img");
		this.productDetailPrice = () -> this.get.Selector("div[data-test=inventory-item-price]");
		this.productDetailDescription = () -> this.get.Selector("div[data-test=inventory-item-desc]");
		this.backToProductsButton = () -> this.get.Selector("button[data-test=back-to-products]");
		this.addToCartButton = () -> this.get.Selector("button[data-test=add-to-cart]");
	}

	public String getProductDetailName() {
		String title = this.productDetailName.get().getText();
		return title;
	}

	public String getProductDetailPrice() {
		String price = this.productDetailPrice.get().getText();
		return price;
	}

	public String getProductDetailDescription() {
		String description = this.productDetailDescription.get().getText();
		return description;
	}

	public WebElement getProductDetailImg() {
		return this.productDetailImg.get();

	}

	public WebElement getBackToProductsButton() {
		return this.backToProductsButton.get();
	}

	public WebElement getAddToCartButton() {

		return this.addToCartButton.get();
	}

}