import org.junit.jupiter.api.Test;
import pages.Account;
import pages.YopMail;
import pages.Registration;
import testConfig.BaseTest;

import static org.apache.commons.lang3.RandomStringUtils.*;

class OperationsAtAccountTest extends BaseTest {

  private final static String EMAIL = randomAlphabetic(8) + "@yopmail.com";
  private Registration registration = new Registration();
  private YopMail yopMail = new YopMail();
  private Account account = new Account();


  @Test
  void createAccount() {
    registration
            .givenOpenedRegistrationWithClosedSubscriptionForm()
            .setPersonalInfo("Smoke", "Automation")
            .setSignInInfo(EMAIL, "Q1w2e3r4", "Q1w2e3r4")
            .create()
            .assertRedirectionTo("/customer/account/");
    yopMail.open(EMAIL)
            .assertGreeting("Welcome to Paper Source.");
  }

  @Test
  void editAccount() {
    account.givenOpenedMyAccountWithLoggedIn()
            .openEditing()
            .edit("Smoke Edited", "Automation Edited")
            .submit()
            .assertAccountInfo("Smoke Edited", "Automation Edited", "postman@gorillagroup.com");
  }


}
