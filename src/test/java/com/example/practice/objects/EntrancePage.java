package com.example.practice.objects;

import com.example.practice.AbstractHandler;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.example.practice.ConfProperties.getProperty;

public class EntrancePage extends AbstractHandler {

    @FindBy(id = "passp-field-login")
    private WebElement loginField;

    @FindBy(id = "passp:sign-in")
    private WebElement nextButton;

    /**
     *
     */
    public EntrancePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    /**
     *
     */
    @Step
    public void enterLogin(String userLogin) {
        loginField.sendKeys(userLogin);
        nextButton.click();
    }

    /**
     *
     */
    @Override
    public boolean testStep() {
        enterLogin(getProperty("userLogin"));
        return checkNext();
    }
}
