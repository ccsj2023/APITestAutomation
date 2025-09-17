package pojo;

/*
 * This is a Plain Old Java Object (POJO) class representing a city request.
 * It is used to model the request body for an API call, typically to add or update city data.
 *
 * This class encapsulates two fields: name and temperature, and provides
 * standard getter and setter methods to access and modify these properties.
 * Using a POJO helps with serialization and deserialization of JSON or XML data
 * in API automation testing.
 *
 * @author Carolina Steadham
 */
public class cityRequest {

    private String name;
    private String temperature;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
}
