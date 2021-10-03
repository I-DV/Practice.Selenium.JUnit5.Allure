package com.example.practice.objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {
    @FindBy(css = "a.gb_3")
    public WebElement enterButton;

    @FindBy(xpath = "//div/a[contains(@class, 'gb_f')]")
    public WebElement emailEntrance;

    //@FindBy(xpath = "//div[contains(@class, 'gb_Ca gbii')]")
    //public WebElement userMenu;

    @FindBy(xpath = "//div[contains(@class, 'gb_nb')]")
    public WebElement userName;

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
