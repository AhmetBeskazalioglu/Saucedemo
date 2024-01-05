package com.saucedemo.pages;

import com.saucedemo.utilities.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import javax.xml.xpath.XPath;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductsPage extends BasePage {
    static ArrayList<WebElement> newList;
    static ArrayList<String> newVerifyList;
    CartPage cartPage = new CartPage();

    @FindBy(css = "[class='product_sort_container']")
    public WebElement l_sortBy;

    @FindBy(xpath = "//div[@class='inventory_item']//button")
    public List<WebElement> l_addToCartBtns;

    @FindBy(xpath = "//div[@class='inventory_item']//a//div")
    public List<WebElement> l_productNames;
    @FindBy(xpath = "//div[@class='inventory_item']")
    public List<WebElement> l_productList;

    @FindBy(xpath = "//a[@class='shopping_cart_link']")
    public WebElement l_cartProductsCount;


    // Add to cart methods

    /**
     * This method will add the product to the cart by name
     *
     * @param
     */
    public void addProductToCart() {
        newList = new ArrayList<>();
        newVerifyList = new ArrayList<>();
        // How many products are add to the cart with random
        // 2-6 products will be added to the cart
        int random1 = (int) (Math.random() * l_productList.size() - 1) + 2;
        for (int i = 0; i < random1; i++) {
            // How many rows of products are add to the cart with random
            // 1-6 rows will be added to the cart
            int random2 = (int) (Math.random() * l_productList.size() - 1) + 1;
            // will add if there is no l_productNames.get(random2) in newList
            if (!(newList.contains(l_addToCartBtns.get(random2)))) {
                newList.add(l_addToCartBtns.get(random2));
            }
        }
        // click the add cart button of the element in the newlist as String
        for (WebElement webElement : newList) {
            newVerifyList.add(webElement.getAttribute("id"));
            webElement.click();
        }
    }

    /***
     * This method will add the product to the cart by l_addToCartBtns index
     * @param index
     */
    public void addProductToCart(int index) {
        l_addToCartBtns.get(index).click();
    }


    // Get Number methods

    /**
     * This method will return how many products are in the cart
     */
    public int getNumberOfProductsInTheCart() {
        return l_cartProductsCount.getText().isEmpty() ? 0 : Integer.parseInt(l_cartProductsCount.getText());
    }

    /**
     * This method will return how many products are added to the cart
     */
    public int getNumberOfProductsAddedToTheCart() {
        int count = 0;
        for (WebElement webElement : l_addToCartBtns) {
            if (webElement.getText().equals("Remove")) {
                count++;
            }
        }
        return count;
    }


    // Verify methods

    /**
     * This method will verify how many products are added to the cart with int
     */
    public void verifyNumberOfProductsAddedToTheCart() {
        int actual = getNumberOfProductsAddedToTheCart();
        int expected = getNumberOfProductsInTheCart();
        if (actual == expected) {
            System.out.println("PASS: Number of products added to the cart is " + actual);
        } else {
            System.out.println("FAIL: Number of products added to the cart is " + actual);
            System.out.println("expected = " + expected);
        }
    }

    /**
     * This method will verify how many products are added to the cart with String
     */
    public void verifyOfProductsAddedToTheCart() {
        String actual = getNumberOfProductsAddedToTheCart() + "";
        String expected = getNumberOfProductsInTheCart() + "";
        Assert.assertEquals(actual, expected, "FAIL: Number of products added to the cart is " + actual);
    }

    /**
     * This method will verify which products are added to the cart
     */
    public void verifyWhichProductsAddedToTheCart() {
        ArrayList<String> cartItems = new ArrayList<>();
        for (WebElement webElement : cartPage.l_cartItems) {
            String str = webElement.getAttribute("id");
            str = str.replaceAll("remove", "add-to-cart");
            cartItems.add(str);
        }
        Assert.assertEquals(newVerifyList, cartItems, "FAIL: These products are not added to the cart: " + newList);
    }


    // Sort methods

    /***
     * This method will sort the products by price (high to low)
     */
    public void sortProducts() {
        Select select = new Select(l_sortBy);
        select.selectByVisibleText("Price (high to low)");
    }

    /***
     * This method will sort the products by String option
     */
    public void sortProducts(String option) {
        Select select = new Select(l_sortBy);
        select.selectByVisibleText(option);
    }

    /***
     * This method will sort the products by index
     */
    public void sortProducts(int index) {
        Select select = new Select(l_sortBy);
        select.selectByIndex(index);
    }


}
