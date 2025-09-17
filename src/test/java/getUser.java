import core.BaseTest;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import utils.ExtentReport;
import utils.JsonReader;
import utils.PropertyReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;


/**
 * This class contains a series of TestNG tests for validating various aspects of GET requests.
 * It demonstrates different validation techniques using RestAssured and Hamcrest matchers.
 * The tests cover:
 * Simple status code validation.
 * Validating specific fields in the JSON response body.
 * Checking for the presence of specific items in a response list.
 * Asserting the size of a response list.
 * Validating a string field using containsString.
 * Using query parameters to filter results and validating the filtered response.
 * Comparing status codes using TestNG's assertEquals.
 * The class also includes utility methods to read test data from properties and JSON files.
 * It extends BaseTest for common test setup and integrates with ExtentReports for logging.
 * It uses the ExtentReports library to log the test's execution,
 * which helps in generating detailed and professional test reports.
 *
 * @author Carolina Steadham
 */
public class getUser extends BaseTest {
    String serverAddress = PropertyReader.propertyReader("config.properties", "server");
    String endpoint = getUrl("endpoint");
    String URL = serverAddress + endpoint;

    /**
     * Retrieves an endpoint URL from a JSON test data file.
     * @param key The key to retrieve the endpoint value from the JSON data.
     * @return The endpoint URL as a string.
     * @throws RuntimeException if an IOException or ParseException occurs while reading the JSON file.
     */
    public String getUrl(String key) {
        String endpoint = null;
        try {
            endpoint = JsonReader.getTestData(key);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return endpoint;
    }
    
    /*
     * Validates that a GET request to https://reqres.in/api/users?page=2 returns a 200 OK status code.
     * This is a basic status code validation using RestAssured's then() method.
     * It uses the ExtentReports library to log the test's execution,
     * which helps in generating detailed and professional test reports.
     */
    @Test
    public void validateGetUserData() {
        ExtentReport.extentlog =
                ExtentReport.extentreport.
                        startTest("validateGetUserData", "Validate 200 ");
        given().
                when().get("https://reqres.in/api/users?page=2").
                then().
                assertThat().
                statusCode(200);
    }

    /**
     * Validates specific fields in the JSON response body of a GET request.
     * This test targets the JSONPlaceholder /todos/1 endpoint. It asserts that the
     * response body is not empty and that the title and userId fields have
     * the expected values using Hamcrest matchers.
     * It uses the ExtentReports library to log the test's execution,
     * which helps in generating detailed and professional test reports.
     */
    @Test(groups = "RegressionSuite")
    public void validateGetResponseBody() {
        ExtentReport.extentlog =
                ExtentReport.extentreport.
                        startTest("validateGetResponseBody", "Send a GET request and validate the response body using 'then' Validate title equal to delectus aut autem userId 1, 200 ");

        // Set base URI for the API
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        // Send a GET request and validate the response body using 'then'
        given()
                .when()
                .get("/todos/1")
                .then()
                .assertThat()
                .statusCode(200)
                .body(not(isEmptyString()))
                .body("title", equalTo("delectus aut autem"))
                .body("userId", equalTo(1));
    }

    /**
     * Validates that a response list contains specific items.
     * This test sends a GET request to /posts and uses Hamcrest's hasItems
     * matcher to verify that the title list in the response contains two specific strings.
     * It uses the ExtentReports library to log the test's execution,
     * which helps in generating detailed and professional test reports.
     */
    @Test(description = "validateResponseHasItems")
    public void validateResponseHasItems() {
        ExtentReport.extentlog =
                ExtentReport.extentreport.
                        startTest("validateResponseHasItems", "Use Hamcrest to check that the response body has a specific size");

        // Set base URI for the API
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        // Send a GET request and store the response in a variable
        Response response =
                given()
                        .when()
                        .get("/posts")
                        .then()
                        .extract()
                        .response();

        // Use Hamcrest to check that the response body contains specific items
        assertThat(response.jsonPath().getList("title"), hasItems("sunt aut facere repellat provident occaecati excepturi optio reprehenderit", "qui est esse"));
    }

    /**
     * Validates the size of a response array.
     * This test sends a GET request to /photos and uses Hamcrest's hasSize
     * matcher to verify that the root JSON array has a size of 5000.
     * It uses the ExtentReports library to log the test's execution,
     * which helps in generating detailed and professional test reports.
     */
    @Test
    public void validateResponseHasSize() {
        ExtentReport.extentlog =
                ExtentReport.extentreport.
                        startTest("validateResponseHasSize", " Use Hamcrest to check that the response body contains specific items");

        // Set base URI for the API
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        // Send a GET request and store the response in a variable
        Response response = given()
                .when()
                .get("/photos")
                .then()
                .extract()
                .response();

        // Use Hamcrest to check that the response body has a specific size
        assertThat(response.jsonPath().getList(""), hasSize(5000));
    }

    /**
     * Validates a specific string value within a list in the response body.
     * This test sends a GET request to /comments with a query parameter and
     * validates that the email of the first comment contains a specific substring.
     * It uses the ExtentReports library to log the test's execution,
     * which helps in generating detailed and professional test reports.
     */
    @Test
    public void validateGetUserList() {
        ExtentReport.extentlog =
                ExtentReport.extentreport.
                        startTest("validateGetUserList", " check that the response body contains specific email");

        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        given()
                .when()
                .get("/comments?postId=1")
                .then()
                .statusCode(200)
                .body("email[0]", containsString("Eliseo@gardner.biz"));
    }
    
    /**
     * Validates a GET request with query parameters.
     * This test sends a GET request to /users with a page query parameter.
     * It then asserts the size of the returned data array and verifies the details
     * of a specific user (the third user in the list).
     * It uses the ExtentReports library to log the test's execution,
     * which helps in generating detailed and professional test reports.
     */
    @Test
    public void validateGetUsersWithQueryParameters() {
        ExtentReport.extentlog =
                ExtentReport.extentreport.
                        startTest("validateGetUsersWithQueryParameters", " check that the response body contains size of users and the information of user 3");

        RestAssured.baseURI = "https://reqres.in/api";
        Response response = given()
                .queryParam("page", 2)
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .extract()
                .response();

        // Assert that the response contains 6 users
        response.then().body("data", hasSize(6));

        // Assert that the third user in the list has the correct values
        response.then().body("data[2].id", is(9));
        response.then().body("data[2].email", is("tobias.funke@reqres.in"));
        response.then().body("data[2].first_name", is("Tobias"));
        response.then().body("data[2].last_name", is("Funke"));
        response.then().body("data[2].avatar", is("https://reqres.in/img/faces/9-image.jpg"));
    }

    /**
     * Validates the status code of a GET request using TestNG's assertEquals.
     * This test demonstrates an alternative way to validate the status code. It sends a request,
     * extracts the status code into a variable, and then uses Assert.assertEquals from TestNG
     * to perform the assertion, separating the request from the validation logic.
     * It uses the ExtentReports library to log the test's execution,
     * which helps in generating detailed and professional test reports.
     */
    @Test()
    public void validateStatusCodeGetUser() {
        ExtentReport.extentlog =
                ExtentReport.extentreport.
                        startTest("validateStatusCodeGetUser", " Validate status code with testNG");

        System.out.println("*****************");
        Response resp = given()
                .queryParam("page", 2)
                .when()
                .get("https://reqres.in/api/users");
        int actualStatusCode = resp.statusCode();  //RestAssured
        assertEquals(actualStatusCode, 200); //Testng
    }
    
    /**
     * Validates a GET request with multiple query parameters.
     * This test sends a GET request with three different query parameters. It
     * validates that the status code is 200, demonstrating how RestAssured handles
     * multiple query parameters fluently.
     * It uses the ExtentReports library to log the test's execution,
     * which helps in generating detailed and professional test reports.
     */
    @Test
    public void validateGetUsersWithMultipleQueryParams() {
        ExtentReport.extentlog =
                ExtentReport.extentreport.
                        startTest("validateGetUsersWithMultipleQueryParams", " Validate status code with multiple params");

        Response response = given()
                .queryParam("page", 2)
                .queryParam("per_page", 3)
                .queryParam("rtqsdr", 4)
                .when()
                .get("https://reqres.in/api/users")
                .then()
                .statusCode(200)
                .extract()
                .response();
    }
}
