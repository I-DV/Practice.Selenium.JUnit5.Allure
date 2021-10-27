package com.example.practice.objects;

import com.example.practice.AbstractHandler;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.example.practice.ConfProperties.getProperty;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainPage extends AbstractHandler {
    private final WebDriver driver;

    @FindBy(xpath = "//div[contains(@class, 'desk-notif-card__login-new-item-title')]")
    public WebElement enterButton;
    @FindBy(xpath = "//div/a[contains(@class, 'home-link desk-notif-card__domik-mail-line home-link_black_yes')]")
    private WebElement emailEntrance;

    @FindBy(xpath = "//span[contains(@class, 'username desk-notif-card__user-name')]")
    private WebElement userName;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     *
     */
    @Step
    public MainPage assertLoggedIn(String property) {
        assertEquals(property, userName.getAttribute("innerText"));
        return this;
    }

    /**
     *
     */
    @Step
    public void openMail() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(emailEntrance));
        setOldWindowsSet(driver.getWindowHandles());
        emailEntrance.click();
    }

    /**
     *
     */
    private void loggedInn() {
        if (!isStatus()) {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOf(enterButton));
            enterButton.click();
            setStatus(true);
        } else {
            assertLoggedIn(getProperty("userLogin"))
                    .openMail();
        }
    }

    /**
     *
     */
    @Override
    public boolean testStep() {
        loggedInn();
        return checkNext();
    }
}
