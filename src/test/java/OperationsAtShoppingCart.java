import pages.ShoppingCart;
import testConfig.BaseTest;
import testConfig.tags.All;
import testConfig.tags.Prod;
import testConfig.tags.Stage;

@All
class OperationsAtShoppingCart extends BaseTest {

  private ShoppingCart cart = new ShoppingCart();

  @Stage
  @Prod
  void editQuantity() {
    cart.givenOpenedShoppingCartWithProducts("/muse-volcano-candle-10007904.html")
            .setQty(2)
            .updateCart()
            .assertQty(2)
            .assertMiniCartSize(2)
            .assertPriceAndSubtotal("$36.95", "$73.90");
  }

  @Stage
  @Prod
  void deleting() {
    cart.givenOpenedShoppingCartWithProducts("/playful-puppy-plush-10003557.html", "/canvas-wine-bag-10000015.html")
            .delete(1)
            .assertMiniCartSize(1)
            .delete(0)
            .assertMessage("You have no items in your shopping bag.");
  }

  @Stage
  @Prod
  void estimateShippingAndTax() {
    cart.givenOpenedShoppingCartWithProducts("/canvas-wine-bag-10000015.html")
            .expandEstimateShippingAndTaxBlock()
            .estimateFor("United States", "Illinois", "60604")
            .assertDelivery("2nd Day", "$19.95");
  }

  @Stage
  void applyDiscountCode() {
    cart.givenOpenedShoppingCartWithProducts("/woodland-babe-bunny-520172.html")
            .expandDiscountBlock()
            .apply("gorilla007")
            .assertOrderSummaryDiscount("-$1.00");
  }

  @Stage
  void removeDiscountCode() {
    cart.givenOpenedShoppingCartWithDiscountCodeForSubscriptionProduct("gorilla007",
            "/paper-source-subscription-box-10008860.html")
            .expandDiscountBlock()
            .remove()
            .assertSubtotalAndOrderTotal("$49.95", "$49.95");
  }
}