package pages;

import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Registration {

  public Registration givenOpenedRegistrationWithClosedSubscriptionForm(){
    open("/customer/account/create/");
    $("aside[data-type='slide'] .action-close").click();
    return this;
  }

  public Registration create() {
    $("button[title*='Create']").click();
    return this;
  }

  public Registration setSignInInfo(String email, String password, String confirmPassword) {
    $("#email_address").setValue(email);
    $("#password").setValue(password);
    $("#password-confirmation").setValue(confirmPassword);
    return this;
  }

  public Registration setPersonalInfo(String firstname, String lastname) {
    $("#firstname").setValue(firstname);
    $("#lastname").setValue(lastname);
    return this;
  }

  public void assertRedirectionToMyAccount() {
    assertTrue(url().contains("/customer/account/"));
  }
}
