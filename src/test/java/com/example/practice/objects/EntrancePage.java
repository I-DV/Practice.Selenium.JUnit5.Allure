package com.example.practice.objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EntrancePage {

    @FindBy(id = "identifierId")
    public WebElement loginField;

    @FindBy(className = "VfPpkd-vQzf8d")
    public WebElement nextButton;

    public EntrancePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
