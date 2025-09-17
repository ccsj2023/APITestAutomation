package pojo;

import java.util.List;

/*
 * This is a Plain Old Java Object (POJO) class that models a complex JSON request body for an API call.
 * It encapsulates nested data structures, including a list of strings and a list of other objects (cityRequest).
 *
 * This class serves as a blueprint for creating structured data to be sent in an API request.
 * It provides standard getter and setter methods to manage its properties, facilitating
 * easy serialization and deserialization of the request body during API automation testing.
 *
 * @author Carolina Steadham
 */
public class postRequestBody {
    private String name;
    private String job;

    private List<String> languages;

    private List<cityRequest> cityRequestBody;

    public List<cityRequest> getCityRequestBody() {
        return cityRequestBody;
    }

    public void setCityRequestBody(List<cityRequest> cityRequestBody) {
        this.cityRequestBody = cityRequestBody;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
