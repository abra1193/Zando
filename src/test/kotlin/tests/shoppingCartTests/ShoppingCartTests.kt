package tests.shoppingCartTests

import base.BaseTest
import io.kotest.matchers.booleans.shouldBeTrue
import org.testng.annotations.Test

class ShoppingCartTests : BaseTest() {

    @Test(priority = 0)
    fun `Verify user can add a product in the shopping cart`() {
        homePage.tapOnSearchButton()
        searchPage.searchProduct("Bershka jeans")

        searchPage.selectProduct()
        productPage.addProductToBag()

        shoppingCartPage.isCorrectAddedSizeDisplayedOnCart(productPage.getProductSize()).shouldBeTrue()
    }

    @Test(priority = 1, dependsOnMethods = ["Verify user can add a product in the shopping cart"])
    fun `Verify user can update the shopping cart`() {
        val productPrice = shoppingCartPage.getProductPrice()

        shoppingCartPage.verifyCartUpdate(productPrice).shouldBeTrue()
    }

    // Due to extra layer of security on the portal
    // the test will only be asserting shopping cart update
    // because login can't be reached
}
