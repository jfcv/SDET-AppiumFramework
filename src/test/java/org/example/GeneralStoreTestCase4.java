package org.example;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class GeneralStoreTestCase4 extends Base {

    @Test
    public void cartPriceValidation() throws IOException, InterruptedException {
        AndroidDriver<AndroidElement> driver = capabilities("GeneralStoreApp");

        // locators
        MobileElement countryDropdown = driver.findElementById("com.androidsample.generalstore:id/spinnerCountry");
        MobileElement inputText = driver.findElementById("com.androidsample.generalstore:id/nameField");
        MobileElement radioFemale = driver.findElementById("com.androidsample.generalstore:id/radioFemale");
        MobileElement shopButton = driver.findElementById("com.androidsample.generalstore:id/btnLetsShop");

        // positive path flow
        inputText.click();
        inputText.sendKeys("hello");
        driver.hideKeyboard();
        radioFemale.click();
        countryDropdown.click();
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"))");
        driver.findElementByXPath("//android.widget.TextView[@text='Argentina']").click();
        shopButton.click();

        int count = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();
        for (int i=0;i<count;i++) { //O(n) time complexity
            driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
        }
        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();

        int SECONDS_TO_WAIT = 4;
        int MILISECS_IN_A_SEC = 1000;
        /*
         * this wait is needed so appium has time to process the changed view
         */
        Thread.sleep(SECONDS_TO_WAIT*MILISECS_IN_A_SEC);

        double priceSum = 0;
        int cartCounter = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).size();
        for(int j=0;j<cartCounter;j++) { //O(n) time complexity
            /*
             * get(index) method is a constant time O(1) operation
             */
            String price = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(j).getText();
            priceSum = priceSum + Double.parseDouble(price.substring(1));
        }

        String totalPrice = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
        double totalAmount = Double.parseDouble(totalPrice.substring(1));

        Assert.assertEquals(priceSum, totalAmount);
    }

}
