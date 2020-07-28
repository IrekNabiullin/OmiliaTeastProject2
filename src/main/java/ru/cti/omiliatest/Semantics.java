package ru.cti.omiliatest;

import java.util.Properties;

public class Semantics {
    private String key1;
    private String key2;

    public void setProperties(Properties properties) {
        key1 = properties.getProperty("key1");
        key2 = properties.getProperty("key2");
    }
}
