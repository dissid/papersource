package pages.widgets;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class Header {

  private SelenideElement container = $("header");

  public CustomerMenu customerMenu() {
    return new CustomerMenu(container.find(".customer-welcome"));
  }
}
