package com.example.practice.objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

public abstract class AbstractPage {
	private static Set<String> oldWindowsSet;

	protected static WebDriver driver;

	protected static Set<String> getOldWindowsSet() {
		return oldWindowsSet;
	}

	protected static void setOldWindowsSet(Set<String> oldWindowsSet) {
		AbstractPage.oldWindowsSet = oldWindowsSet;
	}

	public static void setWait(WebDriver driver, WebElement property) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(property));
	}
}
