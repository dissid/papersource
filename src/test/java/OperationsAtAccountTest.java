import org.junit.jupiter.api.Test;
import pages.Account;
import pages.Registration;
import pages.YopMail;
import testConfig.BaseTest;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

class OperationsAtAccountTest extends BaseTest {

  private Registration registration = new Registration();
  private YopMail yopMail = new YopMail();
  private Account account = new Account();


  @Test
  void registration() {
    String email = randomAlphabetic(8) + "@yopmail.com";
    registration
            .givenOpenedRegistration()
            .setPersonalInfo("Smoke", "Automation")
            .setSignInInfo(email, "Q1w2e3r4", "Q1w2e3r4")
            .create()
            .assertRedirectionTo("/customer/account/");
    yopMail.open(email)
            .assertGreeting("Welcome to Paper Source.");
  }

  @Test
  void editing() {
    account.givenOpenedMyAccount(EMAIL, "Q1w2e3r4")
            .activeEditing()
            .edit("Smoke Edited", "Automation Edited")
            .submit()
            .assertAccountInfo("Smoke Edited", "Automation Edited", EMAIL);
  }


}
