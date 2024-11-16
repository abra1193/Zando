package tests

import base.BaseTest
import org.testng.annotations.Test
import pages.HomePage

class ProfileTests : BaseTest() {
    @Test
    fun `Verify user can create a profile with valid data`() {
        HomePage(driver).tapOnProfileIcon()
    }

    @Test
    fun `Verify user can login with profile data in the database`() {
        HomePage(driver).tapOnProfileIcon()
    }

    @Test
    fun `Verify user can update the profile information with valid data`() {
        HomePage(driver).tapOnProfileIcon()
    }
}
