package pages;

import com.codeborne.selenide.Selenide;
import testConfig.Helpers;

import static com.codeborne.selenide.Selenide.$;

class PDP extends Helpers {

  public PDP open(String sku) {
    Selenide.open("muse-volcano-candle-" + sku + ".html");
    closeSubscriptionForm();
    return this;
  }

  public PDP addToCart() {
    $("#product-addtocart-button").click();
    return this;
  }
}
