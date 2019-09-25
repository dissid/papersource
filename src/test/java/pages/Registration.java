package pages;

import testConfig.Helpers;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Registration extends Helpers {

  public Registration givenOpenedRegistration() {
    open("/customer/account/create/");
    return this;
  }

  public Registration create() {
    $("button[title*='Create']").click();
    return this;
  }

  public Registration setSignInInfo(String email, String password, String confirmPassword) {
    closeSubscriptionForm();
    $("#email_address").setValue(email);
    $("#password").setValue(password);
    $("#password-confirmation").setValue(confirmPassword);
    return this;
  }

  public Registration setPersonalInfo(String firstName, String lastName) {
    $("#firstname").setValue(firstName);
    $("#lastname").setValue(lastName);
    return this;
  }

  public void assertRedirectionTo(String path) {
    assertTrue(url().contains(path));
  }
}
