import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.ProductListing;
import testConfig.BaseTest;

class OperationsAtPLPTest extends BaseTest {

  private ProductListing productListing = new ProductListing();

  @Test
  @Tag("stage")
  @Tag("prod")
  void changingProductsPerPage() {
    productListing
            .givenOpenedCollectionsSale()
            .selectPerPage(96)
            .assertGridQuantity(96);
  }

  @Test
  @Tag("stage")
  @Tag("prod")
  void selectingFilter() {
    productListing
            .givenOpenedGiftHomeDrinkware()
            .selectFilterAndAssertCount("Sugarfina", 1)
            .assertGridQuantity(1)
            .assertResultNumber(1);
  }

  @Test
  @Tag("stage")
  @Tag("prod")
  void selectingSortByPrice() {
    productListing
            .givenOpenedStationerySetsWithSortedByPrice()
            .assertAscendingPriceFor(0, 1)
            .sortByDescending()
            .assertDescendingPriceFor(0, 1);
  }
}
