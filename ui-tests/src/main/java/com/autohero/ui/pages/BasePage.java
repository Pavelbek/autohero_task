package com.autohero.ui.pages;

import com.autohero.ui.utils.WebDriverManager;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.autohero.ui.utils.WebDriverManager.getDriver;

public class BasePage {

    protected boolean waitForJsAndJQueryToLoad() {

        WebDriverWait wait = new WebDriverWait(WebDriverManager.getDriver(), 10);

        // wait for jQuery to load
        ExpectedCondition<Boolean> jQueryLoad = driver -> {
            try {
                return ((Long)((JavascriptExecutor)getDriver()).executeScript("return jQuery.active") == 0);
            }
            catch (Exception e) {
                // no jQuery present
                return true;
            }
        };

        // wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = driver -> ((JavascriptExecutor)getDriver()).executeScript("return document.readyState")
                .toString().equals("complete");

        return wait.until(jQueryLoad) && wait.until(jsLoad);
    }

    protected void sleepForSeconds(int seconds){
        Selenide.sleep(seconds * 1000);
    }
}
