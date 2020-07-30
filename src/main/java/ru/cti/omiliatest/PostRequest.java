package ru.cti.omiliatest;

import java.util.Properties;

public class PostRequest {
    private String application_id;
    private String source;
//    private User user = new User();
//    Context context = new Context();

    public void setProperties(Properties properties) {
        application_id = properties.getProperty("application_id");
        source = properties.getProperty("source");
//        getProperties(properties, user);
//        context.setProperties(properties);
    }

    static void getProperties(Properties properties, User user) {
        user.setUser_id(properties.getProperty("user_id"));
        user.setEmail(properties.getProperty("email"));
        user.setFirst_name(properties.getProperty("first_name"));
        user.setLast_name(properties.getProperty("last_name"));
        user.setFullname(properties.getProperty("fullname"));
        user.setPhone_number(properties.getProperty("phone_number"));
        user.setUsername(properties.getProperty("username"));
    }
}