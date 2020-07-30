package ru.cti.omiliatest;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import javax.net.ssl.*;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

public class HttpsConnectGetURL {
    private String accessToken;

    //костыль для SSL
    private static TrustManager[] trustAllCerts = new TrustManager[]{
            new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                public void checkClientTrusted(
                        java.security.cert.X509Certificate[] certs, String authType) {
                }

                public void checkServerTrusted(
                        java.security.cert.X509Certificate[] certs, String authType) {
                }
            }
    };

/*
    // Create a trust manager that does not validate certificate chains
    private static TrustManager[] trustAllCerts = new TrustManager[] {
            new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
                public void checkClientTrusted(X509Certificate[] certs, String authType) {}
                public void checkServerTrusted(X509Certificate[] certs, String authType) {}
            }};
 */


    // Ignore differences between given hostname and certificate hostname
    private static HostnameVerifier hv = new HostnameVerifier() {
        public boolean verify(String hostname, SSLSession session) { return true; }
    };



    public static void sendHttpGETRequest(String url, String accessToken) throws IOException {
        URL obj = new URL(url);
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) obj.openConnection();

        //блок строк для обхода сертификата
//        httpsURLConnection.setSSLSocketFactory(sslsocketfactoryOne);
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(hv);

            //после обхода сертификата
            httpsURLConnection.setRequestMethod("GET");
            httpsURLConnection.setRequestProperty("accept", "application/json");
        httpsURLConnection.setRequestProperty("Accept-Charset", "UTF-8");
// есть ли ограничения по кодировке (charset) в API?
            httpsURLConnection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
//            httpsURLConnection.setRequestProperty("Authorization", "Bearer " + accessToken);
//            httpsURLConnection.setRequestProperty("Authorization", "Bearer ");

            int responseCode = httpsURLConnection.getResponseCode();
            System.out.println("GET Response Code: " + responseCode);
            if (responseCode == HttpsURLConnection.HTTP_OK) { // success
                System.out.println("GET request is OK");
                BufferedReader in = new BufferedReader(new InputStreamReader(httpsURLConnection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // print result
                System.out.println(response.toString());
            } else {
                System.out.println("GET request not worked");
            }

            for (int i = 1; i <= 8; i++) {
                System.out.println(httpsURLConnection.getHeaderFieldKey(i) + " = " + httpsURLConnection.getHeaderField(i));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}