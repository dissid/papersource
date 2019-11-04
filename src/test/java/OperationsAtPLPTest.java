import pages.PLP;
import testConfig.BaseTest;
import testConfig.tags.All;
import testConfig.tags.Prod;
import testConfig.tags.Stage;

@All
class OperationsAtPLPTest extends BaseTest {

  private PLP plp = new PLP();

  @Stage
  @Prod
  void changingProductsPerPage() {
    plp.givenOpenedCollectionsSale()
            .selectPerPage(96)
            .assertGridQuantity(96);
  }

  @Stage
  @Prod
  void selectingFilter() {
    plp.givenOpenedGiftHomeDrinkware()
            .selectFilterAndAssertCount("Sugarfina", 1)
            .assertGridQuantity(1)
            .assertResultNumber(1);
  }

  @Stage
  @Prod
  void selectingSortByPrice() {
    plp.givenOpenedStationerySetsWithSortedByPrice()
            .assertAscendingPriceFor(0, 1)
            .sortByDescending()
            .assertDescendingPriceFor(0, 1);
  }
}
