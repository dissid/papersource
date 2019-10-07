package pages.widgets;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class ConfirmationDialog {

  private SelenideElement container = $(".modals-wrapper");

  @Step("Confirm deleting")
  public void confirm() {
    container.find(".action-accept").click();
  }

}
