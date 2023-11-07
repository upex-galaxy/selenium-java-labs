package e2e.pages;

import java.util.function.Supplier;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import e2e.utils.Action;
import e2e.utils.Assertion;
import e2e.utils.Locator;

public class LoreLoginPage {

    // ATRIBUTOS
    private WebDriver web;
    private Locator get;
    private Action Do;
    private Assertion validate;
    private Supplier<WebElement> loginUsername;
    private Supplier<WebElement> loginPassword;
    private Supplier<WebElement> loginButton;

    // CONSTRUCTOR
    // METODO COMO LA CLASE
    public LoreLoginPage(WebDriver driver, Locator locator, Action action) {
        this.web = driver;
        this.get = locator;
        this.Do = action;
        this.validate = new Assertion();

        // CAPTAMOS EL SELECTOR
        this.loginUsername = () -> this.get.ByTestId("username");
        this.loginPassword = () -> this.get.ByTestId("password");
        this.loginButton = () -> this.get.ByTestId("login-button");
    }

    // METODOS DONDE CAPTO LA ACCION
    public void usernameAction(String value) {
        this.Do.enterValue(this.loginUsername.get(), value);
    }

    public void passwordAction(String value) {
        this.Do.enterValue(this.loginPassword.get(), value);
    }

    public void submitAction() {
        this.Do.click(this.loginButton.get());
    }

    // METODO DONDE INGRESO EL VALOR real
    public void login() {
        this.usernameAction("standard_user");
        this.passwordAction("secret_sauce");
        this.submitAction();
        this.validate.shouldContain(web.getCurrentUrl(), "inventory.html");
    }
}
