import org.testng.annotations.DataProvider;

public class TestData {
    @DataProvider(name = "data")
    public Object[][] dpData() {
        return new Object[][]{
                {0, "Australia"},
                {5, "Hungary"}
        };
    }
}
