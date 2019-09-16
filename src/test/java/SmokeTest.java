import org.junit.jupiter.api.Test;
import pages.YopMail;
import pages.Registration;
import testConfig.BaseTest;

import static org.apache.commons.lang3.RandomStringUtils.*;

public class SmokeTest extends BaseTest {

  private final static String EMAIL = randomAlphabetic(8) + "@yopmail.com";
  private Registration registration = new Registration();
  private YopMail yopMail = new YopMail();


  @Test
  void createAccount() {
    registration
            .givenOpenedRegistrationWithClosedSubscriptionForm()
            .setPersonalInfo("Smoke", "Automation")
            .setSignInInfo(EMAIL, "Q1w2e3r4", "Q1w2e3r4")
            .create()
            .assertRedirectionToMyAccount();
    yopMail.open(EMAIL)
            .assertGreeting("Welcome to Paper Source.");
  }


}
