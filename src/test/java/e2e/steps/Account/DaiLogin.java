package e2e.steps.Account;

import org.junit.jupiter.api.*;

import e2e.fixtures.TestBase;
import e2e.pages.InventoryPage;
import e2e.pages.LoginPage;

public class DaiLogin extends TestBase {
    @BeforeEach
    public void precondition() {
        web.get(BASE_URL);
    }

    @Test
    @DisplayName("TC01: Validar inicio de sesión exitoso")
    public void loginSuccess() throws InterruptedException {
        System.out.println("TC01: Validar inicio de sesión exitoso");
        // Imprimir en Debug Console

        LoginPage loginPage = new LoginPage(web, get, Do);
        InventoryPage inventoryPage = new InventoryPage(web, get, Do);
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.submitLogin();
        then.shouldContain(web.getCurrentUrl(), "inventory.html");
        if (inventoryPage.isOnInventoryPage()) {
            System.out.println("Test Passed: " + web.getCurrentUrl());
        }
        Thread.sleep(2000);

    }

    @Test
    @DisplayName("TC02: Validar NO poder iniciar sesión cuando la cuenta esta bloqueada")
    public void loginLockedAccount() throws InterruptedException {

        LoginPage loginPage = new LoginPage(web, get, Do);
        loginPage.enterUsername("locked_out_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.submitLogin();
        loginPage.confirmTestPassed(loginPage.getLoginError(), "Epic sadface: Sorry, this user has been locked out.");
        then.shouldContain(web.getCurrentUrl(), "https://www.saucedemo.com/");
        Thread.sleep(2000);

    }

    @Test
    @DisplayName("TC03: Validar NO poder iniciar sesión cuando el usuario es invalido")
    public void loginInvalidUser() throws InterruptedException {
        LoginPage loginPage = new LoginPage(web, get, Do);
        loginPage.enterUsername("invalidUserName");
        loginPage.enterPassword("secret_sauce");
        loginPage.submitLogin();
        then.shouldContain(web.getCurrentUrl(), "https://www.saucedemo.com/");
        loginPage.confirmTestPassed(loginPage.getLoginError(),
                "Epic sadface: Username and password do not match any user in this service");
        Thread.sleep(1000);

    }

    @Test
    @DisplayName("TC04: Validar NO poder iniciar sesión cuando el campo Username está vacío en el formulario")
    public void loginEmptyUsername() throws InterruptedException {
        LoginPage loginPage = new LoginPage(web, get, Do);
        loginPage.enterUsername("");
        loginPage.enterPassword("secret_sauce");
        loginPage.submitLogin();
        then.shouldContain(web.getCurrentUrl(), "https://www.saucedemo.com/");
        loginPage.confirmTestPassed(loginPage.getLoginError(), "Epic sadface: Username is required");
        Thread.sleep(1000);

    }

    @Test
    @DisplayName("TC05: Validar NO poder iniciar sesión cuando el campo Password está vacío en el formulario")
    public void loginEmptyPassword() throws InterruptedException {
        LoginPage loginPage = new LoginPage(web, get, Do);
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("");
        loginPage.submitLogin();
        then.shouldContain(web.getCurrentUrl(), "https://www.saucedemo.com/");
        loginPage.confirmTestPassed(loginPage.getLoginError(), "Epic sadface: Password is required");
        Thread.sleep(1000);

    }

    @Test
    @DisplayName("TC06: Validar NO poder iniciar sesión cuando los campos Username y Password están vacíos en el formulario")
    public void loginEmptyForm() throws InterruptedException {
        LoginPage loginPage = new LoginPage(web, get, Do);
        loginPage.enterUsername("");
        loginPage.enterPassword("");
        loginPage.submitLogin();
        then.shouldContain(web.getCurrentUrl(), "https://www.saucedemo.com/");
        loginPage.confirmTestPassed(loginPage.getLoginError(), "Epic sadface: Username is required");
        Thread.sleep(1000);

    }

    @Test
    @DisplayName("TC07: Validar NO poder poder acceder por un endpoint cuando no está logueado")
    public void tryEndpointAccess() throws InterruptedException {
        // *Endpoint: /inventory.html
        String endpoint = "/inventory.html";
        LoginPage loginPage = new LoginPage(web, get, Do);

        loginPage.navigateToEndpoint(BASE_URL, endpoint);

        then.shouldContain(web.getCurrentUrl(), "https://www.saucedemo.com/");
        loginPage.confirmTestPassed(loginPage.getLoginError(),
                "Epic sadface: You can only access '" + endpoint + "' when you are logged in.");

        Thread.sleep(1000);

    }
}