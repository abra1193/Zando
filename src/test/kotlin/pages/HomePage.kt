package pages

import base.ScreenHandlers
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class HomePage(driver: WebDriver) : ScreenHandlers(driver) {
    @FindBy(id = "header-user-account-icon1")
    private lateinit var profileIcon: WebElement

    fun tapOnProfileIcon() {
        waitForElementToBeVisible(profileIcon)
        profileIcon.click()
    }
}
