package base

import io.qameta.allure.Allure
import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot
import org.openqa.selenium.WebDriver
import org.testng.annotations.AfterMethod
import org.testng.annotations.AfterTest
import org.testng.annotations.BeforeClass
import pages.HomePage
import pages.ProductPage
import pages.ProfilePage
import pages.SearchPage
import pages.ShoppingCartPage
import java.io.ByteArrayInputStream
import java.time.Duration

open class BaseTest {
    protected lateinit var driver: WebDriver

    val homePage: HomePage by lazy { HomePage(driver) }

    val shoppingCartPage: ShoppingCartPage by lazy { ShoppingCartPage(driver) }

    val profilePage: ProfilePage by lazy { ProfilePage(driver) }

    val searchPage: SearchPage by lazy { SearchPage(driver) }

    val productPage: ProductPage by lazy { ProductPage(driver) }

    @BeforeClass
    private fun setup() {
        val browser = System.getProperty("browser", "chrome")
        val url = System.getProperty("baseUrl", "https://en.zalando.de/")

        driver =
            DriverFactory.initializeDriver(browser).apply {
                manage().timeouts().implicitlyWait(Duration.ofSeconds(20))
                manage().window().maximize()
                get(url)
            }
    }

    @AfterMethod
    fun captureScreenshotOnFailure() {
        if (driver is TakesScreenshot) {
            try {
                val screenshotBytes = (driver as TakesScreenshot).getScreenshotAs(OutputType.BYTES)

                val screenshotInputStream = ByteArrayInputStream(screenshotBytes)

                Allure.addAttachment("Screenshot of failed tests", "image/png", screenshotInputStream, ".png")
            } catch (e: Exception) {
                println("Error capturing screenshot: ${e.message}")
            }
        }
    }

    @AfterTest
    fun tearDown() {
        if (::driver.isInitialized) {
            driver.quit()
        }
    }
}
