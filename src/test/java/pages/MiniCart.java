package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.Wait;
import static org.openqa.selenium.support.ui.ExpectedConditions.jsReturnsValue;

public class MiniCart {

  private PDP pdp = new PDP();

  private MiniCart open() {
    $("a.showcart").click();
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
    $(".details-qty input").setValue(Integer.toString(qty));
    $(".update-cart-item").click();
    return this;
  }

  @Step("Delete product")
  public MiniCart delete() {
    $(".paper-trash").click();
    return this;
  }

  @Step
  public MiniCart confirmDeletion() {
    Wait().until(jsReturnsValue("return document.readyState === 'complete'"));
    $(".action-accept").click();
    return this;
  }

  @Step("Assert mini cart total - {count}")
  public void assertCountTotal(int count) {
    $(".items-total>.count").shouldHave(Condition.exactText(Integer.toString(count)));
  }

  @Step("Assert message - {text}")
  public void assertMessage(String text) {
    $(".block-content>.empty").shouldHave(exactText(text));
  }

}
