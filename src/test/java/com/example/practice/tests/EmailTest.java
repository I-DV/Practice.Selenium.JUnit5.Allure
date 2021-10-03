package com.example.practice.tests;


import com.example.practice.objects.EmailPage;
import com.example.practice.objects.EntrancePage;
import com.example.practice.objects.MainPage;
import com.example.practice.objects.PasswordPage;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static com.example.practice.ConfProperties.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmailTest {
    private WebDriver driver;
    private MainPage mainPage;
    private EntrancePage entrancePage;
    private PasswordPage passwordPage;
    private EmailPage emailPage;


    /**
     *
     */
    @BeforeEach
    public void setUp() throws MalformedURLException {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setBrowserName("firefox");
         System.setProperty("webdriver.gecko.driver", getProperty("firefoxdriver"));
         System.setProperty("webdriver.chrome.driver", getProperty("chromedriver"));
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("https://www.google.com/");


        mainPage = new MainPage(driver);
        entrancePage = new EntrancePage(driver);
        passwordPage = new PasswordPage(driver);
        emailPage = new EmailPage(driver);
    }

    /**
     *
     */
    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    /**
     *
     */
    @Test
    public void checkmail() {
        enterLogin();
        enterPassword();
        assertLoggedIn();
        openMail();
        newEmailSend(countEmail());

    }
    @Step
    public void enterLogin(){

        mainPage.enterButton.click();
        entrancePage.loginField.sendKeys(getProperty("userLogin"));
        entrancePage.nextButton.click();

    }
    @Step
    public void enterPassword(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(passwordPage.profileName));
        passwordPage.passwordField.sendKeys(getProperty("userPassword"));
        passwordPage.nextButton.click();
    }
    @Step
    public void assertLoggedIn(){
        assertEquals(getProperty("userEmail"), mainPage.userName.getAttribute("innerText"));
    }
    @Step
    public void openMail(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        mainPage.emailEntrance.click();
        wait.until(ExpectedConditions.visibilityOf(emailPage.mailLogo));
    }
    @Step
    public int countEmail(){
        emailPage.searchField.sendKeys("Simbirsoft Тестовое задание");
        emailPage.buttonSearch.click();
        return driver.findElements(By.xpath("//span[contains(@class, 'bog')]/span[contains(@class,'bqe') and text() = 'Simbirsoft Тестовое задание']")).size();
    }
    @Step
    public void newEmailSend(int i){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        emailPage.newEmail.click();
        wait.until(ExpectedConditions.visibilityOf(emailPage.emailAdressTyping));
        emailPage.emailAdressTyping.sendKeys(getProperty("userEmail"));
        emailPage.titleTyping.sendKeys("Simbirsoft Тестовое задание.Идрисов");
        emailPage.messageTyping.sendKeys(String.valueOf(i));
        emailPage.sendButton.click();
        wait.until(ExpectedConditions.visibilityOf(emailPage.confirmSend));
    }
}
