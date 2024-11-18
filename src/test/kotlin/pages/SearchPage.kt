package pages

import base.ScreenHandler
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

class SearchPage(driver: WebDriver) : ScreenHandler(driver) {
    private val searchBar: ElementWrapper by lazy {
        findElement(LocatorType.ID, "header-search-input")
    }

    private val sortByButton: ElementWrapper by lazy {
        findElement(LocatorType.XPATH, "//*[@aria-label=\"Sort by\"]")
    }

    private val filterByPriceButton: ElementWrapper by lazy {
        findElement(LocatorType.XPATH, "//button[@aria-label=\"filter by Price\"]")
    }

    private val minimumPriceField: ElementWrapper by lazy {
        findElement(LocatorType.ID, "collection-view-desktop-filter-prices-minimum")
    }

    private val maximumPriceField: ElementWrapper by lazy {
        findElement(LocatorType.ID, "collection-view-desktop-filter-prices-maximum")
    }

    private val savePriceFilter: ElementWrapper by lazy {
        findElement(LocatorType.XPATH, "//*[@aria-label=\"Save the Price filter\"]")
    }

    private fun filterProduct(productName: String): SearchPage {
        searchBar.sendKeys(productName)
        selectRandomSearchOption()
        return SearchPage(driver)
    }

    fun selectProduct(productName: String): SearchPage {
        searchBar.sendKeys(productName)
        selectRandomSearchOption()
        return SearchPage(driver)
    }

    private fun selectRandomSearchOption() {
        val element =
            findElement(
                LocatorType.ID,
                "header-search-option-${(1..9).random()}",
            )
        waitForElementToBeVisible(element)
        element.click()
    }

    fun isProductDisplayedOnSearchPage(productName: String): Boolean {
        waitForElementToBeVisible(filterByPriceButton)
        val products = driver.findElements(By.xpath("//h3[contains(text(), '$productName')]")).map { it.text }
        return products.any { it.contains(productName, ignoreCase = true) }
    }

    enum class ProductList(val productName: String) {
        NORTH_FACE("North Face"),
        VOLCOM("Volcom"),
        QUICK_SILVER("Quicksilver"),
    }
}
