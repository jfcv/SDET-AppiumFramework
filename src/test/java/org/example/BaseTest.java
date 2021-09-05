package org.example;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    private static AppiumDriverLocalService service;

    public static AndroidDriver<AndroidElement> capabilities(String application) throws IOException {

        Properties properties = loadGlobalProperties();

        File dir = new File("app");
        File file = new File(dir, (String) properties.get(application));

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, (String) properties.get("platformName"));
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, (String) properties.get("device"));
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME,(String) properties.get("automationName"));
        caps.setCapability(MobileCapabilityType.APP, file.getAbsolutePath());

        AndroidDriver<AndroidElement> driver = new AndroidDriver<>(new URL(getUrl(properties)), caps);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

    private static Properties loadGlobalProperties() throws IOException {
        FileInputStream globalFile = new FileInputStream("src/main/java/org/example/global.properties");
        Properties properties = new Properties();
        properties.load(globalFile);
        return properties;
    }

    private static String getUrl(Properties properties) {
        return properties.get("preffixUrl") +
                (String) properties.get("port") +
                properties.get("suffixUrl");
    }

    @BeforeClass
    public void startServer() throws IOException {
        Properties properties = loadGlobalProperties();
        String port = (String) properties.get("port");
        if(!isServerRunning(Integer.valueOf(port))) {
            service = AppiumDriverLocalService.buildDefaultService();
            service.start();
        }
    }

    @AfterClass
    public void stopServer() {
        service.stop();
    }

    public boolean isServerRunning(int port) {
        boolean serverRunning = false;
        ServerSocket socket;
        try {
            socket = new ServerSocket(port);
            socket.close();
        } catch (IOException e) {
            serverRunning = true;
        }
        return serverRunning;
    }

}
