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

public class getUser extends BaseTest {
    String serverAddress = PropertyReader.propertyReader("config.properties", "server");
    String endpoint = getUrl("endpoint");
    String URL = serverAddress + endpoint;

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
