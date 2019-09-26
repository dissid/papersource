import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.MiniCart;
import testConfig.BaseTest;

class OperationsAtMiniCart extends BaseTest {

  private MiniCart miniCart = new MiniCart();

  @Test
  @Tag("stage")
  @Tag("prod")
  void editingQuantity() {
    miniCart.givenOpenedMiniCartWithProducts("/flourishing-roses-wreath-kit-10007825.html")
            .setQty(2)
            .assertCountTotal(2);
  }

  @Test
  @Tag("stage")
  @Tag("prod")
  void deleting() {
    miniCart.givenOpenedMiniCartWithProducts("/wine-and-naps-pouch-10007635.html")
            .delete()
            .assertMessage("You have no items in your shopping cart.");
  }
}
