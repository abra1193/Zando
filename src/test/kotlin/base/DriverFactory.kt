package base

import io.github.bonigarcia.wdm.WebDriverManager.chromedriver
import io.github.bonigarcia.wdm.WebDriverManager.firefoxdriver
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxDriver
import java.util.Locale

object DriverFactory {
    fun initializeDriver(browserName: String): WebDriver {
        return when (browserName.lowercase(Locale.getDefault())) {
            "chrome" -> {
                chromedriver().setup()
                val options = ChromeOptions()
                options.setExperimentalOption("excludeSwitches", listOf("enable-automation"))
                ChromeDriver(options)
            }
            "firefox" -> {
                firefoxdriver().setup()
                FirefoxDriver()
            }
            else -> throw IllegalArgumentException("Browser $browserName not available")
        }
    }
}
