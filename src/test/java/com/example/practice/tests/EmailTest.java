package com.example.practice.tests;

import com.example.practice.objects.EmailPage;
import com.example.practice.objects.EntrancePage;
import com.example.practice.objects.MainPage;
import com.example.practice.objects.PasswordPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static com.example.practice.ConfProperties.getProperty;

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
        entrancePage.enterLogin(mainPage.enterButton,getProperty("userLogin"));
        passwordPage.enterPassword(driver);
        mainPage.assertLoggedIn(getProperty("userEmail"));
        mainPage.openMail(driver, emailPage.mailLogo);
        emailPage.newEmailSend(emailPage.countEmail(driver), driver,getProperty("userEmail"));

    }
}
