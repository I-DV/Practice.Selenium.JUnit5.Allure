package com.example.practice.objects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

/**
 *
 */
public class EmailPage extends AbstractPage {
	private static int count;
	private static int oldCount;

	@FindBy(xpath = "//span[contains(@class, 'mail-NestedList-Item-Name')]")
	private WebElement mailLogo;
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
	@FindBy(xpath = "//div[1]/div[1][contains(@placeholder, 'Напишите что-нибудь')]")
	private WebElement messageTyping;
	@FindBy(css = "button.Button2_view_default")
	private WebElement sendButton;
	@FindBy(xpath = "//div[contains(@class, 'ComposeDoneScreen-Title')][contains(., 'Письмо отправлено')]")
	private WebElement confirmSend;
	@FindBy(xpath = "//div/button[3]")
	private WebElement folders;
	@FindBy(xpath = "//div[1]/span[contains(@class, 'menu__text')]")
	private WebElement incoming;
	@FindBy(xpath = "//div[1]/a/span[contains(@class, 'mail-NestedList-Item-Name')]")
	private WebElement inboxMail;

	public EmailPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public static int getCount() {
		return count;
	}

	public static int getOldCount() {
		return oldCount;
	}

	/**
	 * count the number of email in the inbox with a title "Simbirsoft theme"
	 *
	 * @return return this page
	 */
	@Step
	public EmailPage countEmail(String theme) {
		oldCount = count;
		searchField.sendKeys(theme);
		buttonSearch.click();
		folders.click();
		incoming.click();
		setWait(driver, driver.findElement(By.cssSelector("span.mail-ui-Link")));
		count = driver.findElements(By.xpath("//span[contains(@title, '" + theme + "')]")).size();
		return this;
	}

	/**
	 * sends a letter to itself with the count of letter
	 *
	 * @param i         count of email
	 * @param userEmail transfer user Email
	 */
	@Step
	public EmailPage newEmailSend(int i, String userEmail, String theme) {
		String s = "Найдено " + i + (i == 1 ? " письмо" : ((i < 5) && (i != 0) ? " письма" : " писем"));
		newEmail.click();
		setWait(driver, emailAdressTyping);
		emailAdressTyping.sendKeys(userEmail);
		titleTyping.click();
		titleTyping.sendKeys(theme);
		messageTyping.click();
		messageTyping.sendKeys(s);
		sendButton.click();
		setWait(driver, confirmSend);
		return this;
	}

	@Step
	public EmailPage openMail() {
		String newWindow = (new WebDriverWait(driver, 10))
				.until((ExpectedCondition<String>) driver -> {
							Set<String> newWindowsSet = driver.getWindowHandles();
							newWindowsSet.removeAll(getOldWindowsSet());
							return newWindowsSet.size() > 0 ?
									newWindowsSet.iterator().next() : null;
						}
				);

		driver.switchTo().window(newWindow);
		setWait(driver, mailLogo);
		return this;
	}

	@Step
	public EmailPage newEmailCount(String theme) {
		try {
			inboxMail.click();
			Thread.sleep(5000);
			countEmail(theme);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return this;
	}
}
