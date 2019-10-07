package pages.widgets;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.PDP;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;

public class MiniCart {

  private PDP pdp = new PDP();
  private SelenideElement container;

  public MiniCart(SelenideElement container) {
    this.container = container;
  }

  @Step("Open Mini Cart")
  private MiniCart open() {
    container.click();
    return this;
  }

  @Step("Open mini cart with product - {paths}")
  public MiniCart givenOpenedMiniCartWithProduct(String path) {
    pdp.open(path).addToCart();
    this.open();
    return this;
  }

  @Step("Set quantity - {qty}")
  public MiniCart setQty(int qty) {
    container.find(".item-qty").setValue(Integer.toString(qty));
    container.find(".update-cart-item").click();
    return this;
  }

  @Step("Delete product")
  public MiniCart delete() {
    container.find(".paper-trash").click();

    new ConfirmationDialog().confirm();
    return this;
  }

  @Step("Assert mini cart total - {count}")
  public void assertCountTotal(int count) {
    container.find(".count").shouldHave(exactText(Integer.toString(count)));
  }

  @Step("Assert message - {text}")
  public void assertMessage(String text) {
    container.find(".block-content").shouldHave(exactText(text));
  }

}
