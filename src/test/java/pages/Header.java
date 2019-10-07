package pages;

import com.codeborne.selenide.SelenideElement;
import pages.widgets.CustomerMenu;
import pages.widgets.MiniCart;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class Header {

  private SelenideElement container = $("header");

  public CustomerMenu customerMenu() {
    return new CustomerMenu(container.find(".customer-welcome"));
  }

  public MiniCart miniCart() {
    return new MiniCart(container.find("[data-block='minicart']"));
  }

  private void openNewArrivals() {
    $("a[href*='collections/new']").shouldBe(visible).click();
  }
}
