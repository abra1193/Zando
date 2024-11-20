package base

import org.openqa.selenium.By
import org.openqa.selenium.ElementNotInteractableException
import org.openqa.selenium.StaleElementReferenceException
import org.openqa.selenium.TimeoutException
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebDriverException
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.ExpectedCondition
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration
import kotlin.NoSuchElementException

abstract class ScreenHandler(val driver: WebDriver) {
    init {
        PageFactory.initElements(driver, this)
    }

    data class ElementWrapper(
        val webElement: WebElement,
        val locator: String
    ) {
        fun sendKeys(value: String) {
            try {
                webElement.sendKeys(value)
            } catch (e: ElementNotInteractableException) {
                throw Exception("Element not interactable while sending keys '$value' to element with locator: $locator", e)
            } catch (e: StaleElementReferenceException) {
                throw Exception("Stale element reference while sending keys '$value' to element with locator: $locator", e)
            } catch (e: WebDriverException) {
                throw Exception("WebDriver error while sending keys '$value' to element with locator: $locator", e)
            }
        }

        fun click() {
            try {
                webElement.click()
            } catch (e: ElementNotInteractableException) {
                throw Exception("Element not interactable while clicking element with locator: $locator", e)
            } catch (e: StaleElementReferenceException) {
                throw Exception("Stale element reference while clicking element with locator: $locator", e)
            } catch (e: WebDriverException) {
                throw Exception("WebDriver error while clicking element with locator: $locator", e)
            }
        }

        fun getAttribute(attributeName: String): String? {
            return try {
                webElement.getAttribute(attributeName)
            } catch (e: NoSuchElementException) {
                throw Exception("Element with locator: $locator not found when getting attribute '$attributeName'", e)
            } catch (e: StaleElementReferenceException) {
                throw Exception("Stale element reference while getting attribute '$attributeName' from element with locator: $locator", e)
            } catch (e: WebDriverException) {
                throw Exception("WebDriver error while getting attribute '$attributeName' from element with locator: $locator", e)
            }
        }

        fun isDisplayed(): Boolean {
            return try {
                webElement.isDisplayed
            } catch (e: RuntimeException) {
                false
            } catch (e: StaleElementReferenceException) {
                false
            } catch (e: WebDriverException) {
                false
            }
        }

        override fun toString(): String {
            return locator
        }
    }

    fun findElement(
        type: LocatorType,
        value: String
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
                        "${type.prefix}: '$value'"
                )
            }
        return ElementWrapper(element, "${type.prefix}: '$value'")
    }

    fun getTextFromField(elementWrapper: ElementWrapper): String {
        return elementWrapper.getAttribute("value") ?: error("Value is not available")
    }

    protected fun waitForElementToBeVisible(
        elementWrapper: ElementWrapper,
        timeOut: Long = 0L
    ) {
        try {
            WebDriverWait(driver, Duration.ofSeconds(timeOut))
                .until(elementDisplayed(elementWrapper.webElement))
        } catch (e: RuntimeException) {
            val locator = elementWrapper.toString()
            throw RuntimeException("Waiting for element to be visible with locator strategy: $locator")
        }
    }

    protected fun waitForElementToBeClickable(
        elementWrapper: ElementWrapper,
        timeOut: Long = 0L
    ) {
        try {
            WebDriverWait(driver, Duration.ofSeconds(timeOut))
                .until(ExpectedConditions.elementToBeClickable(elementWrapper.webElement))
        } catch (e: RuntimeException) {
            val locator = elementWrapper.toString()
            throw RuntimeException("Waiting for element to be clickable with locator strategy: $locator")
        }
    }

    private fun elementDisplayed(element: WebElement): ExpectedCondition<Boolean> {
        return object : ExpectedCondition<Boolean> {
            override fun apply(input: WebDriver?): Boolean {
                return isElementVisible(element)
            }

            override fun toString(): String {
                return String().format("element is displayed: %s", element)
            }
        }
    }

    protected fun isElementVisible(
        element: WebElement,
        timeOut: Long = 0L
    ): Boolean {
        try {
            WebDriverWait(driver, Duration.ofSeconds(timeOut))
                .until(
                    ExpectedConditions.visibilityOf(element)
                )
        } catch (e: Exception) {
            when (e) {
                is NoSuchElementException, is TimeoutException, is StaleElementReferenceException, is RuntimeException -> {
                    return false
                }
                else -> throw e
            }
        }
        return true
    }

    enum class LocatorType(val prefix: String) {
        ID("By.id"),
        XPATH("By.xpath"),
        CSS("By.cssSelector")
    }
}
