package org.example;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import static org.openqa.selenium.support.PageFactory.initElements;


public class LandingPage extends Page {

    @AndroidFindBy(id = "spinnerCountry")
    private MobileElement spinnerCountry;

    @AndroidFindBy(id = "nameField")
    private MobileElement nameField;

    @AndroidFindBy(id = "radioFemale")
    private MobileElement radioFemale;

    @AndroidFindBy(id = "btnLetsShop")
    private MobileElement letsShopBtn;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Argentina']")
    private MobileElement argentinaLbl;

    public LandingPage(AndroidDriver<AndroidElement> driver) {
        this.driver = driver;
        initElements(new AppiumFieldDecorator(driver), this);
    }

    public void clickNameField() {
        nameField.click();
    }

    public void sendKeysToNameField(String key) {
        nameField.sendKeys(key);
    }

    public void clickRadioFemale() {
        radioFemale.click();
    }

    public void clickSpinnerCountry() {
        spinnerCountry.click();
    }

    public void clickArgentinaLbl() {
        argentinaLbl.click();
    }

    public ProductsPage clickLetsShopBtn() {
        letsShopBtn.click();
        return new ProductsPage(driver);
    }

}
