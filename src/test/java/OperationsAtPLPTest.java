import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.PLP;
import testConfig.BaseTest;
import testConfig.tags.Prod;
import testConfig.tags.Stage;

class OperationsAtPLPTest extends BaseTest {

  private PLP PLP = new PLP();

  @Stage
  @Prod
  void changingProductsPerPage() {
    PLP.givenOpenedCollectionsSale()
            .selectPerPage(96)
            .assertGridQuantity(96);
  }

  @Stage
  @Prod
  void selectingFilter() {
    PLP.givenOpenedGiftHomeDrinkware()
            .selectFilterAndAssertCount("Sugarfina", 1)
            .assertGridQuantity(1)
            .assertResultNumber(1);
  }

  @Stage
  @Prod
  void selectingSortByPrice() {
    PLP.givenOpenedStationerySetsWithSortedByPrice()
            .assertAscendingPriceFor(0, 1)
            .sortByDescending()
            .assertDescendingPriceFor(0, 1);
  }
}
