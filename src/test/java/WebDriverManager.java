import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.IOException;

public class WebDriverManager {

    public static WebDriver wd;

    public static WebDriver loadBrowser() throws IOException {
        PropertyManager.loadProperties();
        if(PropertyManager.browserName.equals("chrome")) {
            String driverPath = PropertyManager.getChromeDriverPath();
            System.setProperty(PropertyManager.chromeWebdriverAttrName,driverPath);
            wd = new ChromeDriver();
        }
        return wd;
    }

}
