package com.academy.automationpractice.ddt.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class PropertyManager {
    private static final String PROP_FILE_NAME = "automation.properties";

    private Properties properties;

    private static PropertyManager instance;

    private PropertyManager() {
        loadProperties();
    }

    private static PropertyManager getInstance() {
        if (instance == null)
            instance = new PropertyManager();

        return instance;
    }

    private void loadProperties() {
        // load properties
        properties = new Properties();
        InputStream is= PropertyManager.class.getClassLoader().getResourceAsStream(PROP_FILE_NAME);
        try {
            properties.load(new InputStreamReader(is, "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return getInstance().properties.getProperty(key);
    }
}