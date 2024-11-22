package pages

import base.ScreenHandler
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

private const val OK_BUTTON_CSS_SELECTOR = ".sc-dcJsrY.kXOIji"

class HomePage(driver: WebDriver) : ScreenHandler(driver) {
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
        try {
            Thread.sleep(3000) // Cookie banner will take some time to load
            // since it's one of the first element to load in the home page
            cookiesBanner.webElement.shadowRoot.findElement(By.cssSelector(OK_BUTTON_CSS_SELECTOR)).click()
        } catch (e: Exception) {
            println("Cookie banner not found or couldn't be clicked: ${e.message}")
        }
    }

    fun tapOnProfileButton() {
        waitForElementToBeClickable(profileButton)
        profileButton.click()
    }

    fun tapOnShoppingCartButton() {
        waitForElementToBeClickable(shoppingCartButton)
        shoppingCartButton.click()
    }

    fun tapOnSearchButton() {
        waitForElementToBeClickable(searchBarButton)
        searchBarButton.click()
    }
}
