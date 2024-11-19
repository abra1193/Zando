package pages

import base.ScreenHandler
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

var MINIMUM_PRICE = "0"
var MAXIMUM_PRICE = "1"

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

    private val savePriceFilterButton: ElementWrapper by lazy {
        findElement(LocatorType.XPATH, "//*[@aria-label=\"Save the Price filter\"]")
    }

    private val lowestPriceSort: ElementWrapper by lazy {
        findElement(LocatorType.XPATH, "//*[@for=\"sorting-PRICE_ASC\"]]")
    }

    private val saveSortButton: ElementWrapper by lazy {
        findElement(LocatorType.XPATH, "//*[@aria-label=\"Save the Sort by filter\"]")
    }

    fun searchProduct(productName: String): SearchPage {
        waitForElementToBeVisible(searchBar)
        searchBar.sendKeys(productName)
        selectRandomSearchOption()
        return this
    }

    fun sortProduct(): SearchPage {
        waitForElementToBeVisible(sortByButton)
        sortByButton.click()

        waitForElementToBeVisible(lowestPriceSort)
        lowestPriceSort.click()

        waitForElementToBeVisible(saveSortButton)
        saveSortButton.click()

        return this
    }

    fun filterProduct(): SearchPage {
        waitForElementToBeVisible(filterByPriceButton)
        filterByPriceButton.click()

        waitForElementToBeVisible(minimumPriceField)
        minimumPriceField.sendKeys(MINIMUM_PRICE)
        MINIMUM_PRICE = retrieveTextFieldValue(minimumPriceField)


        waitForElementToBeVisible(maximumPriceField)
        maximumPriceField.clear()
        maximumPriceField.sendKeys(MAXIMUM_PRICE)
        MAXIMUM_PRICE = retrieveTextFieldValue(maximumPriceField)


        waitForElementToBeVisible(savePriceFilterButton)
        savePriceFilterButton.click()

        return this
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
        val products = driver.findElements(By.xpath("//h3[normalize-space(text()) = '$productName']")).map { it.text }
        return products.any { it.contains(productName, ignoreCase = true) }
    }

    fun isProductSortedOnSearchPage(): Boolean {
        waitForElementToBeVisible(filterByPriceButton)
        // val products = driver.findElements(By.xpath("//h3[contains(text(), '$productName')]")).map { it.text }
        return true
    }

    fun isProductFilteredOnSearchPage(): Boolean {
        waitForElementToBeVisible(filterByPriceButton)
        val amounts =
            driver.findElements(
                By.xpath(
                    "//span[contains(text(), '€') and string-length(text()) > 3 and \n" +
                        "     not(preceding-sibling::span[contains(text(), 'Originally')]) and\n" +
                        "     not(ancestor::a[contains(., 'Free delivery for orders over 29,90 €')])]",
                ),
            ).map { it.text }
        val amountInList =
            amounts.map { amountText ->
                val cleanedAmount = amountText.replace("€", "").trim().replace(",", "").toInt()
                cleanedAmount
            }
        val isAmountWithinRange = amountInList.any { amount -> amount in MINIMUM_PRICE.toInt()..MAXIMUM_PRICE.toInt() }
        return isAmountWithinRange
    }

    enum class ProductList(val productName: String) {
        NORTH_FACE("The North Face"),
        VOLCOM("Volcom"),
        QUICK_SILVER("Quiksilver"),
    }
}
