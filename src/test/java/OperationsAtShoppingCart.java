import org.junit.jupiter.api.Test;
import pages.ShoppingCart;
import testConfig.BaseTest;

public class OperationsAtShoppingCart extends BaseTest {

  private ShoppingCart cart = new ShoppingCart();

  @Test
  void updating() {
    cart.openShoppingCartWithProducts("/muse-volcano-candle-10007904.html")
            .setQty(2)
            .updateCart()
            .assertQty(2)
            .assertMiniCartSize(2)
            .assertPriceAndSubtotal("$36.95", "$73.90");
  }

  @Test
  void deleting() {
    cart.openShoppingCartWithProducts("/playful-puppy-plush-10003557.html", "/canvas-wine-bag-10000015.html")
            .delete(1)
            .assertMiniCartSize(1)
            .delete(0)
            .assertMessage("You have no items in your shopping bag.");
  }

  @Test
  void estimateShippingAndTax() {
    cart.openShoppingCartWithProducts("/canvas-wine-bag-10000015.html")
            .expandEstimateShippingAndTaxBlock()
            .enterDestination("Canada", "Ontario", "M4B 1B3")
            .assertMethod("International Economy", "$40");
  }
}