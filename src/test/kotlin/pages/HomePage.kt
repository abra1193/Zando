package pages

import base.ScreensHandler
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class HomePage(driver: WebDriver) : ScreensHandler(driver) {
    @FindBy(id = "header-user-account-icon")
    private val profileIcon: WebElement? = null

    fun tapOnProfileIcon() {
        profileIcon!!.click()
    }
}
