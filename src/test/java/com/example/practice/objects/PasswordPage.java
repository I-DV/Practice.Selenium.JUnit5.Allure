package com.example.practice.objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PasswordPage {
    @FindBy(xpath = "//div/input[contains(@class, 'whsOnd zHQkBf')]")
    public WebElement passwordField;

    @FindBy(id = "profileIdentifier")
    public WebElement profileName;

    @FindBy(className = "VfPpkd-vQzf8d")
    public WebElement nextButton;

    public PasswordPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
