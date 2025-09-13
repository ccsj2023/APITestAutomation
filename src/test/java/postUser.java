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

public class postUser {

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
