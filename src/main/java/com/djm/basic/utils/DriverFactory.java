package com.djm.basic.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver(String browser) {
        if (driver.get() == null) {
            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.setCapability("ignore-certificate-errors", true);
                    chromeOptions.setCapability("acceptInsecureCerts", true);
                    chromeOptions.setCapability("unexpectedAlertBehaviour", "ignore");
                    driver.set(new ChromeDriver(chromeOptions));
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.setCapability("acceptInsecureCerts", true);
                    firefoxOptions.setCapability("unexpectedAlertBehaviour", "ignore");
                    driver.set(new FirefoxDriver(firefoxOptions));
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.setCapability("acceptInsecureCerts", true);
                    edgeOptions.setCapability("unexpectedAlertBehaviour", "ignore");
                    driver.set(new EdgeDriver(edgeOptions));
                    break;
                case "remote":
                    try {
                        ChromeOptions remoteChromeOptions = new ChromeOptions();
                        remoteChromeOptions.setCapability("ignore-certificate-errors", true);
                        remoteChromeOptions.setCapability("acceptInsecureCerts", true);
                        remoteChromeOptions.setCapability("unexpectedAlertBehaviour", "ignore");
                        driver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), remoteChromeOptions));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Invalid browser name provided");
            }
        }
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.set(null);
        }
    }
}
