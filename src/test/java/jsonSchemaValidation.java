import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

/**
 * This class contains a TestNG test for validating the JSON schema of an API response.
 * It demonstrates how to use RestAssured's {@link JsonSchemaValidator} to ensure that
 * the structure of the JSON response body conforms to a predefined schema file.
 * The test fetches a list of users from the reqres.in API and validates the response
 * against a schema file located at resources/Schema/ExpectedSchema.json.
 *
 * @author Carolina Steadham 
 */   
public class jsonSchemaValidation {

    /**
     * Validates the JSON schema of the response from the users API endpoint.
     * This test performs the following steps:
     * Loads the expected JSON schema from a file.
     * Sends a GET request to https://reqres.in/api/users?page=234.
     * Asserts that the response status code is 200.
     * Validates the response body against the loaded JSON schema using
     * {@link JsonSchemaValidator#matchesJsonSchema(File)}.
     */
    @Test
    public void jsonSchemaValidation() {
        File schema = new File("resources/Schema/ExpectedSchema.json");
        given()
                .when()
                .get("https://reqres.in/api/users?page=234")
                .then()
                .assertThat()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchema(schema));
    }

}

