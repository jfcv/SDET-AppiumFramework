package org.example;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import org.testng.annotations.Test;

import java.io.IOException;

public class ApiDemosTest extends BaseTest {

    @Test(dataProvider = "ApiDemosTestData", dataProviderClass = ApiDemosTestData.class)
    public void preferencesValidation(String dataProvided) throws IOException {
        AndroidDriver<AndroidElement> driver = capabilities("ApiDemosApp");

        driver.findElementByXPath("//android.widget.TextView[@text='Preference']").click();
        driver.findElementByXPath("//android.widget.TextView[@text='3. Preference dependencies']").click();
        driver.findElementById("android:id/checkbox").click();
        driver.findElementByXPath("(//android.widget.RelativeLayout)[2]").click();
        driver.findElementByClassName("android.widget.EditText").sendKeys(dataProvided);
        driver.findElementsByClassName("android.widget.Button").get(1).click();
    }

}
