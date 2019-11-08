import pages.PDP;
import testConfig.BaseTest;
import testConfig.tags.All;
import testConfig.tags.Prod;
import testConfig.tags.Stage;

@All
class OperationsAtPDPTest extends BaseTest {

  private PDP pdp = new PDP();

  @Stage
  @Prod
  void selectingProductQuantity() {
    pdp.open("/sonora-response-card-w783-182781202202.html")
            .selectQuantity(20)
            .assertPrice("$42.80");
  }

  @Stage
  @Prod
  void addingSuiteProductToCart() {
    pdp.open("/sonora-response-card-w783-182781202202.html")
            .selectCoordinateItem("Stationery")
            .personalize()

            .next()
            .nextReviewOrder()
            .addToBag()
            .continueToPDP()

            .skipStep()
            .assertCompleteMessage("Congratulations! Your project is complete.")
            .assertMiniCartSize(200);
  }
}