import org.junit.jupiter.api.Test;
import pages.ShoppingCart;
import testConfig.BaseTest;

public class OperationsAtShoppingCart extends BaseTest {

  private ShoppingCart cart = new ShoppingCart();

  @Test
  void updating() {
    cart.openShoppingCartWithProduct("10007904")
            .setQty(2)
            .updateCart()
            .assertQty(2);
  }
}