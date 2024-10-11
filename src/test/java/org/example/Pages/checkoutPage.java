package org.example.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class checkoutPage {
        WebDriver driver;

        @FindBy(id = "BillingNewAddress_FirstName")
        WebElement firstNameField;

        @FindBy(id = "BillingNewAddress_LastName")
        WebElement lastNameField;

        @FindBy(id = "BillingNewAddress_Email")
        WebElement emailField;

        @FindBy(id = "BillingNewAddress_CountryId")
        WebElement countryDropdown;

        @FindBy(id = "BillingNewAddress_StateProvinceId")
        WebElement stateDropdown;

        @FindBy(id = "BillingNewAddress_City")
        WebElement cityField;

        @FindBy(id = "BillingNewAddress_Address1")
        WebElement addressField;

        @FindBy(id = "BillingNewAddress_ZipPostalCode")
        WebElement zipCodeField;

        @FindBy(id = "BillingNewAddress_PhoneNumber")
        WebElement phoneNumberField;

        @FindBy(css = "input.button-1.shipping-method-next-step-button")
        WebElement continueButton;

        @FindBy(id = "shippingoption_0")
        WebElement groundShipping;

        @FindBy(id = "shippingoption_1")
        WebElement nextDayAir;

        @FindBy(id = "shippingoption_2")
        WebElement secondDayAir;

        @FindBy(id = "paymentmethod_1")
        WebElement creditCardPayment;

        @FindBy(id = "paymentmethod_0")
        WebElement checkMoneyOrderPayment;

        @FindBy(xpath = "//button[@class='button-1 payment-method-next-step-button']")
        WebElement paymentContinueButton;

        @FindBy(xpath = "//button[@class='button-1 confirm-order-next-step-button']")
        WebElement confirmOrderButton;

        @FindBy(xpath = "//div[@class='title']/strong")
        WebElement orderConfirmationMessage;

        public CheckoutPage(WebDriver driver) {
            this.driver = driver;
            PageFactory.initElements(driver, this);
        }

        public void fillBillingDetails(String firstName, String lastName, String email, String country, String state, String city, String address, String zipCode, String phoneNumber) {
            firstNameField.sendKeys(firstName);
            lastNameField.sendKeys(lastName);
            emailField.sendKeys(email);
            new Select(countryDropdown).selectByVisibleText(country);
            new Select(stateDropdown).selectByVisibleText(state);
            cityField.sendKeys(city);
            addressField.sendKeys(address);
            zipCodeField.sendKeys(zipCode);
            phoneNumberField.sendKeys(phoneNumber);
            continueButton.click();
        }

        public void selectShippingMethod(String method) {
            switch (method.toLowerCase()) {
                case "ground":
                    groundShipping.click();
                    break;
                case "next day air":
                    nextDayAir.click();
                    break;
                case "2nd day air":
                    secondDayAir.click();
                    break;
                default:
                    throw new IllegalArgumentException("Invalid shipping method: " + method);
            }
            continueButton.click();
        }

        public void selectPaymentMethod(String method) {
            switch (method.toLowerCase()) {
                case "credit card":
                    creditCardPayment.click();
                    break;
                case "check/money order":
                    checkMoneyOrderPayment.click();
                    break;
                default:
                    throw new IllegalArgumentException("Invalid payment method: " + method);
            }
            continueButton.click();
        }

        public void confirmOrder() {
            paymentContinueButton.click();
            confirmOrderButton.click();
        }

        public boolean isOrderConfirmed() {
            return orderConfirmationMessage.getText().contains("Your order has been successfully processed!");
        }
    }

}
