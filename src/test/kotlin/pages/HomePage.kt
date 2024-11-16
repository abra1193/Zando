package pages

import base.ZandoDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory

class HomePage(driver: ZandoDriver) {
    init {
        PageFactory.initElements(driver, this)
    }

    @FindBy(id = "header-user-account-icon")
    private val profileIcon: WebElement? = null

    fun tapOnProfileIcon() {
        profileIcon!!.click()
    }
}
