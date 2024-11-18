package tests.searchTests

import base.BaseTest
import org.testng.annotations.Test

class SearchTests : BaseTest() {
    @Test
    fun `Verify user can search a product based on the given search keywords`() {
        zando.homePage().tapOnSearchButton()
        zando.searchPage().selectProduct("The North Face")
    }

    @Test
    fun `Verify user can sort search results based on the given filters`() {
    }

    @Test
    fun `Verify user can filter search results based on the given filters`() {
    }
}
