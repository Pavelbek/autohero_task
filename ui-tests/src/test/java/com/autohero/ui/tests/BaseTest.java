package com.autohero.ui.tests;

import com.autohero.ui.utils.PropertyReader;
import com.autohero.ui.utils.WebDriverManager;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    protected WebDriver driver;

    @BeforeTest(alwaysRun = true)
    public void setUp(){
        driver = WebDriverManager.getDriver();
        WebDriverRunner.setWebDriver(driver);
        driver.manage().window().maximize();
        Configuration.baseUrl = PropertyReader.getProperty("baseUrl");
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
    }

    @AfterTest(alwaysRun = true)
    public void tearDown(){
        if (driver != null) {
            driver.quit();
        }
    }

    @Step("at {clazz}")
    protected  <T> T at(Class<T> clazz){
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
