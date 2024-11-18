package pages

import base.ScreenHandlers
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

class SearchPage(driver: WebDriver) : ScreenHandlers(driver) {
    private val searchBar: WebElement by lazy {
        findElement(LocatorType.ID, "header-search-input").webElement
    }

    fun selectProduct(product: String): SearchPage {
        searchBar.sendKeys(product)
        selectRandomSearchOption()
        return SearchPage(driver)
    }

    private fun selectRandomSearchOption() {
        val element =
            findElement(
                LocatorType.ID,
                "header-search-option-${(0..9).random()}",
            ).webElement
        waitForElementToBeVisible(element)
        element.click()
    }
}
