package pages.widgets;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

public class CustomerMenu {

  private SelenideElement menu;

  CustomerMenu(SelenideElement menu) {
    this.menu = menu;
  }

  @Step("Open Customer menu")
  public CustomerMenu open() {
    this.menu.click();
    return this;
  }

  @Step("Click <Logout> link")
  public void logout() {
    this.menu.find("[href*='logout']").click();
  }
}
