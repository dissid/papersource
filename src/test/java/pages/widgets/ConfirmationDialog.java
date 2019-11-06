package pages.widgets;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.conditions.ExactText;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.Wait;

public class ConfirmationDialog {

  private SelenideElement container = $(".modals-wrapper");

  @Step("Confirm deleting")
  public void confirm() {
    Wait().until(ExpectedConditions.elementToBeClickable(By.cssSelector(".action-accept")));
    container.find(".action-accept").click();
  }

  @Step("Check modal text")
  public void checkModalText(String text){
    container.find(".confirm .modal-content").waitUntil(visible, 10000).shouldHave(exactText(text));
  }

}
