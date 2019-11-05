package pages.widgets;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;

public class CustomerMenu {

  private SelenideElement menu;

  public CustomerMenu(SelenideElement menu) {
    this.menu = menu;
  }

  @Step("Open Customer menu")
  public CustomerMenu open() {
    this.menu.waitUntil(visible, 5000).click();
    return this;
  }

  @Step("Click <Logout> link")
  public void logout() {
    this.menu.find("[href*='logout']").click();
  }
}
