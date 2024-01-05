package com.saucedemo.tests;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.saucedemo.utilities.BrowserUtils;
import com.saucedemo.utilities.ConfigurationReader;
import com.saucedemo.utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.time.Duration;

public class TestBase {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions action;
    protected ExtentReports report;
    protected ExtentHtmlReporter htmlReporter;
    protected ExtentTest extentLogger;

    @BeforeTest
    public void setUpTest() {

        report = new ExtentReports();
        String projectPath = System.getProperty("user.dir");
        String path = projectPath + "/test-output/report.html";
        htmlReporter = new ExtentHtmlReporter(path);
        report.attachReporter(htmlReporter);
        htmlReporter.config().setReportName("B5 Saucedemo Test");
        report.setSystemInfo("Environment", "Stage");
        report.setSystemInfo("Browser", ConfigurationReader.get("browser"));
        report.setSystemInfo("OS", System.getProperty("os.name"));
        report.setSystemInfo("Test Engineer", "AB");
    }

    @AfterTest
    public void tearDownTest() {
        report.flush();
    }

    @BeforeMethod
    public void setUp() {
        driver = Driver.get();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        action = new Actions(driver);
        driver.manage().window().maximize();

    }

    @AfterMethod
    public void tearDown(ITestResult result) throws InterruptedException, IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            extentLogger.fail(result.getName());
            String screenShotPath = BrowserUtils.getScreenshot(result.getName());
            extentLogger.addScreenCaptureFromPath(screenShotPath);
            extentLogger.fail(result.getThrowable());
        }
        Thread.sleep(2000);
        Driver.closeDriver();
    }


}