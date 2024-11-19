package base

import org.openqa.selenium.By
import org.openqa.selenium.StaleElementReferenceException
import org.openqa.selenium.TimeoutException
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.ExpectedCondition
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

abstract class ScreenHandler(val driver: WebDriver) {
    init {
        PageFactory.initElements(driver, this)
    }

    data class ElementWrapper(
        val webElement: WebElement,
        val locator: String,
    ) {
        fun sendKeys(value: String) {
            try {
                webElement.sendKeys(value)
            } catch (e: Exception) {
                throw Exception("Failed to execute send key $value in the element with locator $locator")
            }
        }

        fun click() {
            try {
                webElement.click()
            } catch (e: Exception) {
                throw Exception("Failed to execute click in the element with locator $locator")
            }
        }

        fun clear() {
            try {
                webElement.clear()
            } catch (e: Exception) {
                throw Exception("Failed to execute clear in the element with locator $locator")
            }
        }

        fun getAttribute(attributeName: String): String? {
            return try {
                webElement.getAttribute(attributeName)
            } catch (e: Exception) {
                throw Exception("Failed to get attribute $attributeName in the element with locator $locator")
            }
        }

        override fun toString(): String {
            return locator
        }
    }

    fun findElement(
        type: LocatorType,
        value: String,
    ): ElementWrapper {
        val element =
            try {
                when (type) {
                    LocatorType.ID -> driver.findElement(By.id(value))
                    LocatorType.XPATH -> driver.findElement(By.xpath(value))
                    LocatorType.CSS -> driver.findElement(By.cssSelector(value))
                }
            } catch (e: RuntimeException) {
                throw
                RuntimeException(
                    "No such element: Unable to locate element: " +
                        "${type.prefix}: '$value'",
                )
            }
        return ElementWrapper(element, "${type.prefix}: '$value'")
    }

    fun retrieveTextFieldValue(elementWrapper: ElementWrapper): String {
        return elementWrapper.getAttribute("value") ?: error("Value is not available")
    }

    protected fun waitForElementToBeVisible(
        elementWrapper: ElementWrapper,
        timeOut: Long = 0L,
    ) {
        try {
            WebDriverWait(driver, Duration.ofSeconds(timeOut))
                .until(elementDisplayed(elementWrapper.webElement))
        } catch (e: TimeoutException) {
            val locator = elementWrapper.toString()
            throw TimeoutException("Waiting for element to be visible with locator strategy: $locator")
        }
    }

    protected fun waitForElementToBeClickable(
        elementWrapper: ElementWrapper,
        timeOut: Long = 0L,
    ) {
        try {
            WebDriverWait(driver, Duration.ofSeconds(timeOut))
                .until(ExpectedConditions.elementToBeClickable(elementWrapper.webElement))
        } catch (e: TimeoutException) {
            val locator = elementWrapper.toString()
            throw TimeoutException("Waiting for element to be clickable with locator strategy: $locator")
        }
    }

    private fun elementDisplayed(element: WebElement): ExpectedCondition<Boolean> {
        return object : ExpectedCondition<Boolean> {
            override fun apply(input: WebDriver?): Boolean {
                return isElementDisplayed(element)
            }

            override fun toString(): String {
                return String().format("element is displayed: %s", element)
            }
        }
    }

    protected fun isElementDisplayed(
        element: WebElement,
        timeOut: Long = 0L,
    ): Boolean {
        try {
            WebDriverWait(driver, Duration.ofSeconds(timeOut))
                .until(
                    ExpectedConditions.visibilityOf(element),
                )
        } catch (e: Exception) {
            when (e) {
                is NoSuchElementException, is TimeoutException, is StaleElementReferenceException -> {
                    return false
                }
                else -> throw e
            }
        }
        return true
    }

    protected fun isElementNotDisplayed(element: WebElement): Boolean {
        return try {
            !element.isDisplayed
        } catch (e: Exception) {
            when (e) {
                is NoSuchElementException, is TimeoutException, is StaleElementReferenceException -> true
                else -> throw e
            }
        }
    }

    enum class LocatorType(val prefix: String) {
        ID("By.id"),
        XPATH("By.xpath"),
        CSS("By.cssSelector"),
    }
}
