import pages.Header;
import testConfig.BaseTest;
import testConfig.tags.All;
import testConfig.tags.Prod;
import testConfig.tags.Stage;

@All
class OperationsAtMiniCartTest extends BaseTest {

  private Header header = new Header();

  @Stage
  @Prod
  void editingQuantity() {
    header.miniCart().givenOpenedMiniCartWithProduct("/flourishing-roses-wreath-kit-10007825.html")
            .setQty(2)
            .assertCountTotal(2);
  }

  @Stage
  @Prod
  void deleting() {
    header.miniCart().givenOpenedMiniCartWithProduct("/wine-and-naps-pouch-10007635.html")
            .delete()
            .assertMessage("You have no items in your shopping cart.");
  }
}
