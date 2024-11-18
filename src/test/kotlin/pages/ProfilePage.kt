package pages

import base.ScreenHandlers
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import utils.RandomGenerators.generateNumericString
import utils.RandomGenerators.generateRandomEmail
import utils.RandomGenerators.generateString
import utils.TimeOuts.TIMEOUT_10_SECONDS

class ProfilePage(driver: WebDriver) : ScreenHandlers(driver) {
    private val emailField: WebElement by lazy {
        findElement(LocatorType.ID, "lookup-email").webElement
    }

    private val continueButton: WebElement by lazy {
        findElement(LocatorType.ID, "//*[@data-testid='verify-email-button']").webElement
    }

    private val passwordField: WebElement by lazy {
        findElement(LocatorType.ID, "register-password").webElement
    }

    private val firstNameField: WebElement by lazy {
        findElement(LocatorType.ID, "first_name").webElement
    }

    private val lastNameField: WebElement by lazy {
        findElement(LocatorType.ID, "last_name").webElement
    }

    private val registerButton: WebElement by lazy {
        findElement(LocatorType.ID, "//*[@data-testid='register-button']").webElement
    }

    fun registerNewProfile() {
        waitForElementToBeVisible(emailField)
        emailField.sendKeys(generateRandomEmail())

        waitForElementToBeVisible(continueButton)
        continueButton.click()

        waitForElementToBeVisible(passwordField, TIMEOUT_10_SECONDS)
        passwordField.sendKeys(generateNumericString(10))

        waitForElementToBeVisible(firstNameField)
        firstNameField.sendKeys(generateString(5))

        waitForElementToBeVisible(lastNameField)
        lastNameField.sendKeys(generateString(5))
    }
}
