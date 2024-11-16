package base

import org.openqa.selenium.WebDriver
import pages.HomePage
import pages.OrderPage
import pages.ProfilePage
import pages.SearchPage

class Zando(private var driver: WebDriver) {
    fun homePage() = HomePage(driver)

    fun orderPage() = OrderPage(driver)

    fun profilePage() = ProfilePage(driver)

    fun searchPage() = SearchPage(driver)
}
