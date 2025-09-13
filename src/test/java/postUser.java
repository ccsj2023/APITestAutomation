import core.BaseTest;
import core.StatusCode;
import helper.BaseTestHelper;
import io.restassured.response.Response;
import org.apache.commons.io.IOUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.cityRequest;
import pojo.postRequestBody;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import static helper.BaseTestHelper.fileInputStreamMethod;
import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
/**
 * This class contains TestNG tests for validating POST requests to the reqres.in API.
 * It demonstrates various methods for creating the request body:
 * <ul>
 * <li>Using a direct JSON string.</li>
 * <li>Reading the JSON payload from an external file.</li>
 * <li>Using a Plain Old Java Object (POJO) for simple and nested JSON structures.</li>
 * </ul>
 * <p>
 * The tests use RestAssured to send HTTP requests and TestNG for assertions,
 * ensuring that the server responds with a successful 'Created' status code (201).
 * </p>
 *
 * @author Your Carolina Steadham
 */
public class postUser {

    /**
     * Validates a POST request where the request body is provided as a JSON string.
     * <p>
     * This test sends a POST request to the `/api/users` endpoint to create a new user.
     * The request body is a hardcoded JSON string: `{"name":"morpheus","job":"leader"}`.
     * It asserts that the response status code is 201 (CREATED).
     * </p>
     */
    @Test
    public void validatePostWithString() {
        Response response = given()
                .header("Content-type", "application/json")
                .body("{\"name\":\"morpheus\",\"job\":\"leader\"}")
                .when()
                .post("https://reqres.in/api/users");
        // Send a POST request to create a new user providing the request body as a Json string
        assertEquals(response.getStatusCode(), StatusCode.CREATED.code);
        System.out.println("validatePostWithString executed successfully");
        System.out.println(response.getBody().asString());
    }

    /**
     * Validates a POST request where the request body is read from an external JSON file.
     * <p>
     * It reads the content of `postRequestBody.json` file using `IOUtils.toString()`
     * and uses the resulting string as the request body.
     * It asserts that the response status code is 201 (CREATED).
     * </p>
     *
     * @throws IOException if an I/O error occurs while reading the file.
     */

    @Test
    public void validatePostWithJsonFile() throws IOException {
        Response response = given()
                .header("Content-Type", "application/json")
                .body(IOUtils.toString(fileInputStreamMethod("postRequestBody.json")))
                .when()
                .post("https://reqres.in/api/users");
        // Send a POST request to create a new user reading the Json data from a file
        Assert.assertEquals(response.getStatusCode(), StatusCode.CREATED.code);
        System.out.println("validatePostWithJsonFile executed successfully");
        System.out.println(response.getBody().asString());
    }
  
    /**
     * Validates a POST request where the request body is created using a simple POJO.
     * <p>
     * It instantiates `postRequestBody`, sets the `name` and `job` properties,
     * and then RestAssured automatically serializes the object to JSON for the request body.
     * It asserts that the response status code is 201 (CREATED).
     * </p>
     */
    @Test
    public void validatePostWithPojo() {
        postRequestBody postRequest = new postRequestBody();
        postRequest.setJob("leader");
        postRequest.setName("morpheus");
        Response response = given()
                .header("Content-Type", "application/json")
                .body(postRequest)
                .when()
                .post("https://reqres.in/api/users");
        // Send a POST request to create a new user using a Plain Old Java Oject to represent the user data
        Assert.assertEquals(response.getStatusCode(), StatusCode.CREATED.code);
        System.out.println("validatePostWithPojo executed successfully");
        System.out.println(response.getBody().asString());
    }

    /**
     * Validates a POST request using a POJO that contains a list of strings.
     * <p>
     * It creates a `postRequestBody` POJO and populates a list of languages (`Java`, `Python`)
     * to be included in the request body.
     * It asserts that the response status code is 201 (CREATED).
     * </p>
     */
    @Test
    public void validatePostWithPojoListString(){

        List<String> listLanguage = new ArrayList<>();
        listLanguage.add("Java");
        listLanguage.add("Python");

        postRequestBody postRequest = new postRequestBody();
        postRequest.setJob("leader");
        postRequest.setName("morpheus");
        postRequest.setLanguages(listLanguage);
        Response response = given()
                .header("Content-Type", "application/json")
                .body(postRequest)
                .when()
                .post("https://reqres.in/api/users");
        // Send a POST request to create a new user using POJO with a lits of string
        Assert.assertEquals(response.getStatusCode(), StatusCode.CREATED.code);
        System.out.println("validatePostWithPojoListString executed successfully");
        System.out.println(response.getBody().asString());
    }

   /**
     * Validates a POST request using a POJO that contains a nested list of other objects.
     * <p>
     * It demonstrates how to build a complex request body by creating a list of `cityRequest` POJOs
     * and setting it within the main `postRequestBody` POJO.
     * It asserts that the response status code is 201 (CREATED).
     * </p>
     */
    @Test
    public void validatePostWithPojoListObject(){
        List<String> listLanguage = new ArrayList<>();
        listLanguage.add("Java");
        listLanguage.add("Python");

        cityRequest cityRequests1 = new cityRequest();
        cityRequests1.setName("Raleigh");
        cityRequests1.setTemperature("30");
        cityRequest cityRequests2 = new cityRequest();
        cityRequests2.setName("Greensboro");
        cityRequests2.setTemperature("29");
        List<cityRequest> cityRequests = new ArrayList<>();
        cityRequests.add(cityRequests1);
        cityRequests.add(cityRequests2);

        postRequestBody postRequest = new postRequestBody();
        postRequest.setJob("leader");
        postRequest.setName("morpheus");
        postRequest.setLanguages(listLanguage);
        postRequest.setCityRequestBody(cityRequests);

        Response response = given()
                .header("Content-Type", "application/json")
                .body(postRequest)
                .when()
                .post("https://reqres.in/api/users");
         // Send a POST request to create a new user using POJO with a lits of objects
        Assert.assertEquals(response.getStatusCode(), StatusCode.CREATED.code);
        System.out.println("validatePostWithPojoListObject executed successfully");
        System.out.println(response.getBody().asString());
    }

}
