package com.example.practice.tests;

import com.example.practice.AbstractHandler;
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
    private AbstractHandler handler;


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
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.yandex.ru/");

        AbstractHandler handler= new MainPage(driver);
        handler.link(new EntrancePage(driver))
                .link(new PasswordPage(driver))
                .link(new MainPage(driver))
                .link(new EmailPage(driver));
        setHandler(handler);
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
        handler.testStep();
    }

    /**
     *
     */
    public void setHandler(AbstractHandler handler) {
        this.handler = handler;
    }
}
