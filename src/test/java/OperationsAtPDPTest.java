import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.PDP;
import testConfig.BaseTest;

class OperationsAtPDPTest extends BaseTest {

  private PDP pdp = new PDP();

  @Test
  @Tag("stage")
  @Tag("prod")
  void selectingProductQuantity() {
    pdp.open("/sonora-response-card-w783-182781202202.html")
            .selectQuantity(20)
            .assertPrice("$42.80");
  }

  @Test
  @Tag("stage")
  @Tag("prod")
  void addingSuiteProductToCart() {
    pdp.open("/sonora-response-card-w783-182781202202.html")
            .selectCoordinateItem("Invitation")
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