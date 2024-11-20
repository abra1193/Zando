package tests.shoppingCartTests

import base.BaseTest
import io.kotest.matchers.booleans.shouldBeTrue
import org.testng.annotations.Test

class ShoppingCartTests : BaseTest() {

    @Test(priority = 0)
    fun `Verify user can add a product in the shopping cart`() {
        homePage.tapOnSearchButton()
        searchPage.searchProduct("New Balance 9060")

        searchPage.selectProduct()
        productPage.addProductToBag()

        shoppingCartPage.isCorrectAddedSizeDisplayedOnCart(productPage.getProductSize()).shouldBeTrue()
    }

    @Test(priority = 1, dependsOnMethods = ["Verify user can add a product in the shopping cart"])
    fun `Verify user can update the shopping cart`() {

    }

    @Test(priority = 2, dependsOnMethods = ["Verify user can update the shopping cart"])
    fun `Verify user can proceed to the checkout page`() {
    }
}
