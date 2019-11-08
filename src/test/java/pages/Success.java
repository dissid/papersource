package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Success {

  private SelenideElement orderNumber = $(".order-info>strong");

  @Step("Assert that order number is not empty")
  public void assertOrderNumberNotEmpty() {
    orderNumber.shouldBe(visible);
    assertEquals(orderNumber.waitUntil(visible,60000).getText()
            .substring(orderNumber.getText().length() - 12, orderNumber.getText().length() - 1).length(), 11);
  }
}
