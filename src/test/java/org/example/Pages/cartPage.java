package org.example.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class cartPage {

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

    public class CartPage {
        WebDriver driver;

        @FindBy(xpath = "//input[@name='termsofservice']")
        WebElement checkboxTerms;

        @FindBy(xpath = "//button[@id='checkout']")
        WebElement checkoutButton;

        public CartPage(WebDriver driver) {
            this.driver = driver;
            PageFactory.initElements(driver, this);
        }

        public void clickTerms() {
            if (!checkboxTerms.isSelected()) {
                checkboxTerms.click();
            }
        }

        public void proceedToCheckout() {
            checkoutButton.click();
        }
    }

}
