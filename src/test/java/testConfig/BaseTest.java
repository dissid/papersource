package testConfig;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideDriver;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.clearBrowserCookies;
import static com.codeborne.selenide.Selenide.clearBrowserLocalStorage;
import static com.codeborne.selenide.WebDriverRunner.hasWebDriverStarted;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

public class BaseTest {

  protected final static String EMAIL = randomAlphabetic(8) + "@yopmail.com";

  @BeforeAll
  public static void setup() {
    Configuration.baseUrl = System.getProperty("selenide.baseUrl", "https://staging.papersource.com/");
    Configuration.timeout = 6000;
    RestAssured.baseURI = "https://staging.papersource.com";
    Configuration.startMaximized = true;
    Configuration.fastSetValue = true;
    SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));

    RestAssured
            .given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .filters(new RequestLoggingFilter(), new ResponseLoggingFilter())
            .body("{ \n" +
                    "\"customer\": {\n" +
                    "\"email\": " + "\"" + EMAIL + "\", \n" +
                    "\"firstname\": \"Automation\", \n" +
                    "\"lastname\": \"Test\", \n" +
                    "\"website_id\":1, \n" +
                    "\"group_id\":1\n" +
                    "\t\t},\n" +
                    "\n" +
                    "\"password\": \"Q1w2e3r4\"\n" +
                    "\n" +
                    "}")
            .when()
            .post("/rest/V1/customers");
  }

  @BeforeEach
  public void clearBrowserLocalStorageAndCookies() {
    if (hasWebDriverStarted()) {
      WebDriverRunner.clearBrowserCache();
      clearBrowserLocalStorage();
      clearBrowserCookies();
      executeJavaScript("location.reload()");
    }
  }
}
