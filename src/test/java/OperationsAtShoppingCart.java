import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.ShoppingCart;
import testConfig.BaseTest;

class OperationsAtShoppingCart extends BaseTest {

  private ShoppingCart cart = new ShoppingCart();

  @Test
  @Tag("stage")
  @Tag("prod")
  void editQuantity() {
    cart.givenOpenedShoppingCartWithProducts("/muse-volcano-candle-10007904.html")
            .setQty(2)
            .updateCart()
            .assertQty(2)
            .assertMiniCartSize(2)
            .assertPriceAndSubtotal("$36.95", "$73.90");
  }

  @Test
  @Tag("stage")
  @Tag("prod")
  void deleting() {
    cart.givenOpenedShoppingCartWithProducts("/playful-puppy-plush-10003557.html", "/canvas-wine-bag-10000015.html")
            .delete(1)
            .assertMiniCartSize(1)
            .delete(0)
            .assertMessage("You have no items in your shopping bag.");
  }

  @Test
  @Tag("stage")
  @Tag("prod")
  void estimateShippingAndTax() {
    cart.givenOpenedShoppingCartWithProducts("/canvas-wine-bag-10000015.html")
            .expandEstimateShippingAndTaxBlock()
            .enterDestination("United States", "Illinois", "60604")
            .assertDelivery("2nd Day", "$19.95");
  }

  @Test
  @Tag("stage")
  void applyDiscountCode() {
    cart.givenOpenedShoppingCartWithProducts("/woodland-babe-bunny-520172.html")
            .expandDiscountBlock()
            .apply("gorilla007")
            .assertOrderSummaryDiscount("-$1.00");
  }

  @Test
  @Tag("stage")
  void removeDiscountCode() {
    cart.givenOpenedShoppingCartWithDiscountCodeForSubscriptionProduct("gorilla007",
            "/paper-source-subscription-box-10008860.html")
            .expandDiscountBlock()
            .remove()
            .assertSubtotalAndOrderTotal("$49.95", "$49.95");
  }
}