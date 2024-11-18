package tests.profileTests

import base.BaseTest
import org.testng.annotations.Test

class ProfileTests : BaseTest() {
    @Test(priority = 0)
    fun `Verify user can create a profile with valid data`() {
        homePage.tapOnProfileButton()
    }

    @Test(priority = 1)
    fun `Verify user can login with profile data in the database`() {
    }

    @Test(priority = 2)
    fun `Verify user can update the profile information with valid data`() {
    }
}
