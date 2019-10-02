package pages;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;

public class YopMail {

  @Step
  public YopMail open() {
    Selenide.open("http://www.yopmail.com/en/");
    return this;
  }

  @Step("Open email box for {email}")
  public YopMail loginBy(String email) {
    $("#login").setValue(email);
    $("input[value='Check Inbox']").click();
    return this;
  }

  @Step("Assert Greeting - {text} in email")
  public YopMail assertGreeting(String text) {
    switchTo().frame("ifmail");
    $(".greeting+p").waitUntil(visible, 15000).shouldHave(exactText(text));
    return this;
  }
}
