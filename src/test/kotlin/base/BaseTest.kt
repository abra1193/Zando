package base

import io.github.bonigarcia.wdm.WebDriverManager.chromedriver
import io.github.bonigarcia.wdm.WebDriverManager.firefoxdriver
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.testng.annotations.AfterTest
import org.testng.annotations.BeforeTest
import org.testng.annotations.Parameters
import java.util.Locale
import java.util.concurrent.TimeUnit

abstract class BaseTest {
    protected lateinit var zando: Zando
    private lateinit var driver: WebDriver

    @Parameters("browser")
    @BeforeTest
    fun setup(browserName: String) {
        driver =
            initializeDriver(browserName).apply {
                manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS)
                manage().window().maximize()
                get("https://en.zalando.de/")
            }
        zando = Zando(driver)
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
