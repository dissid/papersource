import org.junit.jupiter.api.Test;
import testConfig.BaseTest;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class OrderTest extends BaseTest {
  @Test
  void placingOrderByGuest() {
    open("/");

    closeSubscriptionForm();

    openNewArrivals();

    openFirstProduct();
    addToCart();

    openMiniCart();
    openShoppingCart();
    openCheckoutShipping();

    setUserInfo("temp_email@gorilla.ua", "Automation", "Test");
    setCompany("Gorilla Group");
    setStreets("111 W Jackson Blvd Fl 3", "222 W Jackson Blvd Fl 3");
    setCity("Chicago");
    selectState("Illinois");
    setZipCode(60604);
    selectCountry("United States");
    setPhone(1234567890);
    selectShippingMethod("Next Day");
    openCheckoutPayment();

    setCreditCardNumber(4111111111111111L);
    setExpiration("12 - December", 2029);
    setCVV(111);

    placeOrder();
    assertOrderNumberNotEmpty();


  }

  private void assertOrderNumberNotEmpty(){
    $(".order-info span").shouldBe(not(empty));
  }

  private void placeOrder() {
    $("button[data-bind*='placeOrder']").click();
  }

  private void setCreditCardNumber(long number) {
    $("input[name='payment[cc_number]']").setValue(Long.toString(number));
  }

  private void setExpiration(String month, int year) {
    $("select[name='payment[cc_exp_month]']").selectOption(month);
    $("select[name='payment[cc_exp_year]']").selectOption(Integer.toString(year));

  }

  private void setCVV(int code) {
    $("input[name='payment[cc_cid]']").setValue(Integer.toString(code));
  }

  private void openCheckoutPayment() {
    $("button[data-role='opc-continue']").click();
  }

  private void selectShippingMethod(String shippingMethod) {
    $$(".table-checkout-shipping-method td").findBy(exactText(shippingMethod)).click();
  }

  private void setUserInfo(String email, String firstname, String lastname) {
    $("#customer-EMAIL").setValue(email);
    $("input[name='firstname']").setValue(firstname);
    $("input[name='lastname']").setValue(lastname);
  }

  private void setCompany(String name) {
    $("input[name='company']").setValue(name);
  }

  private void setStreets(String street1, String street2) {
    $("input[name='street[0]']").setValue(street1);
    $("input[name='street[1]']").setValue(street2);
  }

  private void setCity(String city) {
    $("input[name='city']").setValue(city);
  }

  private void selectState(String state) {
    $("select[name='region_id']").selectOption(state);
  }

  private void setZipCode(int zipCode) {
    $("input[name='postcode']").setValue(Integer.toString(zipCode));
  }

  private void selectCountry(String country) {
    $("select[name='country_id']").selectOption(country);
  }

  private void setPhone(int phone) {
    $("input[name='telephone']").setValue(Integer.toString(phone));
  }

  private void openCheckoutShipping() {
    $("button.checkout").click();
  }

  private void openShoppingCart() {
    $("a[href*='cart']>button").click();
  }

  private void openMiniCart() {
    $(".showcart").click();
  }

  private void addToCart() {
    $("#product-addtocart-button").click();
  }

  private void openFirstProduct() {
    $(".product-items>li:first-child").shouldBe(visible).click();
  }

  private void openNewArrivals() {
    $("a[href*='collections/new']").click();
  }

  private void closeSubscriptionForm() {
    $("aside[data-type='slide'] .action-close").click();
  }
}
