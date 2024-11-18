package pages

import base.ScreenHandlers
import org.openqa.selenium.WebDriver
import utils.RandomGenerators.generateNumericString
import utils.RandomGenerators.generateRandomEmail
import utils.RandomGenerators.generateString

class ProfilePage(driver: WebDriver) : ScreenHandlers(driver) {
    private val emailField: ElementWrapper by lazy {
        findElement(LocatorType.ID, "lookup-email")
    }

    private val continueButton: ElementWrapper by lazy {
        findElement(LocatorType.XPATH, "//*[@data-testid='verify-email-button']")
    }

    private val passwordField: ElementWrapper by lazy {
        findElement(LocatorType.ID, "register-password")
    }

    private val firstNameField: ElementWrapper by lazy {
        findElement(LocatorType.ID, "first_name")
    }

    private val lastNameField: ElementWrapper by lazy {
        findElement(LocatorType.ID, "last_name")
    }

    private val registerButton: ElementWrapper by lazy {
        findElement(LocatorType.ID, "//*[@data-testid='register-button']")
    }

    fun registerNewProfile() {
        waitForElementToBeVisible(emailField)
        emailField.webElement.sendKeys(generateRandomEmail())

        waitForElementToBeVisible(continueButton)
        continueButton.click()

        waitForElementToBeVisible(passwordField)
        passwordField.sendKeys(generateNumericString(10))

        waitForElementToBeVisible(firstNameField)
        firstNameField.sendKeys(generateString(5))

        waitForElementToBeVisible(lastNameField)
        lastNameField.sendKeys(generateString(5))
    }
}
