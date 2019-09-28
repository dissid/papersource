package pages;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import testConfig.Helpers;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Registration extends Helpers {

  @Step("Open Account Registration page")
  public Registration givenOpenedRegistration() {
    open("/customer/account/create/");
    return this;
  }

  @Step("Click <Create> button")
  public Registration create() {
    $("button[title*='Create']").click();
    return this;
  }

  @Step("Set sign info: Email - {email}, Pass - {password}, Confirm Pass - {confirmPassword}")
  public Registration setSignInInfo(String email, String password, String confirmPassword) {
    closeSubscriptionForm();
    $("#email_address").setValue(email);
    $("#password").setValue(password);
    $("#password-confirmation").setValue(confirmPassword);
    return this;
  }

  @Step("Set personal info: {fistName}, {lastName}")
  public Registration setPersonalInfo(String firstName, String lastName) {
    $("#firstname").setValue(firstName);
    $("#lastname").setValue(lastName);
    return this;
  }

  @Step
  public void logout() {
    $(".page-header .customer-welcome-link").click();
    $(".page-header a[href*='logout']").click();
  }

  @Step("Assert redirection to My Account page")
  public Registration assertRedirectionTo(String path) {
    assertTrue(url().contains(path));
    return this;
  }

}
