package com.example.practice.objects;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.example.practice.ConfProperties.getProperty;

public class PasswordPage {
    @FindBy(xpath = "//div/input[contains(@class, 'whsOnd zHQkBf')]")
    public WebElement passwordField;

    @FindBy(id = "profileIdentifier")
    public WebElement profileName;

    @FindBy(className = "VfPpkd-vQzf8d")
    public WebElement nextButton;

    @Step
    public void enterPassword(WebDriver driver){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(profileName));
        passwordField.sendKeys(getProperty("userPassword"));
        nextButton.click();
    }

    public PasswordPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
