package e2e.pages.LuisRicardo;

import java.util.function.Supplier;
import org.openqa.selenium.WebElement;
import e2e.utils.Action;
import e2e.utils.Locator;

public class InformationToCheckoutPage {

    Locator get;
    Action Do;
    private Supplier<WebElement> firstName;
    private Supplier<WebElement> lastName;
    private Supplier<WebElement> zipCode;
    private Supplier<WebElement> continueButton;

    public InformationToCheckoutPage(Locator locator, Action action) {
        this.get = locator;
        this.Do = action;
        this.firstName = () -> this.get.ByTestId("firstName");
        this.lastName = () -> this.get.ByTestId("lastName");
        this.zipCode = () -> this.get.ByTestId("postalCode");
        this.continueButton = () -> this.get.ByTestId("continue");
    }

    public void enterFirstName(String value) {
        this.Do.enterValue(this.firstName.get(), value);
    }

    public void enterLastName(String value) {
        this.Do.enterValue(this.lastName.get(), value);
    }

    public void enterZipCode(String value) {
        this.Do.enterValue(this.zipCode.get(), value);
    }

    public void continueButton() {
        this.Do.click(this.continueButton.get());
    }

}
