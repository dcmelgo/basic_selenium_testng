package com.djm.basic.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;

import com.djm.basic.utils.ConfigReader;
import com.djm.basic.utils.DriverFactory;
import com.djm.basic.pages.HomePage;

public class BaseTest {

    public static WebDriver driver;
    public static ConfigReader configReader = new ConfigReader();
    String browser = ConfigReader.getPropertyValue("browser");
    String url = ConfigReader.getPropertyValue("url");
    HomePage hp = new HomePage(driver);
    
    @BeforeMethod
    public void setUp() {
        Configuration.browser = browser;
        Configuration.browserSize = "1920x1080";
        driver = DriverFactory.getDriver(browser);
        Selenide.open(url);
        hp.clickLinkByText("Elements");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
