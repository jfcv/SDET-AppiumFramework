package org.example;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class Page {

    protected AndroidDriver<AndroidElement> driver;

    /*
     * Just for Android
     */
    public void scrollToText(String text) {
        String locator = "new UiScrollable(new UiSelector()).scrollIntoView(text(\"__TEXT__\"))".replace("__TEXT__", text);
        driver.findElementByAndroidUIAutomator(locator);
    }

}
