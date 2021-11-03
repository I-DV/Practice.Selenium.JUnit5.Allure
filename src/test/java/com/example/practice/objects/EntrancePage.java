package com.example.practice.objects;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EntrancePage extends AbstractPage {

	@FindBy(id = "passp-field-login")
	private WebElement loginField;
	@FindBy(id = "passp:sign-in")
	private WebElement nextButton;


	public EntrancePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * input user login
	 */
	@Step
	public PasswordPage enterLogin(String userLogin) {
		loginField.sendKeys(userLogin);
		nextButton.click();
		return new PasswordPage(driver);
	}
}
