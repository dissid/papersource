package testConfig;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.hasWebDriverStarted;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.openqa.selenium.support.ui.ExpectedConditions.jsReturnsValue;

public class BaseTest {

  @BeforeAll
  public static void setup() {
    Configuration.baseUrl = System.getProperty("selenide.baseUrl", "https://staging.papersource.com/");
    Configuration.timeout = 10000;
    RestAssured.baseURI = "https://staging.papersource.com";
    Configuration.startMaximized = true;
    Configuration.fastSetValue = true;
    SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
  }

  @BeforeEach
  public void clearBrowserLocalStorageAndCookies() {
    if (hasWebDriverStarted()) {
      clearBrowserCookies();
      clearBrowserLocalStorage();
      refresh();
      clearBrowserLocalStorage();
      clearBrowserCookies();
    }
  }
}
