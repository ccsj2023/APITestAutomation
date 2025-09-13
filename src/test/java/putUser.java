import core.StatusCode;
import io.restassured.response.Response;
import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;
import pojo.postRequestBody;

import java.io.IOException;

import static helper.BaseTestHelper.fileInputStreamMethod;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class putUser {
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
