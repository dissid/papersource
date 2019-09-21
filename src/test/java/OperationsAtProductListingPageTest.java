import org.junit.jupiter.api.Test;
import pages.ProductListing;
import testConfig.BaseTest;

class OperationsAtProductListingPageTest extends BaseTest {

  private ProductListing productListing = new ProductListing();

  @Test
  void changingProductsPerPage() {
    productListing
            .givenOpenedCollectionsSale()
            .selectPerPage(96)
            .assertGridQuantity(96);
  }

  @Test
  void selectingFilter() {
    productListing
            .givenOpenedGiftHomeDrinkware()
            .selectFilterAndAssertCount("Sugarfina", 1)
            .assertGridQuantity(1)
            .assertResultNumber(1);
  }

  @Test
  void selectingSortByPrice() {
    productListing
            .givenOpenedStationerySetsWithSortedByPrice()
            .assertAscendingPriceFor(0, 1)
            .sortByDescending()
            .assertDescendingPriceFor(0, 1);
  }

}
