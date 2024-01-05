package com.saucedemo.pages;


import com.saucedemo.utilities.BrowserUtils;
import com.saucedemo.utilities.ConfigurationReader;
import com.saucedemo.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.List;

public abstract class BasePage {
    SoftAssert softAssert=new SoftAssert();
    public BasePage() {
        PageFactory.initElements(Driver.get(),this);
    }
    @FindBy(css = ".shopping_cart_link")
    public WebElement l_cartBtn;

    public void navigateToCartPage(){
        l_cartBtn.click();
    }
    //every new page should be asserted by page title
    public void verifyPageTitle(){
        BrowserUtils.waitFor(1);
        String expectedTitle="Swag Labs";
        softAssert.assertEquals(Driver.get().getTitle(),expectedTitle);
    }
}
