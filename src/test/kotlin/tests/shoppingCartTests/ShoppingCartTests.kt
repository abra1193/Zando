package tests.shoppingCartTests

import base.BaseTest
import io.kotest.matchers.booleans.shouldBeTrue
import org.testng.annotations.Test
import kotlin.test.assertTrue

class ShoppingCartTests : BaseTest() {

    @Test(priority = 0)
    fun `Verify user can add a product in the shopping cart`() {
        homePage.tapOnSearchButton()
        searchPage.searchProduct("Bershka jeans")

        searchPage.selectProduct()
        productPage.addProductToBag()

        assertTrue(shoppingCartPage.isCorrectAddedSizeDisplayedOnCart(productPage.getProductSize()), "Correct size added to the shopping cart correctly")
    }

    @Test(priority = 1, dependsOnMethods = ["Verify user can add a product in the shopping cart"])
    fun `Verify user can update the shopping cart`() {
        val productPrice = shoppingCartPage.getProductPrice()

        assertTrue(shoppingCartPage.verifyCartUpdate(productPrice).shouldBeTrue(), "Shopping cart is updated correctly")
    }

    // Due to extra layer of security on the portal
    // the test will only be asserting shopping cart updates
    // because login can't be reached
}
