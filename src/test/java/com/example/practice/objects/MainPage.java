package com.example.practice.objects;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainPage extends AbstractPage {

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
	 * opening an autorization form
	 *
	 * @return creates a page and passes the driver to it
	 */
	@Step
	public EntrancePage loggedIn() {
		setWait(driver, enterButton);
		enterButton.click();
		return new EntrancePage(driver);
	}

	/**
	 * autorization check
	 *
	 * @param userLogin transfer user login
	 * @return return this page
	 */
	@Step
	public MainPage assertLoggedIn(String userLogin) {
		assertEquals(userLogin, userName.getAttribute("innerText"));
		return this;
	}

	/**
	 * openin email page
	 *
	 * @return creates a page and passes the driver to it
	 */
	@Step
	public EmailPage openMail() {
		setWait(driver, emailEntrance);
		setOldWindowsSet(driver.getWindowHandles());
		emailEntrance.click();
		return new EmailPage(driver);
	}

}
