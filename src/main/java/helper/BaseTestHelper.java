package helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;

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

