import api.AccountApi;
import org.junit.jupiter.api.BeforeAll;
import pages.Account;
import pages.Header;
import pages.Registration;
import pages.YopMail;
import testConfig.BaseTest;
import testConfig.tags.All;
import testConfig.tags.Prod;
import testConfig.tags.Stage;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static pages.Account.EMAIL;

@All
class OperationsAtAccountTest extends BaseTest {

  private Registration registration = new Registration();
  private YopMail yopMail = new YopMail();
  private Account account = new Account();

  @BeforeAll
  static void givenUserAccount() {
    AccountApi.createAccount();
  }

  @Stage
  void registration() {
    String email = randomAlphabetic(8) + "@yopmail.com";

    registration.open()
            .setPersonal("Smoke", "Automation")
            .setSignIn(email, "Q1w2e3r4", "Q1w2e3r4")
            .create()
            .assertRedirectionTo("/customer/account/");
  }

  @Stage
  @Prod
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

    new Header().customerMenu().open().logout();
  }
}
