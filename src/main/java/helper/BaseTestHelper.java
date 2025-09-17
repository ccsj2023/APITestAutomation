package helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;

/*
 * This is a helper class that provides utility methods for the test framework.
 * These methods are designed to simplify common file and system-related tasks.
 *
 * It is responsible for:
 * 1. Reading files from the resources/TestData directory.
 * 2. Creating a new folder if it doesn't already exist.
 * 3. Generating a unique timestamp for use in file and folder names.
 *
 * @author Carolina Steadham
 */
public class BaseTestHelper {
    private static FileInputStream fileInputStream;

    public static FileInputStream fileInputStreamMethod(String requestBodyFileName){
        try{
            fileInputStream = new FileInputStream(
                    (System.getProperty("user.dir") + "/resources/TestData/" + requestBodyFileName));
        }catch (FileNotFoundException e){
            throw new RuntimeException(e);
        }
        return fileInputStream;
    }

   // Create folder
    public static void CreateFolder(String path)  {
        //File is a class inside java.io package
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();//mkdir is used to create folder
        } else
            System.out.println("Folder already created");
    }

    // Return current time stamp
    public static String Timestamp() {
        Date now = new Date();
        String Timestamp = now.toString().replace(":", "-");
        return Timestamp;
    }
}

