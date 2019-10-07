package pages;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import testConfig.Helpers;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;

public class Account extends Helpers {

  @Step("Open My Account page")
  public Account open(){
    Selenide.open("/customer/account/");
    return this;
  }

  @Step("Login by {email} - {password}")
  public Account givenOpenedMyAccount(String email, String password) {
    open();
    closeSubscriptionForm();
    $("#email").setValue(email);
    $("#pass").setValue(password);
    $("#send2").click();
    return this;
  }

  @Step("Click <Edit> link")
  public Account activeEditing() {
    $(".block-dashboard-info").find(".edit").click();
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
    $(".form-edit-account").find("[type='submit']").click();
    return this;
  }

  @Step("Assert first name - {firstName} last name - {lastName} email - {email}")
  public Account assertAccount(String firstName, String lastName, String email) {
    $(".box-information").find(".box-content")
            .shouldHave(exactText(firstName + " " + lastName + " " + email));
    return this;
  }
}
