import core.BaseTest;
import core.StatusCode;
import io.restassured.response.Response;
import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;
import pojo.postRequestBody;
import utils.ExtentReport;

import java.io.IOException;

import static helper.BaseTestHelper.fileInputStreamMethod;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

/**
 * This class contains TestNG tests for validating HTTP PATCH requests to the reqres.in API.
 * PATCH is used to apply partial modifications to a resource. The tests demonstrate
 * different ways to construct the request body, including:
 * Providing the body as a direct JSON string.
 * Reading the body from an external JSON file.
 * Using a Plain Old Java Object (POJO) to represent the partial update.
 * This class extends {@link BaseTest} and uses RestAssured for making API calls. It also
 * integrates with ExtentReports for test logging and reporting.
 *
 * @author: Carolina Steadham
 */
public class patchUser extends BaseTest {
    
    /**
     * Validates a PATCH request by providing the request body as a JSON string.
     * This test sends a PATCH request to the /api/users/2 endpoint to partially update a user.
     * The request body is a simple JSON string {"name":"morpheus"}.
     * It asserts that the response status code is 200 (SUCCESS).
     */
    @Test(groups = "RegressionSuite")
    public void validatePatchWithString(){
        ExtentReport.extentlog =
                ExtentReport.extentreport.
                        startTest("validatePatchWithString", "validatePatchWithString executed successfully");
        Response response = given()
                .header("Content-Type", "application/json")
                .body("{\"name\":\"morpheus\"}")
                .when()
                .patch("https://reqres.in/api/users/2");
        // Send a PATCH request to update the user providing the request body as a Json string
        assertEquals(response.getStatusCode(), StatusCode.SUCCESS.code);
        System.out.println("validatePatchWithString executed successfully");
        System.out.println(response.getBody().asString());
    }

    /**
     * Validates a PATCH request where the request body is read from an external JSON file.
     * The test reads the JSON content from patchRequestBody.json and uses it as the
     * request body.
     * It asserts that the response status code is 200 (SUCCESS).
     *
     * @throws IOException if an I/O error occurs while reading the file.
     */
    @Test(groups = "RegressionSuite")
    public void validatePatchWithJsonFile() throws IOException {
        ExtentReport.extentlog =
                ExtentReport.extentreport.
                        startTest("validatePatchWithJsonFile", "validatePatchWithJsonFile executed successfully");

        Response response = given()
                .header("Content-Type", "application/json")
                .body(IOUtils.toString(fileInputStreamMethod("patchRequestBody.json")))
                .when()
                .patch("https://reqres.in/api/users/2");
        // Send a PATCH request to update the user reading a Jason data for the request body
        assertEquals(response.getStatusCode(), StatusCode.SUCCESS.code);
        System.out.println("validatePatchWithJsonFile executed successfully");
        System.out.println(response.getBody().asString());
    }

   /**
     * Validates a PATCH request using a POJO to represent the partial update.
     * It creates a postRequestBody POJO, sets only the job field to "tester",
     * and uses this object as the request body. RestAssured serializes the POJO
     * to a JSON object for the request.
     * It asserts that the response status code is 200 (SUCCESS).
     */
    @Test
    public void validatePatchWithPojo(){
        postRequestBody patchRequest = new postRequestBody();
        patchRequest.setJob("tester");
        Response response = given()
                .header("Content-Type", "application/json")
                .body(patchRequest)
                .when()
                .patch("https://reqres.in/api/users/2");
        // Send a PATCH request to update the user using Plain Old Java Object for the request body, to update the job
        assertEquals(response.getStatusCode(), StatusCode.SUCCESS.code);
        System.out.println("validatePatchWithPojo executed successfully");
        System.out.println(response.getBody().asString());
    }
   
    /**
     * Validates a PATCH request using a POJO and deserializes the response back into a POJO for assertion.
     * The test sends a PATCH request with a POJO to update the job field. It then takes the
     * API response and deserializes it back into a postRequestBody object. This allows for
     * direct assertion on the job field in the response body.
     * It verifies that the job field in the response matches the value sent in the request
     * and that the status code is 200 (SUCCESS).
     */
    @Test
    public void validatePatchWithResponsePojo(){
        String job = "QA";
        postRequestBody patchRequest = new postRequestBody();
        patchRequest.setJob(job);
        Response response = given()
                .header("Content-Type", "application/json")
                .body(patchRequest)
                .when()
                .patch("https://reqres.in/api/users/2");
        // Send a PATCH request to update the user using POJO for the request body, 
        // and deserializes the API response back into POJO. It allows to assert the job field in the response body
        postRequestBody responseBody = response.as(postRequestBody.class);
        System.out.println(responseBody.getJob());
        assertEquals(responseBody.getJob(), job);
        assertEquals(response.getStatusCode(), StatusCode.SUCCESS.code);
        System.out.println("validatePatchWithPojo executed successfully");
        System.out.println(response.getBody().asString());
    }
}
