package pages;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.Double.parseDouble;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static pages.widgets.SubscriptionForm.closeSubscriptionForm;

public class PLP {

  private ElementsCollection toolBar = $$("#authenticationPopup+.toolbar div");
  private ElementsCollection productGrid = $$(".product-items>li");

  @Step("Open collection Sale")
  public PLP givenOpenedCollectionsSale() {
    open("/collections/sale");
    closeSubscriptionForm();
    return this;
  }

  @Step("Open Gift Home Drinkware page")
  public PLP givenOpenedGiftHomeDrinkware() {
    open("/gifts/home/drinkware");
    closeSubscriptionForm();
    return this;
  }

  @Step("Open sorted by price Stationery Sets page with ")
  public PLP givenOpenedStationerySetsWithSortedByPrice() {
    open("/stationery/correspondence/sets?product_list_order=price");
    closeSubscriptionForm();
    return this;
  }

  @Step("Select filter - {value} with quantity - {quantity}")
  public PLP selectFilterAndAssertCount(String value, int quantity) {
    closeSubscriptionForm();
    closeSubscriptionForm();
    toolBar.findBy(cssClass("filters-amount")).click();
    $$(".filter-options-content a").findBy(matchesText(value + " " + quantity)).click();
    return this;
  }

  @Step("Assert products count - {quantity}")
  public void assertResultNumber(int quantity) {
    toolBar.findBy(cssClass("toolbar-amount")).find(".toolbar-number").shouldHave(exactText(Integer.toString(quantity)));
  }

  @Step("Select View Per Page - {quantity}")
  public PLP selectPerPage(int quantity) {
    executeJavaScript("document.querySelector(\"select[data-role='limiter']\").style.display = 'block'");
    toolBar.findBy(cssClass("control")).find(".limiter-options").selectOptionByValue(Integer.toString(quantity));
    return this;
  }

  @Step("Sort by descending")
  public PLP sortByDescending() {
    toolBar.findBy(cssClass("toolbar-sorter")).find("a[data-value='desc']").click();
    return this;
  }

  @Step("Assert grid quantity - {quantity}")
  public PLP assertGridQuantity(int quantity) {
    productGrid.shouldBe(size(quantity));
    return this;
  }

  @Step("Assert price from low to high for products index {firstProductIndex} and {secondProductIndex}")
  public PLP assertAscendingPriceFor(int firstProductIndex, int secondProductIndex) {
    assertTrue(getPriceFor(firstProductIndex) < getPriceFor(secondProductIndex));
    return this;
  }

  @Step("Assert price from high to low for products index {firstProductIndex} and {secondProductIndex}")
  public PLP assertDescendingPriceFor(int firstProductIndex, int secondProductIndex) {
    assertTrue(getPriceFor(firstProductIndex) > getPriceFor(secondProductIndex));
    return this;
  }

  private double getPriceFor(int productIndex) {
    String price = productGrid.get(productIndex).find(".price").shouldHave(visible).getText();
    return parseDouble(price.replace("$", ""));
  }
}