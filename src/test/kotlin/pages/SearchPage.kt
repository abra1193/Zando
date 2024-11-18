package pages

import base.ScreenHandler
import org.openqa.selenium.WebDriver

class SearchPage(driver: WebDriver) : ScreenHandler(driver) {
    private val searchBar: ElementWrapper by lazy {
        findElement(LocatorType.ID, "header-search-input")
    }

    fun selectProduct(): SearchPage {
        searchBar.sendKeys(ProductList.entries.toTypedArray().random().productName)
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

    enum class ProductList(val productName: String){
        NORTH_FACE("The North Face"),
        ADIDAS("Adidas"),
        NIKE("Nike"),
        NEW_BALANCE("New Balance"),
        VOLCOM("Volcom"),
        QUICK_SILVER("QuickSilver"),
        CARHARTT("Carhartt")
    }
}
