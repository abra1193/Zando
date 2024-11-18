package tests.profileTests

import base.BaseTest
import org.testng.annotations.Test

class ProfileTests : BaseTest() {
    @Test
    fun `Verify user can create a profile with valid data`() {
        zando.homePage().tapOnProfileButton()
        zando.profilePage().registerNewProfile()
    }

    @Test
    fun `Verify user can login with profile data in the database`() {
    }

    @Test
    fun `Verify user can update the profile information with valid data`() {
    }
}