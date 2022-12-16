import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import java.text.MessageFormat;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RestAssuredTest {

    @Test(dataProvider = "data", dataProviderClass = TestData.class)
    public void MainTest(int index, String country)
    {
         String CircuitJsonAddr = MessageFormat.format(
                 "MRData.CircuitTable.Circuits[{0}]",
                 index);

        Response response = given().
                when().
                get("http://ergast.com/api/f1/2017/circuits.json").
                then().
                assertThat().
                statusCode(200).
                extract().
                response();

        JsonPath jsonPath = new JsonPath(response.asString());
        String circuit = jsonPath.getString(CircuitJsonAddr);

        System.out.println();
        System.out.println(circuit);

        String circuitId = jsonPath.getString(CircuitJsonAddr + ".circuitId");

        given().
                pathParam("circuitId",circuitId).
                when().
                get("http://ergast.com/api/f1/2017/circuits/{circuitId}.json").
                then().
                statusCode(200).
                log().
                all().
                assertThat().
                body("MRData.CircuitTable.Circuits.Location.country[0]", equalTo(country));
    }

}


