package utils;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;

/*
 * This utility class provides methods for reading and parsing JSON files.
 * It is designed to simplify the process of accessing test data stored in JSON format,
 * making test automation scripts more readable and data-driven.
 *
 * It is responsible for:
 * 1. Reading JSON data from the 'testdata.json' file.
 * 2. Retrieving specific key-value pairs from the JSON object.
 * 3. Handling and parsing JSON arrays.
 *
 * @author Carolina Steadham
 */
public class JsonReader {
    public static String getTestData(String key) throws IOException, ParseException {
        String testDataValue;
        return testDataValue = (String) getJsonData().get(key); //input is key
    }

    public static JSONObject getJsonData() throws IOException, ParseException {

        //pass the path of the testdata.json file
        File filename = new File("resources//TestData//testdata.json");
        //convert json file into string
        String json = FileUtils.readFileToString(filename, "UTF-8");
        //parse the string into object
        Object obj = new JSONParser().parse(json);
        //give jsonobject so that I can return it to the function everytime it get called
        JSONObject jsonObject = (JSONObject) obj;
        return jsonObject;
    }

    public static JSONArray getJsonArray(String key) throws IOException, ParseException {
        JSONObject jsonObject = getJsonData();
        JSONArray jsonArray = (JSONArray) jsonObject.get(key);
        return jsonArray;
    }

    public static Object getJsonArrayData(String key, int index) throws IOException, ParseException {
        JSONArray languages = getJsonArray(key);
        return languages.get(index);
    }
}
