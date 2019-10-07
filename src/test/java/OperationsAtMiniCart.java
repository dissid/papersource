import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.Header;
import testConfig.BaseTest;

class OperationsAtMiniCart extends BaseTest {

  private Header header = new Header();

  @Test
  @Tag("stage")
  @Tag("prod")
  void editingQuantity() {
    header.miniCart().givenOpenedMiniCartWithProduct("/flourishing-roses-wreath-kit-10007825.html")
            .setQty(2)
            .assertCountTotal(2);
  }

  @Test
  @Tag("stage")
  @Tag("prod")
  void deleting() {
    header.miniCart().givenOpenedMiniCartWithProduct("/wine-and-naps-pouch-10007635.html")
            .delete()
            .assertMessage("You have no items in your shopping cart.");
  }
}
