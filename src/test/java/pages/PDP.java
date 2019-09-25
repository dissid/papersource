package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import testConfig.Helpers;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.By.*;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBe;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class PDP extends Helpers {

  public PDP open(String path) {
    Selenide.open(path);
    closeSubscriptionForm();
    return this;
  }

  private SelenideElement image = $(".gpf-editor-frame");
  private SelenideElement loader = $(".gspinner");

  public PDP addToCart() {
    $(".fotorama-item").shouldBe(visible);
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
    executeJavaScript("document.querySelector('.qtyTierPrice').style.display = 'block'");
    $(".qtyTierPrice").selectOptionByValue(Integer.toString(value));
    return this;
  }

  public PDP assertPrice(String value) {
    $(".price-box>.tierPriceSelection").shouldHave(exactText("20 - " + value + " ($2.14 each)"));
    return this;
  }

  public PDP selectCoordinateItems(String... items) {
    for (String item : items) {
      $$(".suite-items label").findBy(exactText(item)).shouldBe(visible).click();
    }
    return this;
  }

  public PDP personalize() {
    $("button#product-continue-button").click();
    return this;
  }

  public PDP next() {
    $("iframe.cke_reset").waitUntil(visible, 7000);
    $(".gartner-btn.hide.btn-next").click();
    return this;
  }

  public PDP nextReviewOrder() {
    loader.shouldBe(disappear);
    $(".btn-preview").click();
    return this;
  }

  public PDP addToBag() {
    $("#reviewed-ok").click();
    $(".js-add-to-cart").waitUntil(visible, 10000).click();
    return this;
  }

  public PDP continueToPDP() {
    $(".move-forward+.btn-next").click();
    return this;
  }

  public PDP skipStep() {
    $(".suite-skip__link").shouldBe(visible).click();
    return this;
  }

  public PDP assertCompleteMessage(String text) {
    $(".complete__title").shouldHave(exactText(text));
    return this;
  }

  public PDP assertMiniCartSize(int value) {
    $(".counter-number").shouldHave(exactText(Integer.toString(value)));
    return this;
  }

  private void waitUntilProductAddedToCart() {
    Wait().until(textToBe(cssSelector("#product-addtocart-button"), "ADD TO CART"));
  }
}
