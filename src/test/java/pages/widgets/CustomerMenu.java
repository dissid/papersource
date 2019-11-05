package pages.widgets;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CustomerMenu {

  private SelenideElement menu;

  public CustomerMenu(SelenideElement menu) {
    this.menu = menu;
  }

  @Step("Open Customer menu")
  public CustomerMenu open() {
    $("div.promo-banner").waitUntil(visible, 10000);
    //executeJavaScript("document.querySelector('div.promo-banner').style.display = 'none';");
    this.menu.click();
    return this;
  }

  @Step("Click <Logout> link")
  public void logout() {
    this.menu.find("[href*='logout']").click();
  }
}
