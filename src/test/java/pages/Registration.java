package pages;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.jsReturnsValue;

public class Registration {

  public Registration givenOpenedRegistrationWithClosedSubscriptionForm() {
    open("/customer/account/create/");
    jsReturnsValue("document.querySelector('.modals-overlay--welcome').click()");
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

  public Registration setPersonalInfo(String firstName, String lastName) {
    $("#firstname").setValue(firstName);
    $("#lastname").setValue(lastName);
    return this;
  }

  public void assertRedirectionTo(String path) {
    assertTrue(url().contains(path));
  }
}
