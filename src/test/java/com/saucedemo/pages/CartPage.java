package com.saucedemo.pages;

import com.github.javafaker.Faker;
import com.saucedemo.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class CartPage extends BasePage {
    Faker faker = new Faker();

    @FindBy(xpath = "//div[@class='cart_item']//button")
    public List<WebElement> l_cartItems;
    @FindBy(id = "checkout")
    public WebElement l_checkoutBtn;
    @FindBy(id = "first-name")
    public WebElement l_firstName;
    @FindBy(id = "last-name")
    public WebElement l_lastName;
    @FindBy(id = "postal-code")
    public WebElement l_postalCode;
    @FindBy(id = "continue")
    public WebElement l_continueBtn;
    @FindBy(id = "finish")
    public WebElement l_finishBtn;
    @FindBy(xpath = "//h2[@class='complete-header']")
    public WebElement l_orderCompletedMessage;



    /**
     * This method will click checkout button and fill the form
     */
    public void checkout() {
        l_checkoutBtn.click();
        l_firstName.sendKeys(faker.name().firstName());
        l_lastName.sendKeys(faker.name().lastName());
        l_postalCode.sendKeys(faker.address().zipCode());
    }

    /**
     * This method will click continue button
     */
    public void continueCheckout() {
        l_continueBtn.click();
    }

    /**
     * This method will click finish button
     */
    public void finishCheckout() {
        l_finishBtn.click();
    }

    /**
     * This method will verify the order is completed
     */
    public void verifyOrderCompleted() {
        String expected = "Thank you for your order!";
        String actual = l_orderCompletedMessage.getText();
        Assert.assertEquals(actual, expected);
    }

}
