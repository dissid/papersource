package pages.widgets;

import static com.codeborne.selenide.Selenide.Wait;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static org.openqa.selenium.support.ui.ExpectedConditions.jsReturnsValue;

public class SubscriptionForm {

  public static void closeSubscriptionForm() {
    Wait().until(jsReturnsValue("return document.readyState === 'complete'"));
    executeJavaScript("if (document.querySelector('.modals-overlay--welcome') !== null)" +
            "document.querySelector('.modals-overlay--welcome').click()");
  }
}
