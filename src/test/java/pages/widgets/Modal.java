package pages.widgets;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

public class Modal {

  private SelenideElement container;

  Modal(SelenideElement container) {
    this.container = container;
  }

  @Step("Confirm deleting")
  public void confirm() {
    container.find(".action-accept").click();
  }

}
