import api.AccountApi;
import org.junit.jupiter.api.BeforeAll;
import pages.CheckoutShipping;
import pages.widgets.ConfirmationDialog;
import testConfig.BaseTest;
import testConfig.tags.All;
import testConfig.tags.Prod;
import testConfig.tags.Stage;

import static pages.Account.EMAIL;
import static pages.Account.PASSWORD;

@All
class OperationsAtCheckoutTest extends BaseTest {

  private CheckoutShipping checkoutShipping = new CheckoutShipping();

  @BeforeAll
  static void givenUserAccount() {
    AccountApi.createAccount();
  }

  @Stage
  void placingOrderWithDiscountByGuest() {
    checkoutShipping.givenOpenedCheckoutShippingWithProducts("/sunset-tote-bag-10007616.html")

            .setUserInfo(EMAIL, "Automation", "Test")
            .setStreets("111 W Jackson Blvd Fl 3", "")
            .setCity("Chicago")
            .selectState("Illinois")
            .setZipCode(60604)
            .selectCountry("United States")
            .setPhone(1234567890)
            .selectShippingMethod("2nd Day")
            .next()

            .setCreditCard(5555444433331111L, 12, 2029, 111)
            .applyDiscount("gorilla007")
            .assertOrderSummaryDiscount("-$1.00")
            .placeOrder()

            .assertOrderNumberNotEmpty();
  }

  @Stage
  void placingOrderByLoggedIn() {
    checkoutShipping.givenOpenedCheckoutShippingWithProducts("/travel-diffuser-with-oil-10007666.html")

            .signIn(EMAIL, PASSWORD)
            .setStreets("111 W Jackson", "222 W Jackson")
            .setCompany("Gorilla Group")
            .setCity("New York")
            .selectState("New York")
            .setZipCode(10005)
            .selectCountry("United States")
            .setPhone(1234567890)
            .selectShippingMethod("Ground (1-5 business days)")
            .next()

            .setCreditCard(4111111111111111L, 12, 2029, 111)

            .placeOrder()
            .assertOrderNumberNotEmpty();
  }

  @Prod
  void placingOrderOnProductionByGuest() {
    checkoutShipping.givenOpenedCheckoutShippingWithProducts("/travel-diffuser-with-oil-10007666.html")

            .setUserInfo(EMAIL, "Automation", "Test")
            .setStreets("111 W Jackson", "222 W Jackson")
            .setCompany("Gorilla Group")
            .setCity("New York")
            .selectState("New York")
            .setZipCode(10005)
            .selectCountry("United States")
            .setPhone(1234567890)
            .selectShippingMethod("Ground (1-5 business days)")
            .next()

            .setCreditCard(4111111111111111L, 12, 2029, 111)
            .placeOrder();

    new ConfirmationDialog().checkModalText("First Data Gateway: Invalid CC Number");
  }

  @Prod
  void placingOrderOnProductionByLoggedIn() {
    checkoutShipping.givenOpenedCheckoutShippingWithProducts("/travel-diffuser-with-oil-10007666.html")

            .signIn(EMAIL, "Q1w2e3r4")
            .setStreets("111 W Jackson", "222 W Jackson")
            .setCompany("Gorilla Group")
            .setCity("New York")
            .selectState("New York")
            .setZipCode(10005)
            .selectCountry("United States")
            .setPhone(1234567890)
            .selectShippingMethod("Ground (1-5 business days)")
            .next()

            .setCreditCard(4111111111111111L, 12, 2029, 111)

            .placeOrder();
    new ConfirmationDialog().checkModalText("First Data Gateway: Invalid CC Number");
  }

}
