package pages

import base.ScreenHandler
import org.openqa.selenium.WebDriver

class ShoppingCartPage(driver: WebDriver) : ScreenHandler(driver) {

    private val sizeField: ElementWrapper by lazy {
        findElement(LocatorType.XPATH, "//div[contains(text(), 'Size')]")
    }

    private val quantityDropdown: ElementWrapper by lazy {
        findElement(LocatorType.XPATH, "//*[@aria-label=\"quantity-selection\"]")
    }


    fun isCorrectAddedSizeDisplayedOnCart(productSize: String) : Boolean {
        waitForElementToBeVisible(sizeField)
        return sizeField.webElement.text.replace("Size: ","") == productSize
    }

    private fun updateProductQuantity(){

    }
}
