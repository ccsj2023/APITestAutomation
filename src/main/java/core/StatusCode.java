package core;

/*
 * This enum defines common HTTP status codes and their corresponding messages.
 * It provides a centralized and readable way to manage expected status codes
 * within the test framework, making the tests more maintainable and clear.
 * Each enum constant stores both the numerical code (e.g., 200) and a
 * descriptive message (e.g., "The request succeeded").
  *
 * @author Carolina Steadham
 */
public enum StatusCode {

    SUCCESS(200, "The request succeeded"),
    CREATED(201, "A new resource was created"),
    BAD_REQUEST(400, "Missing required field: name"),
    UNAUTHORIZED(401, "Invalid access token"),
    NOT_FOUND(404, "Cannot Find Requested Resource"),
    NO_CONTENT(204, "No content to send in the response body");


    public final int code;
    public final String msg;
    StatusCode (int code, String msg){
        this.code= code;
        this.msg = msg;
    }
}

