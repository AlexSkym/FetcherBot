package zUtil.myProperties;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * class to get the general properties of the project itself
 */
public class MyProperties {

    Properties properties = new Properties();
    String filePath_forProperties = "file.properties";
    FileInputStream fis;
    FileOutputStream fos;

    /**
     * constructor
     */
    public MyProperties(){
        try {
            //Making this to get specified property file
            fis = new FileInputStream(filePath_forProperties);
            fos = new FileOutputStream(filePath_forProperties);

            //reading the file through FileInputStream (fis)
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * get the general properties
     * @return the properties
     */
    public Properties getMyProperties() {
        return properties;
    }


}
