package com.example.practice.tests;

import com.example.practice.objects.EmailPage;
import com.example.practice.objects.MainPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
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

	/**
	 * starting driver setup
	 *
	 * @throws MalformedURLException possible exception
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

		mainPage = new MainPage(driver);

	}

	/**
	 * shutting down the driver
	 */
	@AfterEach
	public void tearDown() {
		driver.quit();
	}

	/**
	 * autorization,receiving the number of messages, sending a letter to itself with the count of email
	 */
	@Test
	public void checkmail() {
		mainPage.loggedIn()
				.enterLogin(getProperty("userLogin"))
				.enterPassword()
				.assertLoggedIn(getProperty("userLogin"))
				.openMail()
				.openMail()
				.countEmail(getProperty("theme"))
				.newEmailSend(EmailPage.getCount(), getProperty("userEmail"), getProperty("theme"))
				.newEmailCount(getProperty("theme"));
		Assertions.assertEquals(EmailPage.getOldCount() + 1, EmailPage.getCount());
	}
}
