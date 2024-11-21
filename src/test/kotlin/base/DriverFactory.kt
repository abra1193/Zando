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
                    addArguments("--headless", "--no-sandbox", "--disable-dev-shm-usage")
                    System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver")
                }
                ChromeDriver(chromeOptions)
            }

            "firefox" -> {
                firefoxdriver().setup()
                val fireFoxOptions = FirefoxOptions()
                fireFoxOptions.apply {
                    addArguments("--headless")
                    System.setProperty("webdriver.gecko.driver", "/usr/local/bin/geckodriver")
                }
                FirefoxDriver()
            }

            else -> {
                log.error("Browser '$browserName' is not iniatilized")
                throw IllegalArgumentException("Browser $browserName not available")
            }
        }
    }
}
