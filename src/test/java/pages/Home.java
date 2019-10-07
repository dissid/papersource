package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import org.openqa.selenium.support.ui.ExpectedConditions;
import testConfig.Helpers;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.Wait;

public class Home extends Helpers {
  @Step("Open Home page")
  public Home open() {
    Selenide.open("/");
    closeSubscriptionForm();
    return this;
  }

  @Step("Click Search icon")
  public Home activateSearching() {
    closeSubscriptionForm();
    $("#search-icon").shouldHave(visible).click();
    return this;
  }

  @Step("Search query - {query}")
  public Home search(String query) {
    $("#search").setValue(query).pressEnter();
    return this;
  }
}