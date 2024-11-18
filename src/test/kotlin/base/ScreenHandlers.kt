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

abstract class ScreenHandlers(val driver: WebDriver) {
    init {
        PageFactory.initElements(driver, this)
    }

    data class ElementWrapper(
        val webElement: WebElement,
        val locator: String,
    )

    fun findElement(
        type: LocatorType,
        value: String,
    ): ElementWrapper {
        val element =
            when (type) {
                LocatorType.ID -> driver.findElement(By.id(value))
                LocatorType.XPATH -> driver.findElement(By.xpath(value))
                LocatorType.CSS -> driver.findElement(By.cssSelector(value))
            }
        return ElementWrapper(element, "${type.prefix}('$value')")
    }

    protected fun waitForElementToBeVisible(
        element: WebElement,
        timeOut: Long = 0L,
        elementWrapper: ElementWrapper? = null,
    ) {
        try {
            WebDriverWait(driver, Duration.ofSeconds(timeOut))
                .until(elementDisplayed(element))
        } catch (e: TimeoutException) {
            throw TimeoutException("Waiting for element to be visible with locator strategy: $elementWrapper")
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
