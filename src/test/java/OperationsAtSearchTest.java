import org.junit.jupiter.api.Test;
import pages.Home;

public class OperationsAtSearchTest {

  private Home home = new Home();

  @Test
  void searchBySKU() {
    home.open()
            .openSearching()
            .search("10007616");
  }
}