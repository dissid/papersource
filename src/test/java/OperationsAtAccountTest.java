import pages.Account;
import pages.Registration;
import pages.YopMail;
import pages.Header;
import testConfig.BaseTest;
import testConfig.tags.Prod;
import testConfig.tags.Stage;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

class OperationsAtAccountTest extends BaseTest {

  private Registration registration = new Registration();
  private Header header = new Header();
  private YopMail yopMail = new YopMail();
  private Account account = new Account();

  @Stage
  void registration() {
    String email = randomAlphabetic(8) + "@yopmail.com";

    registration.givenOpenedRegistration()
            .setPersonal("Smoke", "Automation")
            .setSignIn(email, "Q1w2e3r4", "Q1w2e3r4")
            .create()
            .assertRedirectionTo("/customer/account/");

    header.customerMenu().open().logout();

  }

  @Stage
  void email() {
    yopMail.open()
            .loginBy(EMAIL)
            .assertGreeting("Welcome to Paper Source.");
  }

  @Stage
  @Prod
  void editing() {
    account.givenOpenedMyAccount(EMAIL, "Q1w2e3r4")
            .activeEditing()
            .edit("Smoke Edited", "Automation Edited")
            .submit()
            .assertAccount("Smoke Edited", "Automation Edited", EMAIL);

    header.customerMenu().open().logout();
  }
}
