package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

public class BasePage {

    public static String getScreenshot(String imageName, WebDriver driver){
        TakesScreenshot screenshot = (TakesScreenshot)driver ;
        File file = screenshot.getScreenshotAs(OutputType.FILE);
        String filePath = "./screenshot/"+imageName;
        try {
            //Copy method to specific location-  here it will save screenshot in our project home directory
            FileUtils.copyFile(file, new File(filePath)); //Copy file from virtual to physical memory location
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return filePath;
    }

    public static String convertToBase64(String screenshotPath){
        byte[] file = null;
        try {
            file = FileUtils.readFileToByteArray(new File(screenshotPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String base64Img = Base64.getEncoder().encodeToString(file);
        return base64Img;
    }
}
