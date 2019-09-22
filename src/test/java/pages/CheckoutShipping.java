package pages;

import static com.codeborne.selenide.Selenide.$;

public class CheckoutShipping {

  public void setUserInfo(String email, String firstname, String lastname) {
    $("#customer-EMAIL").setValue(email);
    $("input[name='firstname']").setValue(firstname);
    $("input[name='lastname']").setValue(lastname);
  }

  public void setCompany(String name) {
    $("input[name='company']").setValue(name);
  }

  public void setStreets(String street1, String street2) {
    $("input[name='street[0]']").setValue(street1);
    $("input[name='street[1]']").setValue(street2);
  }

  public void setCity(String city) {
    $("input[name='city']").setValue(city);
  }

  public void selectState(String state) {
    $("select[name='region_id']").selectOption(state);
  }

  public void setZipCode(int zipCode) {
    $("input[name='postcode']").setValue(Integer.toString(zipCode));
  }

  public void selectCountry(String country) {
    $("select[name='country_id']").selectOption(country);
  }

  public void setPhone(int phone) {
    $("input[name='telephone']").setValue(Integer.toString(phone));
  }
}

