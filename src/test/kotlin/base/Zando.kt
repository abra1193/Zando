package base

import org.openqa.selenium.WebDriver
import pages.HomePage
import pages.OrderPage
import pages.ProfilePage

abstract class Zando(private val driver: WebDriver) {
    fun homePage() = HomePage(driver)

    fun orderPage() = OrderPage(driver)

    fun profilePage() = ProfilePage(driver)
}
