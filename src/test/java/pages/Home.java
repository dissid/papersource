package pages;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.support.ui.ExpectedConditions.jsReturnsValue;

public class Home {

  public void openSite() {
    open("/");
    jsReturnsValue("document.querySelector('.modals-overlay--welcome').click()");
  }
}