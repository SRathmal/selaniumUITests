package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.LoginPage;
import Utils.BaseTest;

public class LoginTest extends BaseTest {
    private LoginPage loginPage;

    @BeforeMethod
    public void setUpPage() {
        loginPage = new LoginPage(driver);
    }

    @Test(priority = 1)
    public void testSuccessfulLogin() {
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();
        
        String currentUrl = loginPage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("inventory.html"), "Login failed!");
    }

    @Test(priority = 2)
    public void testLoginWithInvalidCredentials() {
        loginPage.enterUsername("invalid_user");
        loginPage.enterPassword("wrong_password");
        loginPage.clickLogin();

        String errorMessage = loginPage.getErrorMessage();
        Assert.assertEquals(errorMessage, "Epic sadface: Username and password do not match any user in this service");
    }

    @Test(priority = 3)
    public void testLoginWithEmptyFields() {
        loginPage.enterUsername("");
        loginPage.enterPassword("");
        loginPage.clickLogin();

        String errorMessage = loginPage.getErrorMessage();
        Assert.assertEquals(errorMessage, "Epic sadface: Username is required");
    }
}
