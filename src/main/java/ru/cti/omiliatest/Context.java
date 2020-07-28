package ru.cti.omiliatest;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Context {
//    HashMap<String, String> semantics = new HashMap<String, String>();
    Semantics semantics = new Semantics();
    AsrInfo asr_info = new AsrInfo();

    public void setProperties(Properties properties) {
        semantics.setProperties(properties);
    }
/*
        properties.forEach((k, v) -> {semantics.put((String) k, (String) v);});

    Properties p = new Properties();
p.load(inputStream);
    Map<String, String> map = new HashMap<>();
p.forEach((k, v) -> {map.put((String) k, (String) v);});


 */
}
