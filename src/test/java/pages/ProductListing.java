package pages;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import testConfig.Helpers;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.Double.parseDouble;

public class ProductListing extends Helpers {

  private ElementsCollection toolBar = $$("#authenticationPopup+.toolbar div");
  private ElementsCollection productGrid = $$(".product-items>li");

  @Step
  public ProductListing givenOpenedCollectionsSale() {
    open("/collections/sale");
    closeSubscriptionForm();
    closeSubscriptionForm();
    return this;
  }

  @Step
  public ProductListing givenOpenedGiftHomeDrinkware() {
    open("/gifts/home/drinkware");
    closeSubscriptionForm();
    return this;
  }

  @Step
  public ProductListing givenOpenedStationerySetsWithSortedByPrice() {
    open("/stationery/correspondence/sets?product_list_order=price");
    closeSubscriptionForm();
    return this;
  }

  @Step
  public ProductListing selectFilterAndAssertCount(String value, int quantity) {
    toolBar.findBy(cssClass("filters-amount")).click();
    $$(".filter-options-content a").findBy(matchesText(value + " " + quantity)).click();
    return this;
  }

  @Step
  public void assertResultNumber(int quantity) {
    toolBar.findBy(cssClass("toolbar-amount")).find(".toolbar-number").shouldHave(exactText(Integer.toString(quantity)));
  }

  @Step
  public ProductListing selectPerPage(int quantity) {
    executeJavaScript("document.querySelector(\"select[data-role='limiter']\").style.display = 'block'");
    toolBar.findBy(cssClass("control")).find(".limiter-options").selectOptionByValue(Integer.toString(quantity));
    return this;
  }

  @Step
  public ProductListing assertGridQuantity(int quantity) {
    productGrid.shouldBe(size(quantity));
    return this;
  }

  @Step
  public ProductListing assertAscendingPriceFor(int firstProductIndex, int secondProductIndex) {
    Assertions.assertTrue(getPriceFor(firstProductIndex) < getPriceFor(secondProductIndex));
    return this;
  }

  @Step
  public ProductListing assertDescendingPriceFor(int firstProductIndex, int secondProductIndex) {
    Assertions.assertTrue(getPriceFor(firstProductIndex) > getPriceFor(secondProductIndex));
    return this;
  }

  @Step
  public ProductListing sortByDescending() {
    toolBar.findBy(cssClass("toolbar-sorter")).find("a[data-value='desc']").click();
    return this;
  }

  @Step
  private double getPriceFor(int productIndex) {
    closeSubscriptionForm();
    String price = productGrid.get(productIndex).find(".price").shouldHave(visible).getText();
    return parseDouble(price.replace("$", ""));
  }
}