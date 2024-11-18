package base

import io.github.bonigarcia.wdm.WebDriverManager.chromedriver
import io.github.bonigarcia.wdm.WebDriverManager.firefoxdriver
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.testng.annotations.AfterTest
import org.testng.annotations.BeforeClass
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Parameters
import pages.HomePage
import pages.OrderPage
import pages.ProfilePage
import pages.SearchPage
import java.util.Locale
import java.util.concurrent.TimeUnit

open class BaseTest {
    private lateinit var driver: WebDriver

    val homePage: HomePage by lazy { HomePage(driver) }

    val orderPage: OrderPage by lazy { OrderPage(driver) }

    val profilePage: ProfilePage by lazy { ProfilePage(driver) }

    val searchPage: SearchPage by lazy { SearchPage(driver) }

    @Parameters("browser")
    @BeforeClass
    fun setup(browserName: String) {
        driver =
            initializeDriver(browserName).apply {
                manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS)
                manage().window().maximize()
                get("https://en.zalando.de/")
            }
    }

    private fun initializeDriver(browserName: String): WebDriver {
        return when (browserName.lowercase(Locale.getDefault())) {
            "chrome" -> {
                chromedriver().setup()
                ChromeDriver()
            }
            "firefox" -> {
                firefoxdriver().setup()
                FirefoxDriver()
            }
            else -> throw IllegalArgumentException("Browser $browserName not available")
        }
    }

    @AfterTest
    fun tearDown() {
        if (::driver.isInitialized) {
            driver.quit()
        }
    }
}
