package com.autohero.ui.utils;

import com.autohero.ui.exceptions.UnknownBrowserException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverManager {
    private static final String CURRENT_BROWSER = "browser";
    private static final String OS = "os.name";
    private static final String CHROME_DRIVER = "driver.chrome";
    private static final String CHROME_DRIVER_PATH_MAC = "chromedriver.path.mac";
    private static final String CHROME_DRIVER_PATH_LINUX = "chromedriver.path.linux";
    private static final String CHROME_DRIVER_PATH_WINDOWS = "chromedriver.path.windows";
    private static final String GECKO_DRIVER = "driver.gecko";
    private static final String GECKO_DRIVER_PATH_MAC = "geckodriver.path.mac";
    private static final String GECKO_DRIVER_PATH_LINUX = "geckodriver.path.linux";
    private static final String GECKO_DRIVER_PATH_WINDOWS = "geckodriver.path.windows";
    private static WebDriver driver;

    private WebDriverManager(){}

    public static WebDriver getDriver(){
        if (driver == null){
            driver = initDriver();
            return driver;
        }
        else {
            return driver;
        }
    }

    //TODO create enum or constants for browsers
    private static WebDriver initDriver() {
        switch (PropertyReader.getProperty(CURRENT_BROWSER)) {
            case "chrome":
                return initChromeDriver();
            case "firefox":
                return initFirefoxDriver();
            default:
                throw new UnknownBrowserException("Cannot create driver for unknown browser type");
        }
    }

    //TODO create enum or constants for OS
    private static WebDriver initChromeDriver(){
        if (System.getProperty(OS).contains("Linux")) {
            System.setProperty(CHROME_DRIVER, CHROME_DRIVER_PATH_LINUX);
        } else if (System.getProperty(OS).contains("Mac")) {
            System.setProperty(PropertyReader.getProperty(CHROME_DRIVER), PropertyReader.getProperty(CHROME_DRIVER_PATH_MAC));
        } else if (System.getProperty(OS).contains("Windows")) {
            System.setProperty(CHROME_DRIVER, CHROME_DRIVER_PATH_WINDOWS);
        }
        return new ChromeDriver();
    }

    public static WebDriver initFirefoxDriver(){
        if (System.getProperty(OS).contains("Linux")) {
            System.setProperty(GECKO_DRIVER, GECKO_DRIVER_PATH_LINUX);
        } else if (System.getProperty(OS).contains("Mac")) {
            System.setProperty(PropertyReader.getProperty(GECKO_DRIVER), PropertyReader.getProperty(GECKO_DRIVER_PATH_MAC));
        } else if (System.getProperty(OS).contains("Windows")) {
            System.setProperty(GECKO_DRIVER, GECKO_DRIVER_PATH_WINDOWS);
        }
        return new FirefoxDriver();
    }
}
