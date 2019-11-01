package pages;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static pages.widgets.SubscriptionForm.closeSubscriptionForm;

public class Registration {

  @Step("Open Account Registration page")
  public Registration open() {
    Selenide.open("/customer/account/create/");
    closeSubscriptionForm();
    return this;
  }

  @Step("Click <Create> button")
  public Registration create() {
    $("button[title*='Create']").click();
    return this;
  }

  @Step("Set sign info: Email - {email} Pass - {password} Confirm Pass - {confirmPassword}")
  public Registration setSignIn(String email, String password, String confirmPassword) {
    closeSubscriptionForm();
    $("#email_address").setValue(email);
    $("#password").setValue(password);
    $("#password-confirmation").setValue(confirmPassword);
    return this;
  }

  @Step("Set personal info: Name - {firstName} Last Name - {lastName}")
  public Registration setPersonal(String firstName, String lastName) {
    $("#firstname").setValue(firstName);
    $("#lastname").setValue(lastName);
    return this;
  }

  @Step("Assert redirection to My Account page")
  public Registration assertRedirectionTo(String path) {
    assertTrue(url().contains(path));
    return this;
  }
}
