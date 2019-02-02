package com.autohero.ui.pages;

import com.autohero.ui.utils.WebDriverManager;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.autohero.ui.utils.WebDriverManager.getDriver;

public class BasePage {

    public boolean waitForJsAndJQueryToLoad() {

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
}
