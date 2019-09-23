package pages;

import com.codeborne.selenide.Selenide;
import testConfig.Helpers;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBe;

public class PDP extends Helpers {

  public PDP open(String path) {
    Selenide.open(path);
    closeSubscriptionForm();
    return this;
  }

  public PDP addToCart() {
    $("#product-addtocart-button").click();
    waitUntilProductAddedToCart();
    return this;
  }

  public PDP addToCartSubscriptionProduct() {
    $(".options-list input").click();
    $("#product-addtocart-button").click();
    waitUntilProductAddedToCart();
    return this;
  }

  public PDP selectQuantity(int value) {
    $($(".qty .control")).click();
    $("a[rel='" + value + "']").click();
    return this;
  }

  public PDP assertPrice(String value) {
    $(".price-box>.tierPriceSelection").shouldHave(exactText("20 - " + value + " ($2.14 each)"));
    return this;
  }

  public PDP assertMiniCartSize(int value) {
    $(".counter-number").shouldHave(exactText(Integer.toString(value)));
    return this;
  }

  public PDP selectCoordinateItems(String... items) {
    for (String item : items) {
      $$(".suite-items label").findBy(exactText(item)).scrollIntoView(false).click();
    }
    return this;
  }

  public PDP personalize() {
    $("button#product-continue-button").click();
    return this;
  }

  public PDP next() {
    $(".gspinner").shouldBe(disappear);
    $(".cke_combo_button").shouldBe(visible);
    $(".gartner-btn.hide.btn-next").waitUntil(visible, 8000).click();
    return this;
  }

  public PDP nextReviewOrder() {
    $(".gspinner").shouldBe(disappear);
    $(".btn-preview").shouldBe(visible).click();
    return this;
  }

  public PDP addToBag() {
    $("#reviewed-ok").click();
    $("g.gpf-stock-image-element").shouldBe(visible);
    $(".js-add-to-cart").waitUntil(visible, 8000).click();
    return this;
  }

  public PDP continueToPDP() {
    $(".move-forward+.btn-next").click();
    return this;
  }

  public PDP skipStep() {
    $(".suite-skip__link").scrollIntoView(false).click();
    return this;
  }

  public PDP assertCompleteMessage(String text) {
    $(".complete__title").shouldHave(exactText(text));
    return this;
  }

  private void waitUntilProductAddedToCart() {
    Wait().until(textToBe(cssSelector("#product-addtocart-button"), "ADD TO CART"));
  }
}