package e2e.pages;

import java.util.function.Supplier;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import e2e.utils.Action;
import e2e.utils.Assertion;
import e2e.utils.Locator;

//*  Tech Debt: GX3-253 = https://upexgalaxy26.atlassian.net/browse/GX3-253
public class LoginPage {
    // EL PAGE OBJECT MODEL SE DIVIDE EN 3 SECTORES PRINCIPALES:

    private WebDriver web;
    private Locator get;
    private Action Do;
    private Assertion validate;
    private Supplier<WebElement> usernameInput;
    private Supplier<WebElement> passwordInput;
    private Supplier<WebElement> loginSubmitButton;

    // * #1 ARMAR EL CONSTRUCTOR con WebDriver (para usar los selectores/Locators)
    public LoginPage(WebDriver driver, Locator locator, Action action) {
        this.web = driver;
        this.get = locator;
        this.Do = action;
        this.validate = new Assertion();
        // * #2 LOCATORS DE PAGE => dentro del Constructor (requiere tipado Supplier<>)
        this.usernameInput = () -> this.get.ByTestId("username");
        this.passwordInput = () -> this.get.ByTestId("password");
        this.loginSubmitButton = () -> this.get.ByTestId("login-button");
    }

    // * #3 MÉTODOS CON LOS SELECTORES => métodos public
    // void/String/WebElement/Numbers
    public void enterUsername(String value) {
        this.Do.enterValue(this.usernameInput.get(), value);
    }

    public void enterPassword(String value) {
        this.Do.enterValue(this.passwordInput.get(), value);
    }

    public void submitLogin() {
        this.Do.click(this.loginSubmitButton.get());
    }

    // * Shortcut:
    public void login() {
        this.enterUsername("standard_user");
        this.enterPassword("secret_sauce");
        this.submitLogin();
        this.validate.shouldContain(web.getCurrentUrl(), "inventory.html");
    }

    public String getLoginError() {
        return this.get.ByTestId("error").getText();

    }

    public void confirmTestPassed(String error, String value) {
        if (error.equals(value)) {
            System.out.println("Test Passed: " + '"' + getLoginError() + '"');
        } else {
            System.out.println("Test Failed: " + '"' + getLoginError() + '"');
        }
    }

    public void navigateToEndpoint(String BASE_URL, String endpoint) {
        this.web.get(BASE_URL + endpoint);
    }

}
