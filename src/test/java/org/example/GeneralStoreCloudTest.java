package org.example;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class GeneralStoreCloudTest extends BaseCloudTest {

    @Test
    public void cartPriceValidation() throws IOException, InterruptedException {

        AndroidDriver<AndroidElement> driver = cloudCapabilities();

        LandingPage landingPage = new LandingPage(driver);
        landingPage.clickNameField();
        landingPage.sendKeysToNameField("hello");

        driver.hideKeyboard();

        landingPage.clickRadioFemale();
        landingPage.clickSpinnerCountry();
        landingPage.scrollToText("Argentina");
        landingPage.clickArgentinaLbl();

        ProductsPage productsPage = landingPage.clickLetsShopBtn();
        productsPage.addProductsToCart();
        productsPage.clickCartBtn();
        waitInSeconds(4);

        CartPage cartPage = productsPage.renderCartPage();
        double priceSum = cartPage.calculatePriceSum();
        double totalAmount = cartPage.getTotalAmount();
        Assert.assertEquals(priceSum, totalAmount);
    }

}
