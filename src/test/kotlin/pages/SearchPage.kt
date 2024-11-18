package pages

import base.ScreenHandler
import org.openqa.selenium.WebDriver

class SearchPage(driver: WebDriver) : ScreenHandler(driver) {
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
