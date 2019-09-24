package testConfig;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.hasWebDriverStarted;

public class BaseTest {

  @BeforeAll
  public static void setup() {
    Configuration.baseUrl = System.getProperty("selenide.baseUrl", "https://staging.papersource.com/");
    Configuration.timeout = 6000;
    RestAssured.baseURI = "https://staging.papersource.com";
    Configuration.startMaximized = true;

    RestAssured
            .given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .filters(new RequestLoggingFilter(), new ResponseLoggingFilter())
            .body("{ \n" +
                    "\"customer\": {\n" +
                    "\"email\": \"postman@gorillagroup.com\", \n" +
                    "\"firstname\": \"Test\", \n" +
                    "\"lastname\": \"Postman\", \n" +
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
      clearBrowserLocalStorage();
      Selenide.sleep(500);
      clearBrowserCookies();
    }
  }
}
