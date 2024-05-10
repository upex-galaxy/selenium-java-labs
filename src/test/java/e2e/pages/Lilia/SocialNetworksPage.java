package e2e.pages.Lilia;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.function.Supplier;

import e2e.utils.Action;
import e2e.utils.Locator;

// Tech Debt:GX3-3180= https://upexgalaxy38.atlassian.net/browse/GX3-3180
public class SocialNetworksPage {
	WebDriver web;
	Locator get;
	Action Do;

	private Supplier<List<WebElement>> footerIcons;

	public SocialNetworksPage(WebDriver driver, Locator locator, Action action) {
		this.web = driver;
		this.get = locator;
		this.Do = action;

		this.footerIcons = () -> this.get.Selectors("[data-test*=social]");
	}

	public void clickOnSocialIcon(Integer index) {

		WebElement selectedIcon = this.footerIcons.get().get(index);
		selectedIcon.click();

	}

}