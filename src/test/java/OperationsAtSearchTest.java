import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.Home;
import pages.PDP;
import pages.SRP;
import testConfig.BaseTest;

class OperationsAtSearchTest extends BaseTest {

  private Home home = new Home();
  private PDP pdp = new PDP();
  private SRP srp = new SRP();

  @Test
  @Tag("prod")
  void searchBySKU() {
    home.open()
            .activateSearching()
            .search("10007616");
    pdp.assertSKU(10007616);
  }

  @Test
  @Tag("prod")
  void sortByPriceSearchResultPage() {
    srp.givenSearchResultPageFor("Book")
            .selectSortBy("Price (high to low)")
            .assertDescendingPriceFor(1, 2);
  }
}