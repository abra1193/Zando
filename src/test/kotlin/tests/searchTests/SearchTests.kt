package tests.searchTests

import base.BaseTest
import io.kotest.matchers.booleans.shouldBeTrue
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.testng.annotations.Test
import pages.SearchPage.ProductList

class SearchTests : BaseTest() {

    companion object {
        private var productName: String = ProductList.entries.toTypedArray().random().productName
    }

    @Test(priority = 0)
    fun `Verify user can search a product based on the given search keywords`() {
        homePage.tapOnSearchButton()
        searchPage.searchProduct(productName)

        searchPage.isProductDisplayedOnSearchPage(productName).shouldBeTrue()
        val t =LoggerFactory.getLogger("MyLogger")
        t.error("fa")
    }

    @Test(priority = 1, dependsOnMethods = ["Verify user can search a product based on the given search keywords"])
    fun `Verify user can filter search results based on the given filters`() {
        searchPage.filterProduct()

        searchPage.isProductWithinSearchCriteria().shouldBeTrue()
    }

    @Test(priority = 2, dependsOnMethods = ["Verify user can filter search results based on the given filters"])
    fun `Verify user can sort search results based on the given filters`() {
        searchPage.sortProduct()

        searchPage.isProductWithinSearchCriteria().shouldBeTrue()
    }
}
