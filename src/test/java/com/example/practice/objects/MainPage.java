package com.example.practice.objects;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainPage {
    @FindBy(css = "a.gb_3")
    public WebElement enterButton;

    @FindBy(xpath = "//div/a[contains(@class, 'gb_f')]")
    public WebElement emailEntrance;

    @FindBy(xpath = "//div[contains(@class, 'gb_nb')]")
    public WebElement userName;

    @Step
    public void assertLoggedIn(String property){
        assertEquals(property, userName.getAttribute("innerText"));
    }

    @Step
    public void openMail(WebDriver driver,WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        emailEntrance.click();
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
