package com.example.practice.objects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 */
public class EmailPage {

    @FindBy(xpath = "//div/a/img[contains(@class, 'gb_tc')]")
    public WebElement mailLogo;

    @FindBy(xpath = "//div/input[contains(@class, 'gb_8e')]")
    public WebElement searchField;

    @FindBy(xpath = "//button[contains(@class, 'gb_hf gb_if')]")
    public WebElement buttonSearch;

    @FindBy(xpath = "//div[contains(@class, 'T-I T-I-KE L3')]")
    public WebElement newEmail;

    @FindBy(name = "to")
    public WebElement emailAdressTyping;

    @FindBy(name = "subjectbox")
    public WebElement titleTyping;

    @FindBy(xpath = "//div[contains(@class, 'Am Al editable LW-avf tS-tW')]")
    public WebElement messageTyping;

    @FindBy(xpath = "//div[contains(@class, 'T-I J-J5-Ji aoO v7 T-I-atl L3')]")
    public WebElement sendButton;

    @FindBy(xpath = "//div/span/span[contains(@class, 'bAq')][contains(., 'Письмо отправлено.')]")
    public WebElement confirmSend;

    @Step
    public int countEmail(WebDriver driver){
        searchField.sendKeys("Simbirsoft Тестовое задание");
        buttonSearch.click();
        return driver.findElements(By.xpath("//span[contains(@class, 'bog')]/span[contains(@class,'bqe') and text() = 'Simbirsoft Тестовое задание']")).size();
    }
    @Step
    public void newEmailSend(int i, WebDriver driver,String userEmail){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        newEmail.click();
        wait.until(ExpectedConditions.visibilityOf(emailAdressTyping));
        emailAdressTyping.sendKeys(userEmail);
        titleTyping.sendKeys("Simbirsoft Тестовое задание.Идрисов");
        messageTyping.sendKeys(String.valueOf(i));
        sendButton.click();
        wait.until(ExpectedConditions.visibilityOf(confirmSend));
    }

    public EmailPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
