package com.academy.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertyManager {
    private Map<String, PropertyWrapper> properties = new HashMap<>();

    private static PropertyManager instance;

    private PropertyManager() {}

    private static PropertyManager getInstance() {
        if (instance == null)
            instance = new PropertyManager();

        return instance;
    }

    private void ensureLoaded(String name) {
        if (properties.containsKey(name))
            return;

        // load properties
        properties.put(name, new PropertyWrapper());
        InputStream is= PropertyManager.class.getClassLoader().getResourceAsStream(name+".properties");
        try {
            properties.get(name).load(new InputStreamReader(is, "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static PropertyWrapper from(String name) {
        getInstance().ensureLoaded(name);

        return getInstance().properties.get(name);
    }

    public class PropertyWrapper {
        private Properties properties = new Properties();

        private void load(InputStreamReader ior) throws IOException {
            properties.load(ior);
        }

        public String getProperty(String key) {
            return properties.getProperty(key);
        }

        public Boolean getBoolean(String key) {
            return Boolean.parseBoolean(properties.getProperty(key));
        }
    }
}