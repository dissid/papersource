package pages;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import testConfig.Helpers;

import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Selenide.*;

public class Account extends Helpers {

  @Step("Login by {email} - {password}")
  public Account givenOpenedMyAccount(String email, String password) {
    open("/customer/account/");
    $("#email").setValue(email);
    closeSubscriptionForm();
    $("#pass").setValue(password);
    $("input[name='persistent_remember_me']").click();
    $("#send2").click();
    return this;
  }

  @Step
  public Account activeEditing() {
    $(".block-dashboard-info .edit").click();
    return this;
  }

  @Step
  public Account edit(String firstName, String lastName) {
    $("#firstname").setValue(firstName);
    $("#lastname").setValue(lastName);
    return this;
  }

  @Step
  public Account submit() {
    $(".form-edit-account button[type='submit']").click();
    return this;
  }

  @Step
  public void logout() {
    $(".page-header .customer-welcome-link").click();
    $(".page-header a[href*='logout']").click();
  }

  @Step
  public Account assertAccountInfo(String firstName, String lastName, String email) {
    $$(".box-information .box-content").shouldHave(exactTexts(firstName + " " + lastName + " " + email));
    return this;
  }
}
