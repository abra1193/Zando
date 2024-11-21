package base

import base.Logger.log
import io.github.bonigarcia.wdm.WebDriverManager.chromedriver
import io.github.bonigarcia.wdm.WebDriverManager.firefoxdriver
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions
import java.util.Locale

object DriverFactory {
    fun initializeDriver(browserName: String): WebDriver {
        log.info("Iniatilizing Webdriver for browser $browserName")
        return when (browserName.lowercase(Locale.getDefault())) {
            "chrome" -> {
                chromedriver().setup()
                val chromeOptions = ChromeOptions()
                chromeOptions.apply {
                    setExperimentalOption("excludeSwitches", listOf("enable-automation"))
                    addArguments("--headless", "--no-sandbox", "--disable-dev-shm-usage", "--disable-gpu", "--window-size=1920x1080")
                    addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")
                }
                log.info("ChromeDriver setup complete.")
                ChromeDriver(chromeOptions)
            }

            "firefox" -> {
                firefoxdriver().setup()
                val fireFoxOptions = FirefoxOptions()
                fireFoxOptions.apply {
                    addArguments("--headless", "--no-sandbox", "--disable-dev-shm-usage", "--disable-gpu", "--window-size=1920x1080")
                    addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")
                }
                log.info("FirefoxDriver setup complete.")
                FirefoxDriver()
            }

            else -> {
                log.error("Browser '$browserName' is not iniatilized")
                throw IllegalArgumentException("Browser $browserName not available")
            }
        }
    }
}
