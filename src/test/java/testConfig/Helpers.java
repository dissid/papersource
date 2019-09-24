package testConfig;

import static com.codeborne.selenide.Selenide.executeJavaScript;

public class Helpers {

  public void closeSubscriptionForm() {
    executeJavaScript("if (document.querySelector('.modals-overlay--welcome') !== null)" +
            "document.querySelector('.modals-overlay--welcome').click()");
  }
}
