package e2e.steps.Footer;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.*;

import e2e.fixtures.TestBase;
import e2e.pages.Lilia.SocialNetworksPage;
import e2e.pages.Lilia.LoginPage;
import io.qameta.allure.*;
import java.io.IOException;

import static io.qameta.allure.SeverityLevel.*;

// Tech Debt:GX3-3180 = https://upexgalaxy38.atlassian.net/browse/GX3-3180
@Epic("Footer Component")
@Feature("Social networks authentication")
@Story("GX3-3180: SwagLabs | Footer | Acceder a las redes sociales de SwagLabs")
public class LiliaSocialNetworksTest extends TestBase {
	@BeforeEach
	public void precondition() {
		LoginPage loginPage = new LoginPage(web, get, Do);
		web.get(BASE_URL);
		loginPage.Login();
		then.shouldContain(web.getCurrentUrl(), "inventory.html");
	}

	@Test
	@Severity(MINOR)
	@Issue("https://upexgalaxy38.atlassian.net/browse/GX3-3180")
	@DisplayName("TC1: Validate access to social networks")
	@Description("Validate access to social networks when clicking on each of them")
	public void socialNetworks(TestInfo testInfo) throws InterruptedException, IOException {
		SocialNetworksPage footer = new SocialNetworksPage(web, get, Do);

		Allure.step("Step 1: Click on social network icons", (step) -> {
			for (int i = 0; i < 3; i++) {
				String homeWindow = web.getWindowHandle();
				footer.clickOnSocialIcon(i);
				List<String> linksList = Arrays.asList("https://twitter.com/saucelabs",
						"https://www.facebook.com/saucelabs",
						"https://www.linkedin.com/company/sauce-labs/");

				Set<String> allWindowHandles = web.getWindowHandles();

				for (String handle : allWindowHandles) {
					if (!handle.equals(homeWindow)) {
						web.switchTo().window(handle);

						// Assertion: The user is redirected to the social network
						then.shouldBeEqual(web.getCurrentUrl(), linksList.get(i));
						Do.screenshot(testInfo);
						web.close();
						web.switchTo().window(homeWindow);

					}

				}

			}
			Do.screenshot(testInfo);

		});

		Allure.step("Expected result: The user is redirected to the Home Page", () -> {
			Do.screenshot(testInfo);
		});

	}
}