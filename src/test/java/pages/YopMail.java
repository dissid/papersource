package pages;

import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;

public class YopMail {

  public YopMail open(String email){
    Selenide.open("http://www.yopmail.com/en/");
    $("#login").setValue(email);
    $("input[value='Check Inbox']").click();
    return this;
  }
  public YopMail assertGreeting(String text){
    switchTo().frame("ifmail");
    $(".greeting+p").shouldHave(exactText(text));
    return this;
  }
}
