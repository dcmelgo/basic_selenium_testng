package com.djm.basic.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Parameters;

import com.djm.basic.utils.ConfigReader;
import com.djm.basic.utils.DriverFactory;

public class BaseTest {

    public static WebDriver driver;
    public static ConfigReader configReader = new ConfigReader();
    String browser = ConfigReader.getPropertyValue("browser");
    String url = ConfigReader.getPropertyValue("url");

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver(browser);
        driver.get(url);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}