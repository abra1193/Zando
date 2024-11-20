package tests.profileTests

import base.BaseTest
import org.testng.annotations.Test
import kotlin.test.assertTrue

class ProfileTests : BaseTest() {

    @Test(priority = 0)
    fun `Verify profile page is displayed correctly for an user`() {
        homePage.tapOnProfileButton()
        assertTrue { profilePage.isProfilePageDisplayedCorrectly() }
    }
    // Due to extra layer of security on portal the test will only be asserting presence of element on sign in page
}
