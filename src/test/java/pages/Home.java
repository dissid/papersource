package pages;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static pages.widgets.SubscriptionForm.closeSubscriptionForm;

public class Home {
    @Step("Open Home page")
    public Home open() {
        Selenide.open("/");
        closeSubscriptionForm();
        return this;
    }

    @Step("Click Search icon")
    public Home activateSearching() {
        $("#search-icon").shouldHave(visible).click();
        return this;
    }

    @Step("Search query - {query}")
    public Home search(String query) {
        $("#search").setValue(query).pressEnter();
        return this;
    }
}