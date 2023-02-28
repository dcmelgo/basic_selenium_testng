package com.djm.basic.pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;

import org.openqa.selenium.WebDriver;

import com.codeborne.selenide.ElementsCollection;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.text;


public class HomePage extends BasePage{
    
    private ElementsCollection menuLinks = $$(".category-cards .card.mt-4.top-card");

    public HomePage(WebDriver driver){
        super(driver);
    }
    
    public HomePage clickLinkByText(String linkText) {
        try {
            SelenideElement link = menuLinks.findBy(text(linkText)).shouldBe(visible);
            link.click();
        } catch (ElementNotFound e) {      
            System.err.println("Could not find link with text: " + linkText);
        }
        return this;
    }
    
    
}
