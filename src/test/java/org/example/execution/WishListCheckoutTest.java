package org.example.execution;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.example.Pages.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

    public class WishlistCheckoutTest {
        WebDriver driver;
        HomePage homePage;
        ProductPage productPage;
        WishlistPage wishlistPage;
        CartPage cartPage;
        CheckoutPage checkoutPage;

        @BeforeClass
        public void setup() {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
            driver.get("https://demo.nopcommerce.com/");
            PageFactory.initElements(driver, this);
            homePage = new HomePage(driver);
            productPage = new ProductPage(driver);
            wishlistPage = new WishlistPage(driver);
            cartPage = new CartPage(driver);
            checkoutPage = new CheckoutPage(driver);
        }

        public void takeScreenshot(String screenshotName) throws IOException {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            String screenshotPath = "C:\\Users\\HP\\IdeaProjects\\Project\\Project\\src\\test\\java\\org\\example\\Pages\\Utility\\Screenshots\\WishList\\" + screenshotName + ".png";
            File destination = new File(screenshotPath);
            FileUtils.copyFile(source, destination);
        }

        @Test
        public void testAddProductToWishlist() throws IOException {
            productPage.searchForProduct("Laptop");
            productPage.clickAddToWishlist();
            Assert.assertTrue(productPage.isProductAddedToWishlist(), "Product not added to wishlist");
            takeScreenshot("addedToWishlist");
        }

        @Test(dependsOnMethods = "testAddProductToWishlist")
        public void testCheckoutFromWishlist() throws IOException {
            homePage.navigateToWishlist();
            Assert.assertTrue(wishlistPage.areItemsInWishlist(), "Wishlist is empty");

            wishlistPage.addToCart();
            Assert.assertTrue(productPage.isProductAddedToCart(), "Product not added to cart from wishlist");

            cartPage.clickTerms();
            cartPage.proceedToCheckout();
            checkoutPage.fillBillingDetails("Shweta", "More", "Shweta@example.com", "India", "Maharashtra", "Sangli", "165 Madhavnagar", "416410", "1234567890");
            checkoutPage.selectShippingMethod("ground");
            checkoutPage.selectPaymentMethod("credit card");
            checkoutPage.confirmOrder();

            takeScreenshot("OrderConfirmation");
            Assert.assertTrue(checkoutPage.isOrderConfirmed(), "Order was not confirmed successfully");
        }

        @AfterClass
        public void tearDown() {
            if (driver != null) {
                driver.quit();
            }
        }
    }

}
