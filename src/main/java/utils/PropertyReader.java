package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

 /*
 * This utility class is designed to read and retrieve data from properties files.
 * It provides a simple and reusable method to access key-value pairs from
 * configuration files, ensuring that test scripts are not hardcoded with
 * environment-specific data like URLs, credentials, or file paths.
 *
 * @author Carolina Steadham
 */
public class PropertyReader  {

    public static String propertyReader(String filePath, String key) {
        String value = null;
        //Inputstream is required while loading into properties

        try (InputStream input = new FileInputStream(filePath)) {

            // object creation for Property class
            Properties prop = new Properties();

            // load a properties file
            prop.load(input);

            //getProperty will fetch the value according to the key
            value = prop.getProperty(key);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return value;
    }
}
