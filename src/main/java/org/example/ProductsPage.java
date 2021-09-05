package org.example;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import java.util.List;

import static org.openqa.selenium.support.PageFactory.initElements;

public class ProductsPage extends Page {

    @AndroidFindBy(id = "productName")
    private List<MobileElement> productNameLbl;

    @AndroidFindBy(id = "productAddCart")
    private List<MobileElement> productAddCartBtn;

    @AndroidFindBy(id = "appbar_btn_cart")
    private MobileElement cartBtn;

    public ProductsPage(AndroidDriver<AndroidElement> driver) {
        this.driver = driver;
        initElements(new AppiumFieldDecorator(driver), this);
    }

    public void addProductsToCart() {
        int count = productNameLbl.size();
        for (int i=0;i<count;i++) { //O(n) time complexity
            productAddCartBtn.get(i).click();
        }
    }

    public void clickCartBtn() {
        cartBtn.click();
    }

    public CartPage renderCartPage() {
        return new CartPage(driver);
    }

}
