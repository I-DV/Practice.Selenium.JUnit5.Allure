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
    private final WebDriver driver;

    @FindBy(id = "passp-field-passwd")
    private WebElement passwordField;

    @FindBy(className = "CurrentAccount-displayName")
    private WebElement profileName;

    @FindBy(id = "passp:sign-in")
    private WebElement nextButton;

    public PasswordPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * input user password
     *
     * @return creates a page and passes the driver to it
     */
    @Step
    public MainPage enterPassword() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(profileName));
        passwordField.sendKeys(getProperty("userPassword"));
        nextButton.click();
        return new MainPage(driver);
    }
}
