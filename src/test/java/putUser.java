import core.StatusCode;
import io.restassured.response.Response;
import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;
import pojo.postRequestBody;

import java.io.IOException;

import static helper.BaseTestHelper.fileInputStreamMethod;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
/**
 * This class contains TestNG tests for validating PUT requests to the reqres.in API.
 * It demonstrates different ways of providing the request body:
 * <ul>
 * <li>Using a JSON string directly.</li>
 * <li>Reading a JSON payload from an external file.</li>
 * <li>Using a Plain Old Java Object (POJO).</li>
 * </ul>
 * <p>
 * The tests verify that the API returns a successful status code (200 OK) for each PUT operation.
 * The class uses RestAssured for making the HTTP requests and TestNG for test execution and assertions.
 * </p>
public class putUser {
   /**
     * Validates a PUT request where the request body is provided as a JSON string.
     * <p>
     * It sends a PUT request to update user with ID 2 on the reqres.in API.
     * The request body is a hardcoded JSON string: `{"name":"morpheus","job":"leader"}`.
     * It asserts that the response status code is 200 (SUCCESS).
     * </p>
     */
    @Test
    public void validatePutWithString(){

        Response response = given()
                .header("Content-Type", "application/json")
                .body("{\"name\":\"morpheus\",\"job\":\"leader\"}")
                .when()
                .put("https://reqres.in/api/users/2");
        // Send a PUT request to create a new user providing the request body as a Json string
        assertEquals(response.getStatusCode(), StatusCode.SUCCESS.code);
        System.out.println("validatePutWithString executed successfully");
        System.out.println(response.getBody().asString());
    }
   /**
     * Validates a PUT request where the request body is read from an external JSON file.
     * <p>
     * It uses `IOUtils.toString()` to convert the content of `putRequestBody.json` file
     * into a string which is then used as the request body.
     * It asserts that the response status code is 200 (SUCCESS).
     * </p>
     *
     * @throws IOException if an I/O error occurs while reading the file.
     */
    @Test
    public void validatePutWithJsonFile()throws IOException {

        Response response = given()
                .header("Content-Type", "application/json")
                .body(IOUtils.toString(fileInputStreamMethod("putRequestBody.json")))
                .when()
                .put("https://reqres.in/api/users/2");
        // Send a PUT request to create a new user reading an external file 
        assertEquals(response.getStatusCode(), StatusCode.SUCCESS.code);
        System.out.println("validatePutWithJsonFile executed successfully");
        System.out.println(response.getBody().asString());
    }

    /**
     * Validates a PUT request where the request body is created using a POJO (Plain Old Java Object).
     * <p>
     * It creates an instance of `postRequestBody` POJO, sets the `name` and `job` fields,
     * and uses this object as the request body. RestAssured automatically serializes the POJO to JSON.
     * It asserts that the response status code is 200 (SUCCESS).
     * </p>
     */
    @Test
    public void validatePutWithPojo(){
        postRequestBody putRequest = new postRequestBody();
        putRequest.setJob("QA");
        putRequest.setName("carosteads");
        Response response = given()
                .header("Content-Type", "application/json")
                .body(putRequest)
                .when()
                .put("https://reqres.in/api/users/2");
        // Send a PUT request to create a new user using POJO to represent the new user data, 
        // set both job and name fields 
        assertEquals(response.getStatusCode(), StatusCode.SUCCESS.code);
        System.out.println("validatePutWithJsonFile executed successfully");
        System.out.println(response.getBody().asString());
    }

}
