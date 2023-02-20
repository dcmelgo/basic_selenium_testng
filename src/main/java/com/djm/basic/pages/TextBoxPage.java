package com.djm.basic.pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TextBoxPage extends BasePage {

    private SelenideElement fullName = $(By.id("userName"));
    private SelenideElement email = $(By.id("userName"));
    private SelenideElement cAddress = $(By.id("userName"));
    private SelenideElement pAddress = $(By.id("userName"));
    private SelenideElement submit_btn = $(By.id("submit"));

    public TextBoxPage(WebDriver driver) {
        super(driver);
    }

    public void enter_full_name(String str) {
        enterText(fullName, str);
    }

    public void enter_email(String str) {
        enterText(email, str);
    }

    public void enter_cAddress(String str) {
        enterText(cAddress, str);
    }

    public void enter_pAddress(String str) {
        enterText(pAddress, str);
    }

    public void clickSubmit() {
        clickElement(submit_btn);
    }
}
