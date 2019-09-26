package pages;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class MiniCart {

  private PDP pdp = new PDP();

  private MiniCart show() {
    $("a.showcart").click();
    return this;
  }

  public MiniCart givenOpenedMiniCartWithProducts(String... paths) {
    for (String path : paths) {
      pdp.open(path).addToCart();
    }
    show();
    return this;
  }

  public MiniCart setQty(int qty) {
    $(".details-qty input").setValue(Integer.toString(qty));
    $(".update-cart-item").click();
    return this;
  }

  public MiniCart delete() {
    $(".paper-trash").click();
    $(".action-accept").waitUntil(visible, 8000).click();
    return this;
  }

  public void assertCountTotal(int count) {
    $(".items-total>.count").shouldHave(Condition.exactText(Integer.toString(count)));
  }

  public void assertMessage(String text) {
    $(".block-content>.empty").shouldHave(exactText(text));
  }

}
