import io.qameta.allure.Description;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.Account;
import pages.Registration;
import pages.YopMail;
import pages.Header;
import testConfig.BaseTest;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

class OperationsAtAccountTest extends BaseTest {

  private Registration registration = new Registration();
  private Header header = new Header();
  private YopMail yopMail = new YopMail();
  private Account account = new Account();


  @Test
  @Tag("stage")
  @Tag("prod")
  @Description("Test Description: Login test with wrong username and wrong password.")
  void registration() {
    String email = randomAlphabetic(8) + "@yopmail.com";

    registration.givenOpenedRegistration()
            .setPersonal("Smoke", "Automation")
            .setSignIn(email, "Q1w2e3r4", "Q1w2e3r4")
            .create()
            .assertRedirectionTo("/customer/account/");

    header.customerMenu().open().logout();

  }

  @Disabled("Email is not sent")
  @Test
  void email() {
    yopMail.open()
            .loginBy(EMAIL)
            .assertGreeting("Welcome to Paper Source.");
  }

  @Test
  @Tag("stage")
  @Tag("prod")
  void editing() {
    account.givenOpenedMyAccount(EMAIL, "Q1w2e3r4")
            .activeEditing()
            .edit("Smoke Edited", "Automation Edited")
            .submit()
            .assertAccount("Smoke Edited", "Automation Edited", EMAIL);

    header.customerMenu().open().logout();
  }
}
