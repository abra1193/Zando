package tests.searchTests

import base.BaseTest
import org.testng.annotations.Test
import pages.SearchPage.ProductList
import kotlin.test.assertTrue

class SearchTests : BaseTest() {
    companion object {
        private var productName: String = ProductList.entries.toTypedArray().random().productName
    }

    @Test(priority = 0)
    fun `Verify user can search a product based on the given search keywords`() {
        homePage.tapOnSearchButton()
        searchPage.searchProduct(productName)
        assertTrue { searchPage.isProductDisplayedOnSearchPage(productName) }
    }

    @Test(priority = 1, dependsOnMethods = ["Verify user can search a product based on the given search keywords"])
    fun `Verify user can filter search results based on the given filters`() {
        searchPage.filterProduct()
        assertTrue { searchPage.isProductWithinSearchCriteria() }
    }

    @Test(priority = 2, dependsOnMethods = ["Verify user can filter search results based on the given filters"])
    fun `Verify user can sort search results based on the given filters`() {
        searchPage.sortProduct()
        assertTrue { searchPage.isProductWithinSearchCriteria(true) }
    }
}
