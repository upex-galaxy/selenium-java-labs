package e2e.pages.Lilia;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.google.common.base.Supplier;

import e2e.utils.Action;
import e2e.utils.Locator;

// Tech Debt:GX3-3180 = https://upexgalaxy38.atlassian.net/browse/GX3-3180
public class LoginPage {

	WebDriver web;
	Locator get;
	Action Do;
	private Supplier<WebElement> usernameInput;
	private Supplier<WebElement> passwordInput;
	private Supplier<WebElement> loginSubmitButton;

	public LoginPage(WebDriver driver, Locator locator, Action action) {
		this.web = driver;
		this.get = locator;
		this.Do = action;

		this.usernameInput = () -> this.get.ByTestId("username");
		this.passwordInput = () -> this.get.ByTestId("password");
		this.loginSubmitButton = () -> this.get.ByTestId("login-button");

	}

	public void enterUsername(String value) {
		this.Do.enterValue(this.usernameInput.get(), value);
	}

	public void enterPassword(String value) {
		this.Do.enterValue(this.passwordInput.get(), value);
	}

	public void submitLogin() {
		this.Do.click(this.loginSubmitButton.get());

	}

	public void Login() {
		this.enterUsername("standard_user");
		this.enterPassword("secret_sauce");
		this.submitLogin();
	}

}