package e2e.utils;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Assertion {

    public void shouldBeEqual(String actual, String expected) {
        Assertions.assertEquals(actual, expected);
    }

    public void shouldBeEqualInt(Integer actual, Integer expected) {
        Assertions.assertEquals(actual, expected);
    }

    public void shouldBeDifferent(String actual, String expected) {
        Assertions.assertNotEquals(actual, expected);
    }

    public void shouldBeTrue(Boolean actual_boolean) {
        Assertions.assertTrue(actual_boolean);
    }

    public void shouldBeFalse(Boolean actual_boolean) {
        Assertions.assertFalse(actual_boolean);
    }

    public void shouldContain(String actual, String expected) {
        Assertions.assertTrue(actual.contains(expected));
    }

    public void shouldBeVisible(WebElement locator) {
        Assertions.assertTrue(locator.isDisplayed());
    }

    // Busca en el html en formato string de la pantalla actual
    public void textShouldNotExist(String elementText, WebDriver web) {
        Assertions.assertFalse(web.getPageSource().contains(elementText));
    }

    public void textShouldExist(String elementText, WebDriver web) {
        Assertions.assertTrue(web.getPageSource().contains(elementText));
    }
}
