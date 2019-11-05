package pages;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static pages.widgets.SubscriptionForm.closeSubscriptionForm;

public class Account{

  public final static String EMAIL = randomAlphabetic(8) + "@yopmail.com";

  @Step("Open My Account page")
  public Account open(){
    Selenide.open("/customer/account/");
    closeSubscriptionForm();
    return this;
  }

  @Step("Login by {email} - {password}")
  public Account givenOpenedMyAccount(String email, String password) {
    open();
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
