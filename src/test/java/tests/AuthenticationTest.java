package tests;

import base.BasicSetupTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class AuthenticationTest extends BasicSetupTest {
    @Test
    public void succeccfulLoginTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.enterUsername("tomsmith");
        loginPage.enterPassword("SuperSecretPassword!");
        loginPage.clickLoginButton();

        String flashMessage = loginPage.getFlashMessage();
        Assert.assertTrue(flashMessage.contains("You logged into a secure area!"), "Очікується повідомлення про успішний логін");
    }


    @Test
    public void logoutTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();

        loginPage.loginAs("tomsmith", "SuperSecretPassword!");

        loginPage.logout();

        Assert.assertTrue(loginPage.isLoginPageVisible(), "Очікується повернення на сторінку логіну після виходу");
    }
}
