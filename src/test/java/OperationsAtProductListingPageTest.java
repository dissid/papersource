import org.junit.jupiter.api.Test;
import pages.ProductListing;
import testConfig.BaseTest;

class OperationsAtProductListingPageTest extends BaseTest {

  private ProductListing productListing = new ProductListing();

  @Test
  void changeLimiter() {
    productListing
            .openCollectionsSale()
            .changeLimiterTo(96)
            .assertProductsCount(96);

  }

}
