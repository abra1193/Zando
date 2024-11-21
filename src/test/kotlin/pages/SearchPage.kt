package pages

import base.Logger.log
import base.ScreenHandler
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import utils.TimeOuts.TIMEOUT_10_SECONDS

var mininumPrice = ""
var maximumPrice = ""
var productPricesXpath = "//span[contains(text(), '€') and string-length(text()) > 3 and " +
    "     not(preceding-sibling::span[contains(text(), 'Originally')]) and" +
    "     not(ancestor::a[contains(., 'Free delivery for orders over 29,90 €')])]"

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
        findElement(LocatorType.XPATH, "//span[normalize-space()=\"Lowest Price\"]")
    }

    private val saveSortButton: ElementWrapper by lazy {
        findElement(LocatorType.XPATH, "//*[@aria-label=\"Save the Sort by filter\"]")
    }

    private val dealsPriceFilterButton: ElementWrapper by lazy {
        findElement(LocatorType.XPATH, "//*[@for=\"sales\"]")
    }

    private val favoriteButton: ElementWrapper by lazy {
        findElement(LocatorType.XPATH, "(//*[@aria-label='Add to wish list or remove from wish list'])")
    }

    fun searchProduct(productName: String): SearchPage {
        waitForElementToBeVisible(searchBar)
        searchBar.sendKeys(productName)
        selectRandomSearchOption()
        return this
    }

    fun selectProduct(): ProductPage {
        val products = driver.findElements(By.xpath(productPricesXpath))
        if (products.isNotEmpty()) {
            for (product in products) {
                if (isElementVisible(product)) {
                    product.click()
                    break
                }
            }
        } else {
            throw NoSuchElementException("No products found.")
        }
        return ProductPage(driver)
    }

    fun sortProduct(): SearchPage {
        waitForElementToBeVisible(sortByButton)
        sortByButton.click()

        waitForElementToBeVisible(lowestPriceSort)
        lowestPriceSort.click()

        waitForElementToBeVisible(saveSortButton)
        saveSortButton.click()

        Thread.sleep(2000) // Added Thread.sleep to allow the search page to fully load after the sorting

        return this
    }

    fun filterProduct(): SearchPage {
        waitForElementToBeVisible(filterByPriceButton)
        filterByPriceButton.click()

        waitForElementToBeVisible(dealsPriceFilterButton)
        dealsPriceFilterButton.click()

        waitForElementToBeVisible(minimumPriceField)
        mininumPrice = getTextFromField(minimumPriceField)

        waitForElementToBeVisible(maximumPriceField)
        maximumPrice = getTextFromField(maximumPriceField)

        waitForElementToBeVisible(savePriceFilterButton)
        savePriceFilterButton.click()

        Thread.sleep(5000) // Added Thread.sleep to allow the search page to fully load after the filter

        return this
    }

    private fun selectRandomSearchOption() {
        val element =
            findElement(
                LocatorType.ID,
                "header-search-option-${(1..9).random()}"
            )
        waitForElementToBeVisible(element)
        element.click()
    }

    fun isProductDisplayedOnSearchPage(productName: String): Boolean {
        waitForElementToBeVisible(filterByPriceButton)
        val products = driver.findElements(By.xpath("//h3[normalize-space(text()) = '$productName']")).map { it.text }
        return products.any { it.contains(productName, ignoreCase = true) }
    }

    fun isProductWithinSearchCriteria(isFirstPriceEqualToMin: Boolean = false): Boolean {
        val elementWrappers: List<ElementWrapper> =
            driver.findElements(
                By.xpath(
                    productPricesXpath
                )
            ).map { webElement ->
                ElementWrapper(
                    webElement,
                    productPricesXpath
                )
            }
        elementWrappers.forEach {
                elementWrapper ->
            waitForElementToBeClickable(elementWrapper, TIMEOUT_10_SECONDS)
        }
        val amounts = elementWrappers.map { it.webElement.text }
        val amountInList =
            amounts.map { amountText ->
                val cleanedAmount = amountText.replace("€", "").trim().replace(",", "")
                cleanedAmount.toFloat().div(100)
            }
        return if (amountInList.isNotEmpty()) {
            if (isFirstPriceEqualToMin) {
                amountInList.first() >= mininumPrice.toFloat() && amountInList.first() <= maximumPrice.toFloat()
            } else {
                amountInList.any { amount -> amount in mininumPrice.toFloat()..maximumPrice.toFloat() }
            }
        } else {
            false
        }
    }

    enum class ProductList(val productName: String) {
        NORTH_FACE("The North Face"),
        VOLCOM("Volcom"),
        QUICK_SILVER("Quiksilver")
    }
}
