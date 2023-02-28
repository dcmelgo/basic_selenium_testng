package com.djm.basic.pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public abstract class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickElement(SelenideElement ele) {
        try {
            SelenideElement element = $(ele);
            element.shouldBe(visible).click();
        } catch (Exception e) {
            System.out.println("Error clicking on element: " + e.getMessage());
        }
    }

    public void enterText(SelenideElement ele, String text) {
        try {
            SelenideElement element = $(ele);
            element.shouldBe(visible).sendKeys(text);
        } catch (Exception e) {
            System.out.println("Error entering text: " + e.getMessage());
        }
    }

    public void enterTextAndPressEnter(By locator, String text) {
        try {
            SelenideElement element = $(locator);
            element.shouldBe(visible).sendKeys(text, Keys.ENTER);
        } catch (Exception e) {
            System.out.println("Error entering text and pressing Enter: " + e.getMessage());
        }
    }

    public boolean isElementVisible(By locator) {
        try {
            SelenideElement element = $(locator);
            return element.isDisplayed();
        } catch (Exception e) {
            System.out.println("Error checking if element is visible: " + e.getMessage());
            return false;
        }
    }
     
}
