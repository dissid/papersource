import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.Home;
import pages.PDP;
import pages.SRP;
import testConfig.BaseTest;
import testConfig.tags.Prod;
import testConfig.tags.Stage;

class OperationsAtSearchTest extends BaseTest {

  private Home home = new Home();
  private PDP pdp = new PDP();
  private SRP srp = new SRP();

  @Stage
  @Prod
  void searchBySKU() {
    home.open()
            .activateSearching()
            .search("10007667");
    pdp.assertSKU(10007667);
  }

  @Stage
  @Prod
  void sortByPriceSearchResultPage() {
    srp.givenSearchResultPageFor("Book")
            .selectSortBy("Price (high to low)")
            .assertDescendingPriceFor(1, 2);
  }
}