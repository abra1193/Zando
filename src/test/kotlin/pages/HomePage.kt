package pages

import base.ScreenHandlers
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class HomePage(driver: WebDriver) : ScreenHandlers(driver) {
    @FindBy(id = "header-user-account-icon")
    private lateinit var profileIcon: WebElement

    @FindBy(id = "header-user-actions-container")
    private lateinit var shoppingCartIcon: WebElement



    fun tapOnProfileIcon() {
        waitForElementToBeVisible(profileIcon)
        profileIcon.click()
    }

    fun tapOnShoppingCartIcon(){
        waitForElementToBeVisible(shoppingCartIcon)
        shoppingCartIcon.click()
    }
}
