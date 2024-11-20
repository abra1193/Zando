package tests.profileTests

import base.BaseTest
import io.kotest.matchers.booleans.shouldBeTrue
import org.testng.annotations.Test

class ProfileTests : BaseTest() {

    @Test(priority = 0)
    fun `Verify profile page is displayed correctly for an user`() {
        homePage.tapOnProfileButton()

        profilePage.isProfilePageDisplayedCorrectly().shouldBeTrue()
    }
    // Due to extra layer of security on portal
    // the test will only be asserting presence of element on sign in page
}
