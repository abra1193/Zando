package pages

import base.ScreenHandlers
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import utils.TimeOuts.TIMEOUT_10_SECONDS

private const val OK_BUTTON_CSS_SELECTOR = ".sc-dcJsrY.kXOIji"

class HomePage(driver: WebDriver) : ScreenHandlers(driver) {
    private val cookiesBanner: WebElement by lazy {
        findElement(LocatorType.CSS, "#usercentrics-root").webElement
    }

    private val profileButton: WebElement by lazy {
        findElement(LocatorType.ID, "header-user-account-icon").webElement
    }

    private val shoppingCartButton: WebElement by lazy {
        findElement(LocatorType.ID, "header-search-input").webElement
    }

    private val searchBarButton: WebElement by lazy {
        findElement(LocatorType.ID, "header-search-input").webElement
    }

    init {
        waitForElementToBeVisible(profileButton, TIMEOUT_10_SECONDS)
        cookiesBanner.shadowRoot.findElement(By.cssSelector(OK_BUTTON_CSS_SELECTOR)).click()
    }

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
