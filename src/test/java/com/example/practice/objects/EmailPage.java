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

    @FindBy(xpath = "//span[contains(@class, 'mail-NestedList-Item-Name')]")
    public WebElement mailLogo;

    @FindBy(xpath = "//input[contains(@class, 'textinput__control')]")
    private WebElement searchField;

    @FindBy(css = ".button2_pin_clear-round")
    private WebElement buttonSearch;

    @FindBy(xpath = "//div/a[contains(@class, 'mail-ComposeButton js-main-action-compose')]")
    private WebElement newEmail;

    @FindBy(className = "composeYabbles")
    private WebElement emailAdressTyping;

    @FindBy(xpath = "//input[contains(@class, 'composeTextField ComposeSubject-TextField')]")
    private WebElement titleTyping;

    @FindBy(xpath= "//div[1]/div[1][contains(@placeholder, 'Напишите что-нибудь')]")
    private WebElement messageTyping;

    @FindBy(css = "button.Button2_view_default")
    private WebElement sendButton;

    @FindBy(xpath = "//div[contains(@class, 'ComposeDoneScreen-Title')][contains(., 'Письмо отправлено')]")
    private WebElement confirmSend;

    @FindBy(xpath = "//div/button[3]")
    private WebElement folders;

    @FindBy(xpath = "//div[1]/span[contains(@class, 'menu__text')]")
    private WebElement incoming;

    @Step
    public int countEmail(WebDriver driver){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        searchField.sendKeys("Simbirsoft theme");
        buttonSearch.click();
        folders.click();
        incoming.click();
        wait.until(ExpectedConditions
                .visibilityOfElementLocated(By
                        .cssSelector("span.mail-ui-Link")));
        return driver.findElements(By.xpath("//span[contains(@title, 'Simbirsoft theme')]")).size();
    }
    @Step
    public void newEmailSend(int i, WebDriver driver,String userEmail){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        String s = "Найдено "+ i + ( i==1 ? " письмо" : ((i<5)&&(i!=0) ? " письма" : " писем"));
        newEmail.click();
        wait.until(ExpectedConditions.visibilityOf(emailAdressTyping));
        emailAdressTyping.sendKeys(userEmail);
        titleTyping.click();
        titleTyping.sendKeys("Simbirsoft theme");
        messageTyping.click();
        messageTyping.sendKeys(s);
        sendButton.click();
        wait.until(ExpectedConditions.visibilityOf(confirmSend));
    }

    public EmailPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
