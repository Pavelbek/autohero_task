package com.autohero.ui.utils;

import com.autohero.ui.exceptions.UnknownPropertyException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    private static final String PROPERTIES = "/config.properties";
    private static Properties properties;

    private PropertyReader(){}

    public static String getProperty(String name) {
        if (properties == null){
            properties = loadPropertiesFile();
        }
        Object result = properties.get(name);
        if (result == null) {
            return null;
        } else {
            return result.toString();
        }
    }

    private static Properties loadPropertiesFile() {
        try {
            Properties result = new Properties();
            InputStream stream = PropertyReader.class.getResourceAsStream(PROPERTIES);
            result.load(stream);
            return result;
        } catch (IOException e) {
            throw new UnknownPropertyException("Property file is not found");
        }
    }
}
