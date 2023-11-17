package e2e.pages.LuisRicardo;

import java.util.List;
import java.util.function.Supplier;
import org.openqa.selenium.WebElement;
import e2e.utils.Action;
import e2e.utils.Locator;

import java.text.DecimalFormat;

public class CheckoutOverviewPage {
    Locator get;
    Action Do;

    private Supplier<WebElement> finishButton;
    private Supplier<WebElement> totalPriceLabel;
    private Supplier<List<WebElement>> productsPrices;

    public CheckoutOverviewPage(Locator locator, Action action) {
        this.get = locator;
        this.Do = action;
        this.finishButton = () -> this.get.ByTestId("finish");
        this.productsPrices = () -> this.get.ByClasses("inventory_item_price");
        this.totalPriceLabel = () -> this.get.ByClass("summary_total_label");
    }

    public Double getTotalPrices() {
        int[] productIndices = { 0, 1, 2 };
        Double totalPrices = 0.00;

        for (int productIndex : productIndices) {
            WebElement productsPrices = this.productsPrices.get().get(productIndex);
            String priceText = productsPrices.getText();
            Double priceDouble = Double.parseDouble(priceText.replace("$", ""));
            totalPrices = totalPrices + priceDouble;
        }
        Double igv = 12.49;
        totalPrices = totalPrices + totalPrices / igv;
        DecimalFormat df = new DecimalFormat("#.##");
        double totalPrice = Double.parseDouble(df.format(totalPrices));
        return totalPrice;

    }

    public String totalPriceLabel() {
        WebElement totalPriceLabel = this.totalPriceLabel.get();
        String totalPriceText = totalPriceLabel.getText().replace("Total: $", "");
        return totalPriceText;
    }

    public void finishButton() {
        this.Do.click(this.finishButton.get());
    }

}
