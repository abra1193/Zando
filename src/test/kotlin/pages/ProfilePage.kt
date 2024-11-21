package pages

import base.ScreenHandler
import org.openqa.selenium.WebDriver

class ProfilePage(driver: WebDriver) : ScreenHandler(driver) {
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
        findElement(LocatorType.XPATH, "//*[@data-testid='register-button']")
    }

    private val continueWithGoogleButton: ElementWrapper by lazy {
        findElement(LocatorType.XPATH, "//span[normalize-space()=\"Continue with Google\"]")
    }

    private val continueWithFacebookButton: ElementWrapper by lazy {
        findElement(LocatorType.XPATH, "//span[normalize-space()=\"Continue with Facebook\"]")
    }

    private val continueWithAppleButton: ElementWrapper by lazy {
        findElement(LocatorType.XPATH, "//span[normalize-space()=\"Continue with Apple\"]")
    }

    private val signOrRegisterText: ElementWrapper by lazy {
        findElement(LocatorType.XPATH, "//h1[normalize-space()=\"Sign in or register\"]")
    }

    fun isProfilePageDisplayedCorrectly(): Boolean {
        waitForElementToBeVisible(signOrRegisterText)

        waitForElementToBeClickable(emailField)

        waitForElementToBeClickable(continueButton)

        waitForElementToBeClickable(continueWithGoogleButton)

        waitForElementToBeClickable(continueWithAppleButton)

        waitForElementToBeClickable(continueWithFacebookButton)

        return signOrRegisterText.isDisplayed() && emailField.isDisplayed() && continueButton.isDisplayed() && continueWithGoogleButton.isDisplayed() && continueWithAppleButton.isDisplayed() && continueWithFacebookButton.isDisplayed()
    }
}
