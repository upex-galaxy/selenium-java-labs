package e2e.pages.Lilia;

import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import e2e.utils.Action;
import e2e.utils.Locator;

// Tech Debt:GX3-3180 = https://upexgalaxy38.atlassian.net/browse/GX3-3208

public class ProductListPage {
	WebDriver web;
	Locator get;
	Action Do;

	private Supplier<List<WebElement>> productTitles;
	private Supplier<List<WebElement>> productPrices;
	private Supplier<List<WebElement>> productDescriptions;
	private String selectedName;
	private String selectedPrice;
	private String selectedDescription;

	public ProductListPage(WebDriver driver, Locator locator, Action action) {
		this.web = driver;
		this.get = locator;
		this.Do = action;

		// Selectores
		this.productTitles = () -> this.get.Selectors("div[data-test=inventory-item-name]");
		this.productPrices = () -> this.get.Selectors("div[data-test=inventory-item-price]");
		this.productDescriptions = () -> this.get.Selectors("div[data-test=inventory-item-desc]");

	}

	public Integer randomNumber(Integer number) {
		Random rand = new Random();
		int randomIndex = rand.nextInt(number);
		return randomIndex;
	}

	public void clickOnRandomProductTitle() {
		List<WebElement> productElements = this.productTitles.get();
		int productsNamesIndex = productElements.size();
		int randomProductIndex = randomNumber(productsNamesIndex);

		// Store the selected product data before clicking on the product title
		String selectedName = productElements.get(randomProductIndex).getText();
		String selectedPrice = this.productPrices.get().get(randomProductIndex).getText();
		String selectedDescription = this.productDescriptions.get().get(randomProductIndex).getText();
		// Accede al elemento en el índice aleatorio y haz clic en él
		productElements.get(randomProductIndex).click();

		// Actualiza los datos del producto seleccionado
		this.selectedName = selectedName;
		this.selectedPrice = selectedPrice;
		this.selectedDescription = selectedDescription;

	}

	public String getselectedName() {
		return this.selectedName;
	}

	public String getSelectedPrice() {
		return this.selectedPrice;
	}

	public String getSelectedDescription() {
		return this.selectedDescription;
	}

}