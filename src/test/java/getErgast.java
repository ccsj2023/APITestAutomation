import core.BaseTest;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.ExtentReport;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

/**
 * This class contains TestNG tests for validating GET requests to the Ergast Formula 1 API.
 * It specifically demonstrates how to use path parameters in a REST Assured request.
 * The test sends a GET request to retrieve information about F1 circuits for a specific season
 * and validates the HTTP status code of the response.
 *
 * @author Carolina Steadham
 */
public class getErgast extends BaseTest {

    /**
     * Validates the response body and status code for a GET request using a path parameter.
     * This test performs the following steps:
     * Sets the raceSeason path parameter to 2016.
     * Sends a GET request to http://ergast.com/api/f1/{raceSeason}/circuits.json.
     * Extracts the status code from the response.
     * Asserts that the status code is 200 using TestNG's`assertEquals
     * Prints the response body to the console.
     */
    @Test(description = "Validate the status code for GET users endpoint", groups = "RegressionSuite")
    public void validateResponseBodyGetPathParam() {
        ExtentReport.extentlog =
                ExtentReport.extentreport.
                        startTest("validateResponseBodyGetPathParam", "Validate 200 Status Code for GET Ergast");
        Response resp = given()
                .pathParam("raceSeason", 2016)
                .when()
                .get("http://ergast.com/api/f1/{raceSeason}/circuits.json"); //RestAssured
        int actualStatusCode = resp.statusCode();  //RestAssured
        assertEquals(actualStatusCode, 200); //Testng
        System.out.println(resp.body().asString());
    }
}
