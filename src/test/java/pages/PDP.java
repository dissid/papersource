package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBe;
import static pages.widgets.SubscriptionForm.closeSubscriptionForm;

public class PDP {

    private SelenideElement loader = $(".gspinner");

    @Step("Open PDP of {path}")
    public PDP open(String path) {
        Selenide.open(path);
        executeJavaScript("if(document.querySelector('.modals-overlay--welcome') !== null)" +
                "document.querySelector('.modals-overlay--welcome').click()");
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

    @Step("Select quantity - {value}")
    public PDP selectQuantity(int value) {
        executeJavaScript("document.querySelector('.qtyTierPrice').style.display = 'block'");
        $(".qtyTierPrice").selectOptionByValue(Integer.toString(value));
        return this;
    }

    @Step("Assert price - {value}")
    public PDP assertPrice(String value) {
        $(".price-box>.tierPriceSelection").shouldHave(exactText("20 - " + value + " ($2.14 each)"));
        return this;
    }

    @Step("Select Coordinate item - {items}")
    public PDP selectCoordinateItem(String item) {
        $$(".suite-items label").findBy(exactText(item)).waitUntil(visible, 15000).click();
        return this;
    }

    @Step("Click <Personalize> button")
    public PDP personalize() {
        $("button#product-continue-button").click();
        return this;
    }

    @Step("Click <Next> button")
    public PDP next() {
        loader.waitUntil(disappear, 60000);
        $("iframe.cke_reset").waitUntil(visible, 60000);
        $(".gartner-btn.hide.btn-next").click();
        return this;
    }

    @Step("Click <Next:Review Order> button")
    public PDP nextReviewOrder() {
        loader.shouldBe(disappear);
        $(".btn-preview").click();
        return this;
    }

    @Step("Click <Add To Bag> button")
    public PDP addToBag() {
        $("#reviewed-ok").click();
        $(".js-add-to-cart").waitUntil(visible, 15000).click();
        return this;
    }

    @Step("Click <Continue> button")
    public PDP continueToPDP() {
        $(".move-forward+.btn-next").click();
        return this;
    }

    @Step("Click <Skip Step> link")
    public PDP skipStep() {
        $(".suite-skip__link").shouldBe(visible).click();
        return this;
    }

    @Step("Assert message - {text}")
    public PDP assertCompleteMessage(String text) {
        $(".complete__title").shouldHave(exactText(text));
        return this;
    }

    @Step("Assert mini cart quantity - {value}")
    public PDP assertMiniCartSize(int value) {
        $(".counter-number").shouldHave(exactText(Integer.toString(value)));
        return this;
    }

    @Step("Assert SKU - {value}")
    public void assertSKU(int value) {
        $("div[itemprop=sku]").shouldHave(exactText(Integer.toString(value)));
    }

    private void waitUntilProductAddedToCart() {
        Wait().until(textToBe(cssSelector("#product-addtocart-button"), "ADD TO CART"));
    }
}
