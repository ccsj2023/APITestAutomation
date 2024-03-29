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

public class patchUser extends BaseTest {

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
        assertEquals(response.getStatusCode(), StatusCode.SUCCESS.code);
        System.out.println("validatePatchWithString executed successfully");
        System.out.println(response.getBody().asString());
    }

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
        assertEquals(response.getStatusCode(), StatusCode.SUCCESS.code);
        System.out.println("validatePatchWithJsonFile executed successfully");
        System.out.println(response.getBody().asString());
    }

    @Test
    public void validatePatchWithPojo(){
        postRequestBody patchRequest = new postRequestBody();
        patchRequest.setJob("tester");
        Response response = given()
                .header("Content-Type", "application/json")
                .body(patchRequest)
                .when()
                .patch("https://reqres.in/api/users/2");
        assertEquals(response.getStatusCode(), StatusCode.SUCCESS.code);
        System.out.println("validatePatchWithPojo executed successfully");
        System.out.println(response.getBody().asString());
    }

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
        postRequestBody responseBody = response.as(postRequestBody.class);
        System.out.println(responseBody.getJob());
        assertEquals(responseBody.getJob(), job);
        assertEquals(response.getStatusCode(), StatusCode.SUCCESS.code);
        System.out.println("validatePatchWithPojo executed successfully");
        System.out.println(response.getBody().asString());
    }
}
