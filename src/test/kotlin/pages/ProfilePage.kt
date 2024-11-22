package pages

import base.ScreenHandler
import org.openqa.selenium.WebDriver
import utils.TimeOuts.TIMEOUT_5_SECONDS

class ProfilePage(driver: WebDriver) : ScreenHandler(driver) {
    private val emailField: ElementWrapper by lazy {
        findElement(LocatorType.ID, "lookup-email")
    }

    private val continueButton: ElementWrapper by lazy {
        findElement(LocatorType.XPATH, "//*[@data-testid='verify-email-button']")
    }

    private val xpath = "//span[normalize-space()='%s']"

    private val continueWithGoogleButton: ElementWrapper by lazy {
        findElement(LocatorType.XPATH, xpath.format("Continue with Google"))
    }

    private val continueWithFacebookButton: ElementWrapper by lazy {
        findElement(LocatorType.XPATH, xpath.format("Continue with Facebook"))
    }

    private val continueWithAppleButton: ElementWrapper by lazy {
        findElement(LocatorType.XPATH, xpath.format("Continue with Apple"))
    }

    private val signOrRegisterText: ElementWrapper by lazy {
        findElement(LocatorType.XPATH, "//h1[normalize-space()=\"Sign in or register\"]")
    }

    fun isProfilePageDisplayedCorrectly(): Boolean {
        waitForElementToBeVisible(signOrRegisterText)

        val elementsToCheck = listOf(
            emailField,
            continueButton,
            continueWithGoogleButton,
            continueWithFacebookButton,
            continueWithAppleButton
        )

        elementsToCheck.forEach { waitForElementToBeClickable(it, TIMEOUT_5_SECONDS) }

        return signOrRegisterText.isDisplayed() &&
            elementsToCheck.all { it.isDisplayed() }
    }
}
