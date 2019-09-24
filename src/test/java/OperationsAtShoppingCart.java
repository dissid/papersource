import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import pages.ShoppingCart;
import testConfig.BaseTest;

class OperationsAtShoppingCart extends BaseTest {

  private ShoppingCart cart = new ShoppingCart();

  @Test
  void updating() {
    cart.givenOpenedShoppingCartWithProducts("/muse-volcano-candle-10007904.html")
            .setQty(2)
            .updateCart()
            .assertQty(2)
            .assertMiniCartSize(2)
            .assertPriceAndSubtotal("$36.95", "$73.90");
  }

  @Test
  void deleting() {
    cart.givenOpenedShoppingCartWithProducts("/playful-puppy-plush-10003557.html", "/canvas-wine-bag-10000015.html")
            .delete(1)
            .assertMiniCartSize(1)
            .delete(0)
            .assertMessage("You have no items in your shopping bag.");
  }

  @Test
  void estimateShippingAndTax() {
    cart.givenOpenedShoppingCartWithProducts("/canvas-wine-bag-10000015.html")
            .expandEstimateShippingAndTaxBlock()
            .enterDestination("Canada", "Ontario", "M4B 1B3")
            .assertDelivery("International Economy", "$40");
  }

  @Test
  void applyDiscountCode() {
    cart.givenOpenedShoppingCartWithProducts("/woodland-babe-bunny-520172.html")
            .expandDiscountBlock()
            .apply("gorilla007")
            .assertOrderSummaryDiscount("-$1.00")
            .assertSubtotalAndOrderTotal("$25.95", "$24.95");
  }

  @Test
  void removeDiscountCode() {
    cart.givenOpenedShoppingCartWithDiscountCodeForSubscriptionProduct("gorilla007",
            "/paper-source-subscription-box-10008860.html")
            .expandDiscountBlock()
            .remove()
            .assertSubtotalAndOrderTotal("$49.95", "$49.95");
  }
}