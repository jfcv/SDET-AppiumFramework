package org.example;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    private static AndroidDriver<AndroidElement> driver;
    private static AppiumDriverLocalService service;

    @BeforeClass
    public void startServer() throws IOException, InterruptedException {
        killAllNodes();
        service = AppiumDriverLocalService.buildDefaultService();
        service.start();
    }

    @AfterClass
    public void stopServer() {
        service.stop();
    }

    public static AndroidDriver<AndroidElement> capabilities(String application) throws IOException {

        Properties properties = loadGlobalProperties();

        File dir = new File("app");
        File file = new File(dir, (String) properties.get(application));

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, (String) properties.get("platformName"));
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, (String) properties.get("device"));
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME,(String) properties.get("automationName"));
        caps.setCapability(MobileCapabilityType.APP, file.getAbsolutePath());

        driver = new AndroidDriver<>(new URL(getUrl(properties)), caps);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

    private static Properties loadGlobalProperties() throws IOException {
        FileInputStream globalFile = new FileInputStream("src/main/resources/global.properties");
        Properties properties = new Properties();
        properties.load(globalFile);
        return properties;
    }

    private static String getUrl(Properties properties) {
        return properties.get("preffixUrl") +
                (String) properties.get("port") +
                properties.get("suffixUrl");
    }

    private void killAllNodes() throws IOException, InterruptedException {
        Runtime.getRuntime().exec("killall node");
        waitInSeconds(1);
    }

    public void waitInSeconds(int seconds) throws InterruptedException {
        int MILISECS_IN_A_SEC = 1000;
        Thread.sleep(seconds * MILISECS_IN_A_SEC);
    }

    public static void getScreenShot(String name) throws IOException {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("src/test/screenshots/"+ name +".png"));
    }

}
