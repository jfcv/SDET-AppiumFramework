package org.example;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import java.util.List;

import static org.openqa.selenium.support.PageFactory.initElements;

public class CartPage extends Page {

    @AndroidFindBy(id = "productPrice")
    private List<MobileElement> productPriceLbl;

    @AndroidFindBy(id = "totalAmountLbl")
    private MobileElement totalAmountLbl;

    public CartPage(AndroidDriver<AndroidElement> driver) {
        this.driver = driver;
        initElements(new AppiumFieldDecorator(driver), this);
    }

    public double calculatePriceSum() {
        double priceSum = 0;
        int cartCounter = productPriceLbl.size();
        for(int j=0;j<cartCounter;j++) { //O(n) time complexity
            /*
             * get(index) method is a constant time O(1) operation
             */
            String price = productPriceLbl.get(j).getText();
            priceSum = priceSum + Double.parseDouble(price.substring(1));
        }
        return priceSum;
    }

    public double getTotalAmount() {
        String totalPrice = totalAmountLbl.getText();
        return Double.parseDouble(totalPrice.substring(1));
    }
}
