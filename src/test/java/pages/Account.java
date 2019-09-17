package pages;

import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Selenide.*;

public class Account {

  public Account givenOpenedMyAccountWithLoggedIn() {
    open("/customer/account/");
    executeJavaScript("if (document.querySelector('.modals-overlay--welcome') !== null)" +
            "document.querySelector('.modals-overlay--welcome').click()");

    $("#email").setValue("postman@gorillagroup.com");
    $("#pass").setValue("Q1w2e3r4");
    $("input[name='persistent_remember_me']").click();
    $("#send2").click();
    return this;
  }

  public Account openEditing() {
    $(".block-dashboard-info .edit").click();
    return this;
  }

  public Account edit(String firstName, String lastName) {
    $("#firstname").setValue(firstName);
    $("#lastname").setValue(lastName);
    return this;
  }

  public Account submit() {
    $(".form-edit-account button[type='submit']").click();
    return this;
  }

  public void assertAccountInfo(String firstName, String lastName, String email) {
    $$(".box-information .box-content").shouldHave(exactTexts(firstName + " " + lastName + " " + email));
  }
}
