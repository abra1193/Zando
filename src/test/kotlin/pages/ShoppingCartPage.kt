package pages

import base.ScreenHandler
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

class ShoppingCartPage(driver: WebDriver) : ScreenHandler(driver) {

    private val sizeField: ElementWrapper by lazy {
        findElement(LocatorType.XPATH, "//div[contains(text(), 'Size')]")
    }

    private val totalPriceField: ElementWrapper by lazy {
        findElement(LocatorType.XPATH, "//div[@data-id='total-price']//span[contains(text(), '€')]")
    }

    private val quantityDropdown: ElementWrapper by lazy {
        findElement(LocatorType.XPATH, "(//select[@aria-label=\"quantity-selection\"])[2]")
    }

    fun isCorrectAddedSizeDisplayedOnCart(productSize: String): Boolean {
        waitForElementToBeVisible(sizeField)
        return sizeField.webElement.text.replace("Size: ", "") == productSize
    }

    fun verifyCartUpdate(productPrice: String): Boolean {
        val dropdownQuantitiesXPath = "(//select[@aria-label='quantity-selection'])[2]/option[@value='2']"
        val updatedQuantitiesXpath = "(//select[@aria-label='quantity-selection'])[2]/option[@selected]"
        val totalPriceXpath = "//div[@data-id='total-price']//span[not(contains(text(), 'VAT included')) " +
            "and not(contains(text(), 'Total'))]"

        waitForElementToBeVisible(quantityDropdown)
        val dropdown = driver.findElement(By.xpath(dropdownQuantitiesXPath))
        dropdown.click()
        val totalPriceField = driver.findElement(
            By.xpath(
                totalPriceXpath
            )
        )

        Thread.sleep(3000) // Added because when updating the shopping cart the shopping cart takes too long to load

        val totalPrice = totalPriceField.text
            .replace(" €", "").trim().replace(",", ".").toBigDecimal()
        driver.navigate().refresh()

        val updatedQuantity = driver.findElement(
            By.xpath(
                updatedQuantitiesXpath
            )
        ).text.toBigDecimal()

        val expectedTotalPrice = productPrice.trim().replace(",", ".")
            .toBigDecimal() * updatedQuantity
        return totalPrice.compareTo(expectedTotalPrice) == 0
    }

    fun getProductPrice(): String {
        return totalPriceField.webElement.text.replace(" €", "").replace(",", ".")
    }
}
