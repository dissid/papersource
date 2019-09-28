package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class MiniCart {

  private PDP pdp = new PDP();

  private MiniCart show() {
    $("a.showcart").click();
    return this;
  }
  @Step
  public MiniCart givenOpenedMiniCartWithProducts(String... paths) {
    for (String path : paths) {
      pdp.open(path).addToCart();
    }
    show();
    return this;
  }
  @Step
  public MiniCart setQty(int qty) {
    $(".details-qty input").setValue(Integer.toString(qty));
    $(".update-cart-item").click();
    return this;
  }
  @Step
  public MiniCart delete() {
    $(".paper-trash").click();
    $(".action-accept").waitUntil(visible, 8000).click();
    return this;
  }
  @Step
  public void assertCountTotal(int count) {
    $(".items-total>.count").shouldHave(Condition.exactText(Integer.toString(count)));
  }
  @Step
  public void assertMessage(String text) {
    $(".block-content>.empty").shouldBe(visible).shouldHave(exactText(text));
  }

}
