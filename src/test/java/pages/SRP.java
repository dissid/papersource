package pages;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.Double.parseDouble;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static pages.widgets.SubscriptionForm.closeSubscriptionForm;

public class SRP {

  private Home home = new Home();
  private ElementsCollection productGrid = $$(".product-items>li");

  @Step("Open Search Result page for query - {query}")
  public SRP givenSearchResultPageFor(String query) {
    home.open().activateSearching().search(query);
    return this;
  }

  @Step("Select sort by {value}")
  public SRP selectSortBy(String value) {
    executeJavaScript("document.querySelector('#sorter').style.display = 'block'");
    $("#sorter").selectOption(value);
    return this;
  }

  @Step("Assert products price from high to low for products index - {firstProductIndex} and {secondProductIndex}")
  public SRP assertDescendingPriceFor(int firstProductIndex, int secondProductIndex) {
    assertTrue(getPriceFor(firstProductIndex) > getPriceFor(secondProductIndex));
    return this;
  }

  private double getPriceFor(int productIndex) {
    String price = productGrid.get(productIndex).find(".price").shouldHave(visible).getText();
    return parseDouble(price.replace("$", ""));
  }
}
