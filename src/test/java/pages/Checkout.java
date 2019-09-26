package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.junit.jupiter.api.Assertions.*;

public class Checkout {

  private PDP pdp = new PDP();
  private SelenideElement orderNumber = $(".order-info>strong");

  public Checkout open() {
    Selenide.open("/checkout/");
    return this;
  }

  public Checkout givenOpenedCheckoutShippingWithProducts(String... sku) {
    for (String item : sku) {
      pdp.open(item).addToCart();
    }
    this.open();
    return this;
  }

  public Checkout setUserInfo(String email, String firstName, String lastName) {
    $("#customer-email").setValue(email);
    $("input[name='firstname']").setValue(firstName);
    $("input[name='lastname']").setValue(lastName);
    return this;
  }

  public Checkout signIn(String email, String password) {
    $("#customer-email").setValue(email).click(100, 100);
    $("#customer-password").waitUntil(visible, 8000).setValue(password);
    $("button.login").click();
    $(".loader").waitUntil(disappear, 10000);
    return this;
  }

  public Checkout setCompany(String name) {
    $("input[name='company']").setValue(name);
    return this;
  }

  public Checkout setStreets(String street1, String street2) {
    $("input[name='street[0]']").setValue(street1);
    $("input[name='street[1]']").setValue(street2);
    return this;
  }

  public Checkout setCity(String city) {
    $("input[name='city']").setValue(city);
    return this;
  }

  public Checkout selectState(String state) {
    $("select[name='region_id']").selectOption(state);
    return this;
  }

  public Checkout setZipCode(int zipCode) {
    $("input[name='postcode']").setValue(Integer.toString(zipCode));
    return this;
  }

  public Checkout selectCountry(String country) {
    $("select[name='country_id']").selectOption(country);
    return this;
  }

  public Checkout setPhone(int phone) {
    $("input[name='telephone']").setValue(Integer.toString(phone));
    return this;
  }

  public Checkout selectShippingMethod(String shippingMethod) {
    $$(".table-checkout-shipping-method td").findBy(exactText(shippingMethod)).click();
    return this;
  }

  public Checkout openCheckoutPayment() {
    $("button[data-role='opc-continue']").click();
    return this;
  }

  public Checkout setCreditCard(long number, int month, int year, int cvv) {
    $("input[name='payment[cc_number]']").setValue(Long.toString(number));
    $("select[name='payment[cc_exp_month]']").selectOptionByValue(Integer.toString(month));
    $("select[name='payment[cc_exp_year]']").selectOption(Integer.toString(year));
    $("input[name='payment[cc_cid]']").setValue(Integer.toString(cvv));
    return this;
  }

  public Checkout applyDiscount(String code) {
    $("#discount-code").setValue(code).pressEnter();
    return this;
  }

  public Checkout placeOrder() {
    $("button[data-bind*='placeOrder']").click();
    return this;
  }

  public void assertOrderNumberNotEmpty() {
    orderNumber.shouldBe(visible);
    assertEquals(orderNumber.getText()
            .substring(orderNumber.getText().length() - 12, orderNumber.getText().length() - 1).length(), 11);
  }

  public Checkout assertOrderSummaryDiscount(String value) {
    $(".discount .price").shouldBe(visible).shouldHave(exactText(value));
    return this;
  }
}

