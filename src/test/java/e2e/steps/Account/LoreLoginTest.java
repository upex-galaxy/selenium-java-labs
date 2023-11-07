package e2e.steps.Account;

import org.junit.jupiter.api.*;
import e2e.fixtures.TestBase;

import e2e.pages.LoreLoginPage;

public class LoreLoginTest extends TestBase {

    // PRECONDICION ANTES DE:
    @BeforeEach
    public void precondition() {
        web.get("https://www.saucedemo.com/");
    }

    // TESTEO
    @Test
    @DisplayName("PRECONDICION")
    public void login() throws InterruptedException {
        LoreLoginPage loginPom = new LoreLoginPage(web, get, Do);
        // loginPom.usernameAction("standard_user");
        // loginPom.passwordAction("secret_sauce");
        // loginPom.submitAction();
        // then.shouldContain(web.getCurrentUrl(), "inventory.html");
        loginPom.login();
    }

}
