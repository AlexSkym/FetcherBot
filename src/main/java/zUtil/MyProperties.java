package zUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * class to get the general properties of the project itself
 */
public class MyProperties {

    Properties properties = new Properties();
    String filePath_forProperties = "file.properties";
    FileInputStream fis;

    /**
     * constructor
     */
    public MyProperties(){

        //Making this to get specified property file
        try {
            fis = new FileInputStream(filePath_forProperties);
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
