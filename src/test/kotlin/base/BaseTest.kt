package base

import org.openqa.selenium.WebDriver
import org.testng.annotations.AfterTest
import org.testng.annotations.BeforeClass
import pages.HomePage
import pages.ProfilePage
import pages.SearchPage
import pages.ShoppingCartPage
import java.time.Duration

open class BaseTest {
    protected lateinit var driver: WebDriver

    val homePage: HomePage by lazy { HomePage(driver) }

    val shoppingCartPage: ShoppingCartPage by lazy { ShoppingCartPage(driver) }

    val profilePage: ProfilePage by lazy { ProfilePage(driver) }

    val searchPage: SearchPage by lazy { SearchPage(driver) }

    @BeforeClass
    fun setup() {
        val browser = System.getProperty("browser", "chrome")
        val url = System.getProperty("baseUrl", "https://en.zalando.de/")

        driver =
            DriverFactory.initializeDriver(browser).apply {
                manage().timeouts().implicitlyWait(Duration.ofSeconds(20))
                manage().window().maximize()
                get(url)
            }
    }

    @AfterTest
    fun tearDown() {
        if (::driver.isInitialized) {
            driver.quit()
        }
    }
}
