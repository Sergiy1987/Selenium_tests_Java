package Tools;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropConfig {
    private static Properties properties;
    private static void loadProperties() {
        properties = new Properties();
        FileInputStream propFile;
        FileInputStream propDB;
        String fileName = "/SendEmail.properties";
        String fileDB = "/DataBase.properties";
        try {
            String targetFile = PropConfig.class.getResource(fileName).getPath();
            String targetDB = PropConfig.class.getResource(fileDB).getPath();
            propFile = new FileInputStream(new File(targetFile));
            propDB = new FileInputStream(new File(targetDB));
            properties.load(propFile);
            properties.load(propDB);
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

