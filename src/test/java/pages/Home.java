package pages;

import testConfig.Helpers;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.open;

public class Home extends Helpers {

  public void openSite() {
    open("/");
    closeSubscriptionForm();
  }
}