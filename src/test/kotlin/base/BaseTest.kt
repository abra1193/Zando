package base

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.testng.annotations.AfterTest
import org.testng.annotations.BeforeTest
import java.util.concurrent.TimeUnit

abstract class BaseTest {
    private lateinit var driver: WebDriver

    @BeforeTest
    fun setup() {
        val driver = ChromeDriver()
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS)
        driver.manage().window().maximize()
        driver.get("https://en.zalando.de/")
    }

    @AfterTest
    fun tearDown()  {
        driver.close()
    }
}
