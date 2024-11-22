package tests.profileTests

import base.BaseTest
import io.kotest.matchers.booleans.shouldBeTrue
import org.testng.annotations.Test
import kotlin.test.assertTrue

class ProfileTests : BaseTest() {

    @Test(priority = 0)
    fun `Verify profile page is displayed correctly for an user`() {
        homePage.tapOnProfileButton()

        assertTrue(profilePage.isProfilePageDisplayedCorrectly().shouldBeTrue(), "Profile page is displayed correctly")
    }
    // Due to extra layer of security on the portal
    // the test will only be asserting presence of element on sign in page
}
