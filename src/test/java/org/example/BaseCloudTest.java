package org.example;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseCloudTest {

    private static AndroidDriver<AndroidElement> driver;

    public static AndroidDriver<AndroidElement> cloudCapabilities() throws IOException {

        DesiredCapabilities caps = new DesiredCapabilities();

        // Set your access credentials
        caps.setCapability("browserstack.user", "kinggmail_aDhB1y");
        caps.setCapability("browserstack.key", "VouhyxzrFMiWhzfRgZJw");
        // Set URL of the application under test
        caps.setCapability("app", "bs://b110d50cf5c9596f17a05da825a0f77cfda7f7ee");
        // Specify device and os_version for testing
        caps.setCapability("device", "Google Pixel 3");
        caps.setCapability("os_version", "9.0");
        // Set other BrowserStack capabilities
        caps.setCapability("project", "Cloud Testing Demo");
        caps.setCapability("build", "Java Android");
        caps.setCapability("name", "GeneralStoreCloudTest");


        // Initialise the remote Webdriver using BrowserStack remote URL
        // and desired capabilities defined above
        driver = new AndroidDriver<AndroidElement>(new URL("http://hub.browserstack.com/wd/hub"), caps);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

    public void waitInSeconds(int seconds) throws InterruptedException {
        int MILISECS_IN_A_SEC = 1000;
        Thread.sleep(seconds * MILISECS_IN_A_SEC);
    }

}
