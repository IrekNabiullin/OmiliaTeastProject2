package ru.cti.omiliatest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {

        String host = new String();
        String accessToken = new String();
        Properties properties = new Properties();

        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream fileInputStream = loader.getResourceAsStream("app.properties");
            //         FileInputStream fileInputStream = new FileInputStream("src/main/resources/app.properties");
            properties.load(fileInputStream);
            host = properties.getProperty("host").intern();
            accessToken = properties.getProperty("access_token");
        } catch (FileNotFoundException e) {
            System.err.println("Error: Property file not found!");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        System.out.println("Creating new Post Request");
        PostRequest postRequest = new PostRequest();
        postRequest.setProperties(properties);
        String gsonObject = GsonConvertation.convertObjectToGson(postRequest);
        System.out.println("New Post Request created:");
        System.out.println(gsonObject);

        System.out.println("\nTesting - Send Https GET request");
        HttpsConnectGetURL connectionGet = new HttpsConnectGetURL();
        try {
            connectionGet.sendHttpGETRequest(host, accessToken);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("\nTesting - Send Https POST request");
        HttpsConnectPostURL connectionPost = new HttpsConnectPostURL();
        try {
            connectionPost.sendPOST(host, gsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
