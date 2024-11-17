package pages

import base.ScreenHandlers
import org.amshove.kluent.should
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class SearchPage(driver: WebDriver) : ScreenHandlers(driver){

    @FindBy(id = "header-search-input")
    private lateinit var searchBarIcon: WebElement

    @FindBy(id = "header-search-option-0")
    private lateinit var productOption: WebElement

    @FindBy(id = "reset-search-term-:R375d8cap:")
    private lateinit var resetSearchButton: WebElement

    @FindBy(id = "reset-search-term-:R375d8cap:")
    private lateinit var searchResultTitle: WebElement

    fun selectProduct(product: String): SearchPage{
        waitForElementToBeVisible(searchBarIcon)
        searchBarIcon.sendKeys(product)
        productOption.click()
        isElementDisplayed(resetSearchButton)
        isElementDisplayed(driver.findElement(By.xpath("cc")))
    }
}
