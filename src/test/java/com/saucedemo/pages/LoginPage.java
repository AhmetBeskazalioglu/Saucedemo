package com.saucedemo.pages;

import com.saucedemo.utilities.ConfigurationReader;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    @FindBy(id = "user-name")
    public WebElement l_usernameInput;
    @FindBy(id = "password")
    public WebElement l_passwordInput;
    @FindBy(id = "login-button")
    public WebElement l_loginBtn;



    /**
     * This method will login, version #1
     *
     */
    public void login() {
        String username = ConfigurationReader.get("username");
        String password = ConfigurationReader.get("password");
        l_usernameInput.sendKeys(username);
        l_passwordInput.sendKeys(password);
        l_loginBtn.click();
    }

    /**
     * This method will login, version #2
     * @param username
     * @param password
     */
    public void login(String username,String password) {
        l_usernameInput.sendKeys(username);
        l_passwordInput.sendKeys(password);
        l_loginBtn.click();
    }
}
