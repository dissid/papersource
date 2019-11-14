package testConfig;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.hasWebDriverStarted;

public class BaseTest {

  @BeforeAll
  public static void setup() {
    Configuration.driverManagerEnabled = false;
    Configuration.remote = "http://172.20.104.16:4444/wd/hub";
    Configuration.baseUrl = System.getProperty("selenide.baseUrl", "https://staging.papersource.com/");
    Configuration.timeout = 10000;
    RestAssured.baseURI = Configuration.baseUrl;
    Configuration.startMaximized = true;
    Configuration.fastSetValue = true;
    Configuration.browser = "chrome";
    SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
  }

  @BeforeEach
  public void clearBrowserLocalStorageAndCookies() {
    if (hasWebDriverStarted()) {
      clearBrowserCookies();
      clearBrowserLocalStorage();
      refresh();
      clearBrowserCookies();
      clearBrowserLocalStorage();
    }
  }
}
