package pages;

import com.codeborne.selenide.ElementsCollection;
import org.junit.jupiter.api.Assertions;
import testConfig.Helpers;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.Double.parseDouble;

public class ProductListing extends Helpers {

  private ElementsCollection toolBar = $$("#authenticationPopup+.toolbar div");
  private ElementsCollection productGrid = $$(".product-items>li");

  public ProductListing givenOpenedCollectionsSale() {
    open("/collections/sale");
    closeSubscriptionForm();
    return this;
  }

  public ProductListing givenOpenedGiftHomeDrinkware() {
    open("/gifts/home/drinkware");
    closeSubscriptionForm();
    return this;
  }

  public ProductListing givenOpenedStationerySetsWithSortedByPrice() {
    open("/stationery/correspondence/sets?product_list_order=price");
    closeSubscriptionForm();
    return this;
  }

  public ProductListing selectFilterAndAssertCount(String value, int quantity) {
    toolBar.findBy(cssClass("filters-amount")).click();
    $$(".filter-options-content a").findBy(matchesText(value + " " + quantity)).click();
    return this;
  }

  public void assertResultNumber(int quantity) {
    toolBar.findBy(cssClass("toolbar-amount")).find(".toolbar-number").shouldHave(exactText(Integer.toString(quantity)));
  }

  public ProductListing selectPerPage(int quantity) {
    executeJavaScript("document.querySelector(\"select[data-role='limiter']\").style.display = 'block'");
    toolBar.findBy(cssClass("control")).find(".limiter-options").selectOptionByValue(Integer.toString(quantity));
    return this;
  }

  public ProductListing assertGridQuantity(int quantity) {
    productGrid.shouldBe(size(quantity));
    return this;
  }

  public ProductListing assertAscendingPriceFor(int firstProductIndex, int secondProductIndex) {
    Assertions.assertTrue(getPriceFor(firstProductIndex) < getPriceFor(secondProductIndex));
    return this;
  }

  public ProductListing assertDescendingPriceFor(int firstProductIndex, int secondProductIndex) {
    Assertions.assertTrue(getPriceFor(firstProductIndex) > getPriceFor(secondProductIndex));
    return this;
  }

  public ProductListing sortByDescending() {
    toolBar.findBy(cssClass("toolbar-sorter")).find("a[data-value='desc']").click();
    return this;
  }

  private double getPriceFor(int productIndex) {
    String price = productGrid.get(productIndex).find(".price").shouldHave(visible).getText();
    return parseDouble(price.replace("$", ""));
  }
}