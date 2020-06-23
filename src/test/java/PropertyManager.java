import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyManager {

    public static Properties configProp = new Properties();
    public static String browserName;
    public static String chromeWebdriverAttrName;
    public static String chromWebdriverPath;
    public static String googleMapsUrl;
    public static String qaAgilityPage;
    public static String ataPage;

    public static void loadProperties() throws IOException {
        String fileName = "/config.properties";
        String path = PropertyManager.class.getResource(fileName).getFile();
        path = CommonUtils.convertStringToUTFStandards(path);
        File file = new File(path);
        FileInputStream fis = new FileInputStream(file);
        configProp.load(fis);
        browserName = configProp.getProperty("browserName");
        chromeWebdriverAttrName = configProp.getProperty("chromeWebdriverAttrName");
        chromWebdriverPath = configProp.getProperty("chromeWebDriverPath");
        googleMapsUrl = configProp.getProperty("googleMapsUrl");
        qaAgilityPage = configProp.getProperty("qaAgilityPage");
        ataPage = configProp.getProperty("ataPage");
    }


    public static String getChromeDriverPath() {
        String path = System.getProperty("user.dir")+"/src/test/resources/drivers/chromedriver.exe";
        File driverPath = new File(path);
        return  CommonUtils.convertStringToUTFStandards(driverPath.getPath());
    }
}
