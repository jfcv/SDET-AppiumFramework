package org.example;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class GeneralStoreTestCase4 extends BaseTest {

    @Test
    public void cartPriceValidation() throws IOException, InterruptedException {

        AndroidDriver<AndroidElement> driver = capabilities("GeneralStoreApp");

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
        productsPage.waitInSeconds(4);

        CartPage cartPage = productsPage.renderCartPage();
        double priceSum = cartPage.calculatePriceSum();
        double totalAmount = cartPage.getTotalAmount();
        Assert.assertEquals(priceSum, totalAmount);
    }

}
