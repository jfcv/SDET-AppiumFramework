package org.example;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    public static AndroidDriver<AndroidElement> capabilities(String application) throws IOException {

        FileInputStream globalFile = new FileInputStream("src/main/java/org/example/global.properties");
        Properties properties = new Properties();
        properties.load(globalFile);

        File dir = new File("app");
        File file = new File(dir, (String) properties.get(application));

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, (String) properties.get("platformName"));
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, (String) properties.get("device"));
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME,(String) properties.get("automationName"));
        caps.setCapability(MobileCapabilityType.APP, file.getAbsolutePath());

        AndroidDriver<AndroidElement> driver = new AndroidDriver<>(new URL((String) properties.get("serverUrl")), caps);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

}
