package pages

import base.ScreensHandler
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class HomePage(driver: WebDriver) : ScreensHandler(driver) {
    @FindBy(id = "header-user-account-icon")
    private lateinit var profileIcon: WebElement

    fun tapOnProfileIcon() {
        waitForElementToBeVisible(profileIcon)
        profileIcon.click()
    }
}
