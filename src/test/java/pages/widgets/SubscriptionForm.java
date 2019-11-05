package pages.widgets;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.jsReturnsValue;

public class SubscriptionForm {

  public static void closeSubscriptionForm() {
    $(".modals-overlay--welcome").waitUntil(Condition.visible, 10000);
    executeJavaScript("document.querySelector('.modals-overlay--welcome').click()");
  }
}
