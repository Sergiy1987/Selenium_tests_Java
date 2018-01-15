package Tools;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropConfig {
    private static Properties properties;
    private static void loadProperties() {
        properties = new Properties();
        FileInputStream propFile;
        String fileName = "/SendEmail.properties";
        try {
            String targetFilePath = PropConfig.class.getResource(fileName).getPath();
            propFile = new FileInputStream(new File(targetFilePath));
            properties.load(propFile);
            //System.out.println(properties.getProperty("UserData"));
        } catch (Exception e) {
            e.getMessage();
        }
    }
    public static String getProperty(String propertyName) {
        if (properties == null) {
            loadProperties();
        }
        return properties.getProperty(propertyName);
    }
}

