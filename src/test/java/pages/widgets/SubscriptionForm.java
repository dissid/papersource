package pages.widgets;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class SubscriptionForm {

  public static void closeSubscriptionForm() {
    $(".modals-overlay--welcome").waitUntil(visible, 10000);
    executeJavaScript("document.querySelector('.modals-overlay--welcome').click()");
  }
}
