package tests.orderTests

import base.BaseTest
import org.testng.annotations.Test

class OrderTests : BaseTest() {
    @Test(priority = 0)
    fun `Verify user can add a product in the shopping cart`() {
        homePage.tapOnShoppingCartButton()
    }

    @Test(priority = 1)
    fun `Verify user can update the shopping cart`() {
    }

    @Test(priority = 2)
    fun `Verify user can proceed to the checkout page`() {
    }
}
