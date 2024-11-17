package pages

import base.ScreenHandlers
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class HomePage(driver: WebDriver) : ScreenHandlers(driver) {
    init {
        waitForElementToBeVisible(profileButton)
        cookiesBanner.shadowRoot.findElement(By.cssSelector(".sc-dcJsrY.kXOIji")).click()
    }

    @FindBy(css = "#usercentrics-root")
    private lateinit var cookiesBanner: WebElement

    @FindBy(id = "header-user-account-icon")
    private lateinit var profileButton: WebElement

    @FindBy(id = "header-user-actions-container")
    private lateinit var shoppingCartButton: WebElement

    @FindBy(id = "header-search-input")
    private lateinit var searchBarButton: WebElement

    fun tapOnProfileButton() {
        waitForElementToBeVisible(profileButton)
        profileButton.click()
    }

    fun tapOnShoppingCartButton() {
        waitForElementToBeVisible(shoppingCartButton)
        shoppingCartButton.click()
    }

    fun tapOnSearchButton() {
        waitForElementToBeVisible(searchBarButton)
        searchBarButton.click()
    }
}
