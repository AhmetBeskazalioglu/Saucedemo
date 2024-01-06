package com.saucedemo.tests.saucedemoTest;

import com.github.javafaker.Faker;
import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductsPage;
import com.saucedemo.tests.TestBase;
import com.saucedemo.utilities.ConfigurationReader;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class SaucedemoTest extends TestBase {
    /**
     * 1-Create a TestNG  project from scratch. Design all packages, classes and ohter files... use last version of selenium
     * 2-Navigate to https://www.saucedemo.com/
     * 3-Login with valid credentials (shown on the page)
     * 4-Sort the products high to  low  (prepare a useful method for this )
     * 5-Add to cart at least two item
     * 6-Assert that the basket shows the true number of product
     * 7-Navigate to Your cart page
     * 8-Assert that the right items added to cart
     * 9-Go to checkout page and fill the form
     * 10-click continue button and go to the checkout overview page
     * 11-Click finish and assert the success shopping message
     * <p>
     * notes:       a. every new page should be asserted by page title
     *              b. to have maximum step with minimum method  use the parameterized methods.
     *              c. This is an e2e test scenario
     * <p>
     * good luck...
     */
    LoginPage loginPage;
    ProductsPage productsPage;
    CartPage cartPage;
    SoftAssert softAssert;


    @Test
    public void Login_Sort_AddToCart_VerifyCart_Checkout_Finish() {
        loginPage=new LoginPage();
        productsPage=new ProductsPage();
        cartPage=new CartPage();
        softAssert=new SoftAssert();

        extentLogger=report.createTest("Saucedemo e2e test");
        extentLogger.info("Navigate to "+ ConfigurationReader.get("url") +"");
        driver.get(ConfigurationReader.get("url"));
        extentLogger.info("Login with valid credentials");
        loginPage.login();
        loginPage.verifyPageTitle();
        extentLogger.info("Sort the products high to low");
        productsPage.sortProducts(); // high to low (default-not dynamic) There are two methods also
        extentLogger.info("Add to cart at least two item");
        productsPage.addProductToCart();
        extentLogger.info("Assert that the basket shows the true number of product");
        productsPage.verifyOfProductsAddedToTheCart();
        productsPage.verifyNumberOfProductsAddedToTheCart();
        extentLogger.info("Navigate to Your cart page");
        productsPage.navigateToCartPage();
        productsPage.verifyPageTitle();
        extentLogger.info("Assert that the right items added to cart");
        productsPage.verifyWhichProductsAddedToTheCart();
        extentLogger.info("Go to checkout page and fill the form");
        cartPage.checkout();
        productsPage.verifyPageTitle();
        extentLogger.info("Click continue button and go to the checkout overview page");
        cartPage.continueCheckout();
        productsPage.verifyPageTitle();
        extentLogger.info("Click finish and assert the success shopping message");
        cartPage.finishCheckout();
        productsPage.verifyPageTitle();
        cartPage.verifyOrderCompleted();
        extentLogger.pass("PASS: Saucedemo e2e test");

    }
}
