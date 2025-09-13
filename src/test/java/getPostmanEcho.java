import core.StatusCode;
import io.restassured.response.Response;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import utils.JsonReader;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

/**
 * This class contains TestNG tests for validating various authentication mechanisms
 * using the Postman Echo API. It demonstrates how to perform basic and digest
 * authentication and how to read credentials from an external JSON file.
 * <p>
 * The tests use RestAssured for making the API calls and TestNG for assertions.
 * </p>
 *
 * @author Carolina Steadham
 */
public class getPostmanEcho {

    /**
     * Validates a basic authentication request using credentials read from a JSON file.
     * <p>
     * This test retrieves the username and password from a JSON file using the
     * {@link JsonReader} utility class. It then sends a GET request with basic
     * authentication and asserts that the response status code is 200 (SUCCESS).
     * </p>
     *
     * @throws IOException    if an I/O error occurs while reading the JSON file.
     * @throws ParseException if an error occurs while parsing the JSON data.
     */
    @Test(groups= "SmokeSuite")
    public void validateWithTestDataFromJson()throws IOException, ParseException {
        String username = JsonReader.getTestData("username");
        String password = JsonReader.getTestData("password");
        System.out.println("username from json is: " + username + "***password from json is:" + password);
        Response resp = given()
                .auth()
                .basic(username, password)
                .when()
                .get("https://postman-echo.com/basic-auth"); //RestAssured

        int actualStatusCode = resp.statusCode();  //RestAssured
        assertEquals(actualStatusCode, 200); //Testng
        System.out.println("validateWithTestDataFromJson executed successfully");
    }
    
    /**
     * Validates a GET request with digest authentication.
     * <p>
     * This test sends a GET request to the digest-auth endpoint with "postman" as the
     * username and "password" as the password. It asserts that the response status
     * code is 200 (SUCCESS) and prints the response body to the console.
     * </p>
     */
    @Test()
    public void validateResponseBodyGetDigestAuth() {
        Response resp = given()
                .auth()
                .digest("postman", "password")
                .when()
                .get("https://postman-echo.com/digest-auth"); //RestAssured

        int actualStatusCode = resp.statusCode();  //RestAssured
        assertEquals(actualStatusCode, StatusCode.SUCCESS.code); //Testng
        System.out.println(resp.body().asString());
    }

    
    /**
     * Validates a GET request with basic authentication.
     * <p>
     * This test sends a GET request to the basic-auth endpoint with "postman" as the
     * username and "password" as the password. It asserts that the response status
     * code is 200 (SUCCESS) and prints the response body.
     * </p>
     */
    @Test()
    public void validateResponseBodyGetBasicAuth(){
        Response resp = given()
                .auth()
                .basic("postman", "password")
                .when()
                .get("https://postman-echo.com/basic-auth"); //RestAssured

        int actualStatusCode = resp.statusCode();  //RestAssured
        assertEquals(actualStatusCode, 200); //Testng
        System.out.println(resp.body().asString());
    }
}
