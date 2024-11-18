TC1 - Verify user can add a product in the shopping cart
Given the user has enter the Zalando home page
And the user select a product from the home page
When the user taps on the Add to bag button
Then the product is added successfully on the shopping cart

Preconditions: TC1
TC2 - Verify user can update the shopping carta
Given the user it's on the Shopping cart page and has a product in the cart
When the user update the shopping cart product
Then the product will be updated on the shopping cart

Preconditions: TC1
TC3 - Verify user can proceed to the checkout page
Given the user has a product on the shopping cart
When the user taps on the Go to checkout button
Then the user is redirected to the checkout page