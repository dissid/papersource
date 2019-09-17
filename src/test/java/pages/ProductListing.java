package pages;

import com.codeborne.selenide.CollectionCondition;

import static com.codeborne.selenide.Selenide.*;

public class ProductListing {

  public ProductListing openCollectionsSale() {
    open("/collections/sale");
    executeJavaScript("if (document.querySelector('.modals-overlay--welcome') !== null)" +
            "document.querySelector('.modals-overlay--welcome').click()");
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
