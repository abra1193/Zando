package base

import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.testng.annotations.AfterTest
import org.testng.annotations.BeforeTest
import org.testng.annotations.Parameters
import java.util.concurrent.TimeUnit

abstract class BaseTest {
    lateinit var driver: WebDriver
    lateinit var zando: Zando

    @Parameters("browser")
    @BeforeTest
    fun setup(browserName: String) {
        when (browserName) {
            "chrome" -> WebDriverManager.chromedriver().setup()
            "firefox" -> WebDriverManager.firefoxdriver().setup()
            else -> throw IllegalArgumentException("Browser $browserName not available")
        }
        driver =
            when (browserName) {
                "chrome" -> ChromeDriver()
                "firefox" -> FirefoxDriver()
                else -> throw IllegalArgumentException("Driver not available")
            }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS)
        driver.manage().window().maximize()
        driver.get("https://en.zalando.de/")
    }

    @AfterTest
    fun tearDown() {
        driver.close()
    }
}
