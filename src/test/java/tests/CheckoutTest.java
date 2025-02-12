package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.CheckoutPage;
import page.LoginPage;
import page.InventoryPage;
import Utils.BaseTest;

public class CheckoutTest extends BaseTest {
    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private CheckoutPage checkoutPage;

    @BeforeMethod
    public void setUpPages() {
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        checkoutPage = new CheckoutPage(driver);

         
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();

        inventoryPage.clickAddToCart();
        driver.findElement(By.className("shopping_cart_link")).click(); 
        driver.findElement(By.id("checkout")).click(); 
    }

    @Test(priority = 1)
    public void testCheckoutWithEmptyFields() {
        checkoutPage.clickContinue();

        String errorMessage = checkoutPage.getErrorMessage();
        Assert.assertEquals(errorMessage, "Error: First Name is required");
    }
}
