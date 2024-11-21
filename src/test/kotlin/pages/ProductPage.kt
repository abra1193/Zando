package pages

import base.ScreenHandler
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import utils.TimeOuts.TIMEOUT_10_SECONDS

var productSizeXpath = "//div[@role='listitem' and .//span[string-length(text()) > 0]" +
    "and not(.//span[contains(text(), 'Notify Me')]) and not(.//span[contains(text(), 'Only 1 left')])]"

class ProductPage(driver: WebDriver) : ScreenHandler(driver) {

    private val addToBagButton: ElementWrapper by lazy {
        findElement(LocatorType.XPATH, "//*[span[contains(text(), \"Add to bag\")]]")
    }

    private val chooseSizeDropDown: ElementWrapper by lazy {
        findElement(LocatorType.XPATH, "//*[span[contains(text(), \"Choose your size\")]]")
    }

    private val productNameField: ElementWrapper by lazy {
        findElement(LocatorType.XPATH, "//span[@class=\"EKabf7 R_QwOV\"]")
    }

    private val shoppingCartButton: ElementWrapper by lazy {
        findElement(LocatorType.XPATH, "//*[@data-testid=\"cart-link\"]")
    }

    private var productSize: String? = null

    fun addProductToBag(): ShoppingCartPage {
        waitForElementToBeVisible(productNameField)
        selectProductSize()

        waitForElementToBeVisible(addToBagButton)
        addToBagButton.click()

        waitForElementToBeClickable(shoppingCartButton, TIMEOUT_10_SECONDS)
        shoppingCartButton.click()
        return ShoppingCartPage(driver)
    }

    private fun selectProductSize() {
        waitForElementToBeClickable(chooseSizeDropDown)
        chooseSizeDropDown.click()

        isElementVisible(driver.findElement(By.xpath(productSizeXpath)))

        val availableSizeElements = driver.findElements(By.xpath(productSizeXpath)) ?: error("No size available")

        val randomSizeElement = availableSizeElements.map { it.text }.random().split("\n")[0]

        val selectedSizeElement = availableSizeElements.find { it.text.split("\n")[0] == randomSizeElement }
        selectedSizeElement?.click()

        productSize = randomSizeElement
    }

    fun getProductSize(): String {
        return productSize ?: error("Product size is not avaiable for the product")
    }
}
