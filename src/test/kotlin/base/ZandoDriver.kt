package base

import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.testng.annotations.Parameters

object ZandoDriver {
    private var driver: WebDriver? = null

    @Parameters("browser")
    fun getDriver(browserName: String): WebDriver  {
        if (driver == null)
            {
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
            }
        return driver!!
    }

    fun quitDriver()  {
        driver!!.quit()
        driver = null
    }
}
