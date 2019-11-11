import pages.ShoppingCart;
import testConfig.BaseTest;
import testConfig.tags.All;
import testConfig.tags.Prod;
import testConfig.tags.Stage;

@All
class OperationsAtShoppingCartTest extends BaseTest {

  private ShoppingCart cart = new ShoppingCart();

  @Stage
  @Prod
  void editQuantity() {
    cart.givenOpenedShoppingCartWithProducts("/pencil-nose-game-10007940.html")
            .setQty(2)
            .updateCart()
            .assertQty(2)
            .assertMiniCartSize(2)
            .assertPriceAndSubtotal("$26.95", "$53.90");
  }

  @Stage
  @Prod
  void deleting() {
    cart.givenOpenedShoppingCartWithProducts("/little-nibble-bunny-10007288.html")
            .delete(0)
            .assertMessage("You have no items in your shopping bag.");
  }

  @Stage
  @Prod
  void estimateShippingAndTax() {
    cart.givenOpenedShoppingCartWithProducts("/squishamal-scavenger-hunt-10008254.html")
            .expandEstimateShippingAndTaxBlock()
            .estimateFor("United States", "Illinois", "60604")
            .assertDelivery("2nd Day", "$19.95");
  }

  @Stage
  void applyDiscountCode() {
    cart.givenOpenedShoppingCartWithProducts("/wrapples-assortment-10007897.html")
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