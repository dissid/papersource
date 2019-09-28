package pages;

import com.codeborne.selenide.Selenide;
import testConfig.Helpers;

import static com.codeborne.selenide.Selenide.$;

public class Home extends Helpers {

  public Home open() {
    Selenide.open("/");
    closeSubscriptionForm();
    return this;
  }

  public Home openSearching() {
    $("#search-icon").click();
    return this;
  }

  public Home search(String value) {
    $("#search-icon").setValue(value).pressEnter();
    return this;
  }
}