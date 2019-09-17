package pages;

import com.codeborne.selenide.CollectionCondition;

import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.jsReturnsValue;

public class ProductListing {

  public ProductListing openCollectionsSale() {
    open("/collections/sale");
    jsReturnsValue("document.querySelector('.modals-overlay--welcome').click()");
    return this;
  }

  public ProductListing changeLimiterTo(int count) {
    $("#authenticationPopup+.toolbar .control").click();
    $("a[rel='" + count + "']").click();
    return this;
  }

  public ProductListing assertProductsCount(int count) {
    $$(".product-items>li").shouldBe(CollectionCondition.size(count));
    return this;
  }

}
