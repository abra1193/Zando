package pages

import base.ScreenHandlers
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

class SearchPage(driver: WebDriver) : ScreenHandlers(driver) {
    private val searchBar: ElementWrapper by lazy {
        findElement(LocatorType.ID, "header-search-input")
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
            )
        waitForElementToBeVisible(element)
        element.click()
    }
}
