import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class CommonUtils {

    public static String convertStringToUTFStandards(String string) {
        string = URLDecoder.decode(string, StandardCharsets.UTF_8);
        return string;
    }

    public static String takeScreenShot(WebDriver driver,String fileName) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src =  ts.getScreenshotAs(OutputType.FILE);
        String destFile = System.getProperty("user.dir")+"\\reports"+"\\"+fileName+".png";
        FileUtils.copyFile(src,new File(destFile));
        System.out.println("Screenshot successfully taken at location : "+ destFile);
        return destFile;
    }
}
