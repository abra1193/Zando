package base

import org.testng.annotations.AfterTest
import org.testng.annotations.BeforeTest
import org.testng.annotations.Parameters
import java.util.concurrent.TimeUnit

abstract class BaseTest {
    @Parameters("browser")
    @BeforeTest
    fun setup(browserName: String) {
        val driver = ZandoDriver.getDriver(browserName)
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS)
        driver.manage().window().maximize()
        driver.get("https://en.zalando.de/")
    }

    @AfterTest
    fun tearDown() {
        ZandoDriver.quitDriver()
    }
}
