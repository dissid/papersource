package pages.widgets;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.conditions.ExactText;
import io.qameta.allure.Step;
import org.openqa.selenium.support.ui.ExpectedCondition;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.Wait;

public class ConfirmationDialog {

  private SelenideElement container = $(".modals-wrapper");

  @Step("Confirm deleting")
  public void confirm() {
    container.find(".action-accept").waitUntil(visible, 10000).click();
  }

  @Step
  public void checkModalText(String text){
    container.find(".confirm .modal-content").waitUntil(visible, 10000).shouldHave(exactText(text));
  }

}
