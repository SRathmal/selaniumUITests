package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.InventoryPage;
import page.LoginPage;
import Utils.BaseTest;
public class AddToCartTest extends BaseTest {
    private LoginPage loginPage;
    private InventoryPage inventoryPage;

    @BeforeMethod
    public void setUpPages() {
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);

        
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();
    }

    @Test(priority = 1)
    public void testAddToCartSuccessfully() {
        inventoryPage.clickAddToCart();
        boolean isBadgeDisplayed = inventoryPage.isCartBadgeDisplayed();
        Assert.assertTrue(isBadgeDisplayed, "Item was not added to the cart!");
    }

    @Test(priority = 2)
    public void testAddToCartWithoutLogin() {
        driver.get("https://www.saucedemo.com/inventory.html"); 
        inventoryPage.clickAddToCart();

        
        boolean isBadgeDisplayed = inventoryPage.isCartBadgeDisplayed();
        Assert.assertFalse(isBadgeDisplayed, "Item should not be added to cart without login!");
    }
}
