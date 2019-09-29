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

  @Step("Click <Edit> link")
  public Account activeEditing() {
    $(".block-dashboard-info .edit").click();
    return this;
  }

  @Step("Editing first name - {firstName} and last name - {lastName}")
  public Account edit(String firstName, String lastName) {
    $("#firstname").setValue(firstName);
    $("#lastname").setValue(lastName);
    return this;
  }

  @Step("Click <Submit> button")
  public Account submit() {
    $(".form-edit-account button[type='submit']").click();
    return this;
  }

  @Step("Click <Logout> link")
  public void logout() {
    $(".page-header .customer-welcome-link").click();
    $(".page-header a[href*='logout']").click();
  }

  @Step("Assert first name - {firstName} last name - {lastName} email - {email}")
  public Account assertAccountInfo(String firstName, String lastName, String email) {
    $$(".box-information .box-content").shouldHave(exactTexts(firstName + " " + lastName + " " + email));
    return this;
  }
}
