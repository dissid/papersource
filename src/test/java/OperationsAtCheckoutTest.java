import org.junit.jupiter.api.Test;
import pages.Checkout;
import testConfig.BaseTest;

class OperationsAtCheckoutTest extends BaseTest {

  private Checkout checkout = new Checkout();

  @Test
  void placingOrderWithDiscountByGuest() {
    checkout.givenOpenedCheckoutShippingWithProducts("/sunset-tote-bag-10007616.html")

            .setUserInfo(EMAIL, "Automation", "Test")
            .setStreets("111 W Jackson Blvd Fl 3", "")
            .setCity("Chicago")
            .selectState("Illinois")
            .setZipCode(60604)
            .selectCountry("United States")
            .setPhone(1234567890)
            .selectShippingMethod("2nd Day")
            .openCheckoutPayment()

            .setCreditCard(5555444433331111L, 12, 2029, 111)
            .applyDiscount("gorilla007")
            .assertOrderSummaryDiscount("-$1.00")
            .placeOrder()
            .assertOrderNumberNotEmpty();
  }

  @Test
  void placingOrderByLoggedIn() {
    checkout.givenOpenedCheckoutShippingWithProducts("/travel-diffuser-with-oil-10007666.html")

            .signIn(EMAIL, "Q1w2e3r4")
            .setStreets("111 W Jackson", "222 W Jackson")
            .setCompany("Gorilla Group")
            .setCity("New York")
            .selectState("New York")
            .setZipCode(10005)
            .selectCountry("United States")
            .setPhone(1234567890)
            .selectShippingMethod("Ground (1-5 business days)")
            .openCheckoutPayment()

            .setCreditCard(4111111111111111L, 12, 2029, 111)
            .placeOrder()
            .assertOrderNumberNotEmpty();
  }

}
