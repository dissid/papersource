package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import testConfig.Helpers;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.jsReturnsValue;

public class ShoppingCart extends Helpers {

  private PDP pdp = new PDP();
  private SelenideElement qty = $("input[data-role='cart-item-qty']");

  public ShoppingCart open() {
    Wait().until(jsReturnsValue("document.querySelector('.counter-number').textContent !== 0"));
    Selenide.open("/checkout/cart/");
    return this;
  }

  public ShoppingCart openShoppingCartWithProduct(String sku) {
    pdp.open(sku).addToCart();
    this.open();
    return this;
  }

  public ShoppingCart setQty(int value) {
    qty.shouldBe(visible).setValue(Integer.toString(value));
    return this;
  }

  public ShoppingCart assertQty(int value) {
    qty.shouldHave(exactValue(Integer.toString(value)));
    return this;
  }

  public ShoppingCart updateCart() {
    $("button.update").click();
    return this;
  }

}
