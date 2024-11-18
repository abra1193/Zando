package base

import org.openqa.selenium.WebDriver
import org.testng.annotations.AfterTest
import org.testng.annotations.BeforeClass
import org.testng.annotations.Parameters
import pages.HomePage
import pages.OrderPage
import pages.ProfilePage
import pages.SearchPage
import java.util.concurrent.TimeUnit

open class BaseTest {
    private lateinit var driver: WebDriver

    val homePage: HomePage by lazy { HomePage(driver) }

    val orderPage: OrderPage by lazy { OrderPage(driver) }

    val profilePage: ProfilePage by lazy { ProfilePage(driver) }

    val searchPage: SearchPage by lazy { SearchPage(driver) }

    @Parameters("browser", "baseUrl")
    @BeforeClass
    fun setup(
        browserName: String,
        baseUrl: String,
    ) {
        driver =
            DriverFactory.initializeDriver(browserName).apply {
                manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS)
                manage().window().maximize()
                get(baseUrl)
            }
    }

    @AfterTest
    fun tearDown() {
        if (::driver.isInitialized) {
            driver.quit()
        }
    }
}
