package pages;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.jsReturnsValue;

public class CheckoutShipping {

  private PDP pdp = new PDP();

  @Step("Open CheckoutShipping Shipping page")
  public CheckoutShipping open() {
    Selenide.open("/checkout/");
    return this;
  }

  @Step("Open CheckoutShipping Shipping for product - {sku}")
  public CheckoutShipping givenOpenedCheckoutShippingWithProducts(String... sku) {
    for (String item : sku) {
      pdp.open(item).addToCart();
    }
    this.open();
    return this;
  }

  @Step("Fill user info email - {email} first name - {firstName} last name - {lastName}")
  public CheckoutShipping setUserInfo(String email, String firstName, String lastName) {
    $("#customer-email").setValue(email);
    $("input[name='firstname']").setValue(firstName);
    $("input[name='lastname']").setValue(lastName);
    return this;
  }

  @Step("Sign in by email - {email} password - {password}")
  public CheckoutShipping signIn(String email, String password) {
    $("#customer-email").setValue(email).pressEnter();
    $("#customer-password").shouldBe(visible).setValue(password);
    $("button.login").click();
    $(".loader").waitUntil(disappear, 15000);
    Wait().until(jsReturnsValue("return document.readyState === 'complete'"));
    return this;
  }

  @Step("Fill Company - {name}")
  public CheckoutShipping setCompany(String name) {
    $("input[name='company']").setValue(name);
    return this;
  }

  @Step("Fill street1 - {street1} street2 - {street2}")
  public CheckoutShipping setStreets(String street1, String street2) {
    $("input[name='street[0]']").setValue(street1);
    $("input[name='street[1]']").setValue(street2);
    return this;
  }

  @Step("Fill city - {city}")
  public CheckoutShipping setCity(String city) {
    $("input[name='city']").setValue(city);
    return this;
  }

  @Step("Fill state - {state}")
  public CheckoutShipping selectState(String state) {
    $("select[name='region_id']").selectOption(state);
    return this;
  }

  @Step("Fill zip code - {zipCode}")
  public CheckoutShipping setZipCode(int zipCode) {
    $("input[name='postcode']").setValue(Integer.toString(zipCode));
    return this;
  }

  @Step("Fill country - {country}")
  public CheckoutShipping selectCountry(String country) {
    $("select[name='country_id']").selectOption(country);
    return this;
  }

  @Step("Fill phone - {phone}")
  public CheckoutShipping setPhone(int phone) {
    $("input[name='telephone']").setValue(Integer.toString(phone));
    return this;
  }

  @Step("Select shipping method - {shippingMethod}")
  public CheckoutShipping selectShippingMethod(String shippingMethod) {
    $$(".table-checkout-shipping-method td").findBy(exactText(shippingMethod)).click();
    return this;
  }

  @Step("Open CheckoutShipping Payment page")
  public CheckoutPayment next() {
    $("button[data-role*='continue']").click();
    return new CheckoutPayment();
  }
}

