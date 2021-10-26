package com.example.practice.objects;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainPage {
    @FindBy(xpath = "//div[contains(@class, 'desk-notif-card__login-new-item-title')]")
    public WebElement enterButton;

    @FindBy(xpath = "//div/a[contains(@class, 'home-link desk-notif-card__domik-mail-line home-link_black_yes')]")
    private WebElement emailEntrance;

    @FindBy(xpath = "//span[contains(@class, 'username desk-notif-card__user-name')]")
    private WebElement userName;

    @Step
    public void assertLoggedIn(String property){
        assertEquals(property, userName.getAttribute("innerText"));
    }

    @Step
    public void openMail(WebDriver driver,WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(emailEntrance));
        final Set<String> oldWindowsSet = driver.getWindowHandles();
        emailEntrance.click();
        String newWindow = (new WebDriverWait(driver, 10))
                .until((ExpectedCondition<String>) driver1 -> {
                    Set<String> newWindowsSet = driver1.getWindowHandles();
                    newWindowsSet.removeAll(oldWindowsSet);
                    return newWindowsSet.size() > 0 ?
                            newWindowsSet.iterator().next() : null;
                }
                );

        driver.switchTo().window(newWindow);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
