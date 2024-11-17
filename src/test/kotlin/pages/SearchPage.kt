package pages

import base.ScreenHandlers
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class SearchPage(driver: WebDriver) : ScreenHandlers(driver) {
    @FindBy(id = "header-search-input")
    private lateinit var searchBar: WebElement

    @FindBy(id = "reset-search-term-:R375d8cap:")
    private lateinit var resetSearchButton: WebElement

    @FindBy(id = "reset-search-term-:R375d8cap:")
    private lateinit var searchResultTitle: WebElement

    fun selectProduct(product: String): SearchPage {
        searchBar.sendKeys(product)
        selectRandomSearchOption()
        return SearchPage(driver)
    }

    private fun selectRandomSearchOption() {
        val element = driver.findElement(By.id("header-search-option-${(0..9).random()}"))
        waitForElementToBeVisible(element)
        element.click()
    }
}
