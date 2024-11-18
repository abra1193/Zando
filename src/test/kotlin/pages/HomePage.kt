package pages

import base.ScreenHandlers
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import utils.TimeOuts.TIMEOUT_10_SECONDS

private const val OK_BUTTON_CSS_SELECTOR = ".sc-dcJsrY.kXOIji"

class HomePage(driver: WebDriver) : ScreenHandlers(driver) {
    private val cookiesBanner: ElementWrapper by lazy {
        findElement(LocatorType.CSS, "#usercentrics-root")
    }

    private val profileButton: ElementWrapper by lazy {
        findElement(LocatorType.ID, "header-user-account-icon")
    }

    private val shoppingCartButton: ElementWrapper by lazy {
        findElement(LocatorType.XPATH, "//*[@data-testid=\"cart-link\"]")
    }

    private val searchBarButton: ElementWrapper by lazy {
        findElement(LocatorType.ID, "header-search-input")
    }

    init {
        waitForElementToBeVisible(profileButton, TIMEOUT_10_SECONDS)
        cookiesBanner.webElement.shadowRoot.findElement(By.cssSelector(OK_BUTTON_CSS_SELECTOR)).click()
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
