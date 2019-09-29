package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import testConfig.Helpers;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ShoppingCart extends Helpers {

  private PDP pdp = new PDP();
  private SelenideElement qty = $("input[data-role='cart-item-qty']");
  private ElementsCollection shoppingCart = $$("#shopping-cart-table .cart.item");

  @Step("Open Shopping Cart page")
  public ShoppingCart open() {
    Selenide.open("/checkout/cart/");
    closeSubscriptionForm();
    return this;
  }

  public ShoppingCart givenOpenedShoppingCartWithProducts(String... paths) {
    for (String path : paths) {
      pdp.open(path).addToCart();
    }
    this.open();
    return this;
  }

  @Step
  private ShoppingCart givenOpenedShoppingCartWithSubscriptionProduct(String path) {
    pdp.open(path).addToCartSubscriptionProduct();
    this.open();
    return this;
  }

  @Step("Open PDP of {path} with discount code - {code}")
  public ShoppingCart givenOpenedShoppingCartWithDiscountCodeForSubscriptionProduct(String code, String path) {
    givenOpenedShoppingCartWithSubscriptionProduct(path);
    expandDiscountBlock();
    apply(code);
    return this;
  }

  @Step("Expand Estimate Shipping and Tax block")
  public ShoppingCart expandEstimateShippingAndTaxBlock() {
    $("#block-shipping .paper-plus").click();
    return this;
  }

  @Step("Set quantity - {value}")
  public ShoppingCart setQty(int value) {
    qty.setValue(Integer.toString(value));
    return this;
  }

  @Step("Click <Update Cart> button")
  public ShoppingCart updateCart() {
    $("button.update").click();
    return this;
  }

  @Step("Delete product by index - {index}")
  public ShoppingCart delete(int index) {
    shoppingCart.get(index).find(".action-delete").click();
    return this;
  }

  @Step("Entering destination country - {country} state - {state} zipCode - {zipCode}")
  public ShoppingCart enterDestination(String country, String state, String zipCode) {
    $("select[name='country_id']").selectOption(country);
    $("select[name='region_id']").selectOption(state);
    $("input[name='postcode']").sendKeys(zipCode);
    return this;
  }

  @Step("Expand Discount block")
  public ShoppingCart expandDiscountBlock() {
    $(".cart-discount>div[role='tab']").click();
    return this;
  }

  @Step("Apply discount code - {code}")
  public ShoppingCart apply(String code) {
    $("#coupon_code").setValue(code);
    $("button.apply").click();
    return this;
  }

  @Step("Remove discount code")
  public ShoppingCart remove() {
    $("button.cancel").click();
    return this;
  }

  @Step("Assert discount value - {value} in Order Summary")
  public ShoppingCart assertOrderSummaryDiscount(String value) {
    $("td[data-th='Discount']").shouldHave(exactText(value));
    return this;
  }

  @Step("Assert delivery method - {method} and price - {price}")
  public ShoppingCart assertDelivery(String method, String price) {
    $$("input[name='estimate_method']+label").findBy(exactText(method + " " + price)).shouldBe(visible);
    return this;
  }

  @Step("Assert quantity - {value} in Shopping Cart")
  public ShoppingCart assertQty(int value) {
    qty.shouldHave(exactValue(Integer.toString(value)));
    return this;
  }

  @Step("Assert size - {value} in Mini Cart")
  public ShoppingCart assertMiniCartSize(int value) {
    $(".counter-number").waitUntil(visible, 15000).shouldHave(exactText(Integer.toString(value)));
    return this;
  }

  @Step("Assert price - {price} and subtotal - {subtotal} in Shopping Cart")
  public ShoppingCart assertPriceAndSubtotal(String price, String subtotal) {
    $(".price .cart-price").shouldHave(exactText(price));
    $(".subtotal .cart-price").shouldHave(exactText(subtotal));
    return this;
  }

  @Step("Assert message - {text}")
  public ShoppingCart assertMessage(String text) {
    $(".cart-empty p:first-child").shouldHave(exactText(text));
    return this;
  }

  @Step("Assert subtotal - {subtotal} and order total - {orderTotal}")
  public ShoppingCart assertSubtotalAndOrderTotal(String subtotal, String orderTotal) {
    $("td[data-th='Subtotal']").shouldHave(exactText(subtotal));
    $("td[data-th='Order Total']").shouldHave(exactText(orderTotal));
    return this;
  }
}
