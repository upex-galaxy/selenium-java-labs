package e2e.pages.LuisRicardo;

import java.util.List;
import java.util.function.Supplier;
import org.openqa.selenium.WebElement;
import e2e.utils.Action;
import e2e.utils.Locator;

public class CheckoutCompletePage {
    Locator get;
    Action Do;

    private Supplier<WebElement> backHomeButton;
    private Supplier<WebElement> completeHeader;

    public CheckoutCompletePage(Locator locator, Action action) {
        this.get = locator;
        this.Do = action;
        this.completeHeader = () -> this.get.ByClass("complete-header");
        this.backHomeButton = () -> this.get.ByTestId("back-to-products");

    }

    public String validateCheckoutMessage() {
        WebElement completeHeader = this.completeHeader.get();
        String completeHeaderText = completeHeader.getText();
        return completeHeaderText;
    }

    public void backHomeButton() {
        this.Do.click(this.backHomeButton.get());
    }
}
