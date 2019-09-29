package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import testConfig.Helpers;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBe;

public class PDP extends Helpers {

  private SelenideElement loader = $(".gspinner");

  @Step("Open PDP of {path}")
  public PDP open(String path) {
    Selenide.open(path);
    closeSubscriptionForm();
    return this;
  }

  @Step("Click <Add to cart> button")
  public PDP addToCart() {
    $(".fotorama-item").shouldBe(visible);
    $("#product-addtocart-button").click();
    waitUntilProductAddedToCart();
    return this;
  }

  @Step("Click <Add to cart> button")
  public PDP addToCartSubscriptionProduct() {
    $(".options-list input").click();
    $("#product-addtocart-button").click();
    waitUntilProductAddedToCart();
    return this;
  }

  @Step
  public PDP selectQuantity(int value) {
    executeJavaScript("document.querySelector('.qtyTierPrice').style.display = 'block'");
    $(".qtyTierPrice").selectOptionByValue(Integer.toString(value));
    return this;
  }

  @Step
  public PDP assertPrice(String value) {
    $(".price-box>.tierPriceSelection").shouldHave(exactText("20 - " + value + " ($2.14 each)"));
    return this;
  }

  @Step
  public PDP selectCoordinateItems(String... items) {
    for (String item : items) {
      $$(".suite-items label").findBy(exactText(item)).waitUntil(visible, 15000).click();
    }
    return this;
  }

  @Step
  public PDP personalize() {
    $("button#product-continue-button").click();
    return this;
  }

  @Step
  public PDP next() {
    loader.waitUntil(disappear, 15000);
    $("iframe.cke_reset").waitUntil(visible, 15000);
    $(".gartner-btn.hide.btn-next").click();
    return this;
  }

  @Step
  public PDP nextReviewOrder() {
    loader.shouldBe(disappear);
    $(".btn-preview").click();
    return this;
  }

  @Step
  public PDP addToBag() {
    $("#reviewed-ok").click();
    $(".js-add-to-cart").waitUntil(visible, 15000).click();
    return this;
  }

  @Step
  public PDP continueToPDP() {
    $(".move-forward+.btn-next").click();
    return this;
  }

  @Step
  public PDP skipStep() {
    $(".suite-skip__link").shouldBe(visible).click();
    return this;
  }

  @Step
  public PDP assertCompleteMessage(String text) {
    $(".complete__title").shouldHave(exactText(text));
    return this;
  }

  @Step
  public PDP assertMiniCartSize(int value) {
    $(".counter-number").shouldHave(exactText(Integer.toString(value)));
    return this;
  }

  @Step
  private void waitUntilProductAddedToCart() {
    Wait().until(textToBe(cssSelector("#product-addtocart-button"), "ADD TO CART"));
  }
}
