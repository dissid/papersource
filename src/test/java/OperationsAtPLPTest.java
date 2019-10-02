import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.PLP;
import testConfig.BaseTest;

class OperationsAtPLPTest extends BaseTest {

  private PLP PLP = new PLP();

  @Test
  @Tag("stage")
  @Tag("prod")
  void changingProductsPerPage() {
    PLP.givenOpenedCollectionsSale()
            .selectPerPage(96)
            .assertGridQuantity(96);
  }

  @Test
  @Tag("stage")
  @Tag("prod")
  void selectingFilter() {
    PLP.givenOpenedGiftHomeDrinkware()
            .selectFilterAndAssertCount("Sugarfina", 1)
            .assertGridQuantity(1)
            .assertResultNumber(1);
  }

  @Test
  @Tag("stage")
  @Tag("prod")
  void selectingSortByPrice() {
    PLP.givenOpenedStationerySetsWithSortedByPrice()
            .assertAscendingPriceFor(0, 1)
            .sortByDescending()
            .assertDescendingPriceFor(0, 1);
  }
}
