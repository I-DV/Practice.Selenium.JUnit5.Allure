package com.example.practice.objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    //@FindBy(xpath = "//span[contains(@class, 'bog')]/span[contains(@class,'bqe') and text() = 'Simbirsoft Тестовое задание']")
    //public WebElement searchEmail;

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

    //@FindBy(xpath = "//*[@id=\":6n\"]")
    //public WebElement titleEmail;

    @FindBy(xpath = "//div/span/span[contains(@class, 'bAq')][contains(., 'Письмо отправлено.')]")
    public WebElement confirmSend;

    public EmailPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
