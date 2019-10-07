package pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class CheckoutPayment {

  @Step("Assert discount - {value} in Order Summary")
  public CheckoutPayment assertOrderSummaryDiscount(String value) {
    $(".discount .price").shouldBe(visible).shouldHave(exactText(value));
    return this;
  }

  @Step("Fill credit card - {number} {month} {year} {cvv}")
  public CheckoutPayment setCreditCard(long number, int month, int year, int cvv) {
    $("[name*='[cc_number]']").setValue(Long.toString(number));
    $("[name*='[cc_exp_month]']").selectOptionByValue(Integer.toString(month));
    $("[name*='[cc_exp_year]']").selectOption(Integer.toString(year));
    $("[name*='[cc_cid]']").setValue(Integer.toString(cvv));
    return this;
  }

  @Step("Apply discount - {code}")
  public CheckoutPayment applyDiscount(String code) {
    $("#discount-code").setValue(code).pressEnter();
    return this;
  }

  @Step("Click <Place Order> button")
  public Success placeOrder() {
    $("[data-bind*='placeOrder']").click();
    return new Success();
  }

}
