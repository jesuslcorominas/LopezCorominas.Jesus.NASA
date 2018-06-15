package com.jesuslcorominas.nasa.appium.view.activity;

import com.jesuslcorominas.nasa.appium.AppiumTesteable;
import com.jesuslcorominas.nasa.appium.utils.AppiumHelper;

import org.openqa.selenium.support.PageFactory;

/**
 * @author Jesús López Corominas
 */
public class AppiumAwesomeActivity implements AppiumTesteable {

    @Override
    public boolean runTests(String screenshotsDir, String packageName) {
        AppiumAwesomeActivityObject appiumAwesomeActivityObject = new AppiumAwesomeActivityObject();
        PageFactory.initElements(AppiumHelper.getDriver(), appiumAwesomeActivityObject);

        AppiumHelper.getScreenshots(screenshotsDir, "01_start");

        appiumAwesomeActivityObject.itemMenuCopyrigth.click();

        AppiumHelper.getScreenshots(screenshotsDir, "02_licenses");

        appiumAwesomeActivityObject.button1.click();

        return true;
    }
}
