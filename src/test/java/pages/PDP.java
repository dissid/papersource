package pages;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import testConfig.Helpers;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.Wait;
import static org.openqa.selenium.By.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

class PDP extends Helpers {

  public PDP open(String path) {
    Selenide.open(path);
    closeSubscriptionForm();
    return this;
  }

  public PDP addToCart() {
    $("#product-addtocart-button").click();
    Wait().until(ExpectedConditions.textToBe(cssSelector("#product-addtocart-button"), "ADD TO CART"));
    return this;
  }
}
